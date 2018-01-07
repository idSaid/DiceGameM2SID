package miage.m2sid.dicegame.miage.m2sid.dicegame.control;

import miage.m2sid.dicegame.miage.m2sid.dicegame.metier.Dice;
import miage.m2sid.dicegame.miage.m2sid.dicegame.metier.Player;

public class Game {

    private int id;
    private int round;
    private int score;
    private Dice dice1;
    private Dice dice2;
    private Player player;
    private int nbMaxrounds;

    public Game() {
    }

    public Game(int id, int round, int score, Dice dice1, Dice dice2, Player player) {
        this.id = id;
        this.round = round;
        this.score = score;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.player = player;
        this.nbMaxrounds = 10; // Constente
        Start();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getNbMaxrounds() {
        return nbMaxrounds;
    }

    public void setNbMaxrounds(int nbMaxrounds) {
        this.nbMaxrounds = nbMaxrounds;
    }

    public void init(){

    }

    public void playARound(){
        this.round++;
        this.dice1.roll();
        this.dice2.roll();

        // si la somme des dés est égale à 7 on incrémente le score de 10 points
        if(dice1.getState()+dice2.getState()==7){
            this.score= this.score+10;
        }

        // si dernier tour on sauvegarde le score si c'est le meilleur score
        if(this.round==this.nbMaxrounds){

        }
    }

    public int Start(){
        return 0;
    }
}
