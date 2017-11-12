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

    public Game() {
    }

    public Game(int id, int round, int score, Dice dice1, Dice dice2, Player player) {
        this.id = id;
        this.round = round;
        this.score = score;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.player = player;
        Start();
    }

    public int Start(){
        return 0;
    }


}
