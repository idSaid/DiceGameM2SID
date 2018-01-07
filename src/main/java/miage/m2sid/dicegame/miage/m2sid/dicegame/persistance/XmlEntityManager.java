package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

public class XmlEntityManager implements EntityManager {
    static XmlEntityManager xmlEntityManager;

    public static EntityManager getInstance() {
        if(xmlEntityManager == null)
            xmlEntityManager = new XmlEntityManager();

        return xmlEntityManager;
    }
}
