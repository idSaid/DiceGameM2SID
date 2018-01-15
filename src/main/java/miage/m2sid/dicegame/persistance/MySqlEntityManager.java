package miage.m2sid.dicegame.persistance;

import miage.m2sid.dicegame.utils.NbrTours;

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
        if (mySqlEntityManager == null)
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
            Statement s = this.connection.getConnection().createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            s.executeUpdate("use " + DB_NAME);

            String sql = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "` ("
                    + "`score` int(11) NOT NULL," + "`pseudo` varchar(255) NOT NULL"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";

            s.executeUpdate(sql);

            String selectTableSQL = "SELECT score, pseudo from " + TABLE_NAME + " WHERE score = (SELECT MAX(score) FROM "+TABLE_NAME+" )";
            s = this.connection.getConnection().createStatement();
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

    public void sauvegarder(int score, String pseudo) {
        /* Chargement du driver JDBC pour MySQL */
        this.connection.connection();

        try {
            Statement s = this.connection.getConnection().createStatement();
            s.executeUpdate("use " + DB_NAME);
            String rqtSQL = "";

            rqtSQL = "INSERT INTO " + TABLE_NAME + "(score, pseudo) VALUES" + "("
                    + score + ",'" + pseudo + "')";

            PreparedStatement preparedStatement = this.connection.getConnection().prepareStatement(rqtSQL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			/* Gérer les éventuelles erreurs ici */
            System.out.println("Exception.............. !");
        } finally {
            if (this.connection.getConnection() != null){
                try {
                    this.connection.getConnection().close();
                } catch (SQLException e) { }
            }
        }
    }
}
