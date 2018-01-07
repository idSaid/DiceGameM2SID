package miage.m2sid.dicegame.miage.m2sid.dicegame.control;

import miage.m2sid.dicegame.miage.m2sid.dicegame.metier.Dice;
import miage.m2sid.dicegame.miage.m2sid.dicegame.metier.Result;
import miage.m2sid.dicegame.miage.m2sid.dicegame.persistance.EntityManager;
import miage.m2sid.dicegame.miage.m2sid.dicegame.utils.NbrTours;

import java.util.Map;
import java.util.Observable;

public class Game extends Observable {

    private int id;
    private int round;
    private int score;
    private Dice dice1;
    private Dice dice2;
    private EntityManager entityManager;

    public Game(EntityManager entityManager) {
        this.round = 0;
        this.dice1 = new Dice();
        this.dice2 = new Dice();
        this.score = 0;
        this.entityManager=entityManager;
    }

    public Game(int id, int round, int score, Dice dice1, Dice dice2, EntityManager entityManager) {
        this.id = id;
        this.round = round;
        this.score = score;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.entityManager = entityManager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Dice getDice1() {
        return dice1;
    }

    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }


    public Result play(String playerName){
        Integer arg = null;
        int meilleurScore = -1;
        String pseudoMeilleurScore = "";
        this.round++;
        this.dice1.roll();
        this.dice2.roll();

        // si la somme des dés est égale à 7 on incrémente le score de 10 points
        if(dice1.getState()+dice2.getState()==7){
            this.score= this.score+10;
        }

        // si dernier tour on sauvegarde le score si c'est le meilleur score
        if(this.round== NbrTours.NOMBRE_MAX_TOURS){
            Map<String, String> highscore = entityManager.charger();
            // sauvegarder(score)
            if (highscore == null || highscore.isEmpty()
                    || Integer.parseInt((String) highscore.get("score")) <= score) {
                arg = score;
                entityManager.sauvegarder(score, playerName);
                Map<String, String> highscore2 = entityManager.charger();
                meilleurScore = Integer.valueOf(highscore2.get("score"));
                pseudoMeilleurScore = highscore2.get("pseudo");
                System.out.println("----------------- GANGER -------------------");
            }else{
                meilleurScore = Integer.valueOf(highscore.get("score"));
                pseudoMeilleurScore = highscore.get("pseudo");
                System.out.println("----------------- PERDU -------------------");
            }
        }
        Result result = new Result(dice1, dice2, score);
        if (NbrTours.NOMBRE_MAX_TOURS == round) {
            result.setMeilleurScore(meilleurScore);
            result.setPseudoMeilleurScore(pseudoMeilleurScore);
        }
        // On notifie les observateurs
        this.setChanged();
        this.notifyObservers(arg);
        return result;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
