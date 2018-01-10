package miage.m2sid.dicegame.persistance;

import java.sql.Connection;

public interface DBConnection {
    void connection();
    Connection getConnection();
}
