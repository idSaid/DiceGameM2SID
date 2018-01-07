package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlConnection implements DBConnection {

    static MySqlConnection mySqlConnection;
    public Connection conn;
    private Statement statement;

    public void connection() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "dice_game";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if(mySqlConnection == null)
            mySqlConnection = new MySqlConnection();

        return mySqlConnection;
    }
}
