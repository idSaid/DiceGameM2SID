package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;

import java.util.Map;

public class XmlEntityManager implements EntityManager {
    static XmlEntityManager xmlEntityManager;

    public static EntityManager getInstance() {
        if(xmlEntityManager == null)
            xmlEntityManager = new XmlEntityManager();

        return xmlEntityManager;
    }

    public Map<String, String> charger() {
        return null;
    }

    public boolean isExit(int tour) {
        return false;
    }

    public void sauvegarder(int score, String pseudo) {

    }
}
