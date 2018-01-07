package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

public class Factory {

    static Factory factory;

    DBConnection createDBConnection(int db){
        switch (db) {
            case 1:
                return MySqlConnection.getInstance();
            case 2:
                return XmlConnection.getInstance();
            default:
                return null;
        }
    }

    EntityManager createEntityManager(int db){
        switch (db) {
            case 1:
                return MySqlEntityManager.getInstance();
            case 2:
                return XmlEntityManager.getInstance();
            default:
                return null;
        }
    }

    static Factory getInstance(){
        if(factory == null)
            factory = new Factory();

        return factory;
    }
}
