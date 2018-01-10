package miage.m2sid.dicegame.persistance;

import java.util.Map;

public interface EntityManager {

    Map<String,String> charger();
    //boolean isExit(int tour);
    void sauvegarder(int score, String pseudo);

}
