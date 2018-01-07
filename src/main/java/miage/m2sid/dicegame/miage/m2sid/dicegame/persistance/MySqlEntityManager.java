package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

public class MySqlEntityManager implements EntityManager {
    static MySqlEntityManager mySqlEntityManager;

    public static EntityManager getInstance() {
        if(mySqlEntityManager == null)
            mySqlEntityManager = new MySqlEntityManager();

        return mySqlEntityManager;
    }
}
