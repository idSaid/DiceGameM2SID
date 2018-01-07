package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

import miage.m2sid.dicegame.miage.m2sid.dicegame.utils.NbrTours;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySqlEntityManager implements EntityManager {

    Factory factory = Factory.getInstance();
    DBConnection connection = factory.createDBConnection(1);

    static MySqlEntityManager mySqlEntityManager;
    private static String DB_NAME = "dice_game";
    private static String TABLE_NAME = "meilleur_score";

    public static EntityManager getInstance() {
        if(mySqlEntityManager == null)
            mySqlEntityManager = new MySqlEntityManager();

        return mySqlEntityManager;
    }

    public Map<String, String> charger() {

        Map<String, String> result = null;
		/* Chargement du driver JDBC pour MySQL */
        this.connection.connection();

		/* Connexion à la base de données */
        try {
            this.connection.getConnection();
            Statement s =this.connection.getConnection().createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            s.executeUpdate("use " + DB_NAME);

            String sql = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` (" + "`nombre_tour` int(11) NOT NULL,"
                    + "`score` int(11) NOT NULL," + "`pseudo` varchar(255) NOT NULL," + " PRIMARY KEY (`nombre_tour`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            s.executeUpdate(sql);

            String selectTableSQL = "SELECT score, pseudo from " + TABLE_NAME + " WHERE nombre_tour="
                    + NbrTours.NOMBRE_MAX_TOURS;
            s =this.connection.getConnection().createStatement();
            ResultSet rs = s.executeQuery(selectTableSQL);
            while (rs.next()) {
                String score = rs.getString("score");
                String pseudo = rs.getString("pseudo");
                if (score != null && pseudo != null) {
                    result = new HashMap<String, String>();
                    result.put("score", String.valueOf(score));
                    result.put("pseudo", pseudo);
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Exception.............. !");
        } finally {
            if (this.connection.getConnection() != null)
                try {
                   this.connection.getConnection().close();
                } catch (SQLException ignore) {
                }
        }

        return result;
    }

    public boolean isExit(int tour) {
        /* Chargement du driver JDBC pour MySQL */
        this.connection.connection();

		/* Connexion à la base de données */
        try {
            Statement s =this.connection.getConnection().createStatement();
            s.executeUpdate("use " + DB_NAME);
            String selectTableSQL = "SELECT nombre_tour from " + TABLE_NAME + " WHERE nombre_tour=" + tour;
            s = this.connection.getConnection().createStatement();
            ResultSet rs = s.executeQuery(selectTableSQL);
            String nbrTour = "";
            while (rs.next()) {
                nbrTour = rs.getString("nombre_tour");
            }
            if (this.connection.getConnection() != null)
                this.connection.getConnection().close();
            if (nbrTour.equals("") || nbrTour == null)
                return false;
            else
                return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
			/* Gérer les éventuelles erreurs ici */
            System.out.println("Exception.............. !");
            return false;
        }
    }

    public void sauvegarder(int score, String pseudo) {
        /* Chargement du driver JDBC pour MySQL */
        this.connection.connection();


        try {
            Statement s =this.connection.getConnection().createStatement();
            s.executeUpdate("use " + DB_NAME);
            String rqtSQL = "";
            boolean exist = this.isExit(NbrTours.NOMBRE_MAX_TOURS);
            if (exist) {
                rqtSQL = "UPDATE " + TABLE_NAME + " SET score=" + score + ", pseudo='" + pseudo + "' WHERE nombre_tour="
                        + NbrTours.NOMBRE_MAX_TOURS;
            } else {
                rqtSQL = "INSERT INTO " + TABLE_NAME + "(nombre_tour, score, pseudo) VALUES" + "("
                        + NbrTours.NOMBRE_MAX_TOURS + "," + score + ",'" + pseudo + "')";
            }
            PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(rqtSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			/* Gérer les éventuelles erreurs ici */
            System.out.println("Exception.............. !");
        } finally {
            if (this.connection.getConnection() != null)
                try {
					/* Fermeture de la connexion */
                    this.connection.getConnection().close();
                } catch (SQLException ignore) {
					/*
					 * Si une erreur survient lors de la fermeture, il suffit de
					 * l'ignorer.
					 */
                }
        }
    }
}
