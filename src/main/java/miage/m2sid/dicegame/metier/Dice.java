package miage.m2sid.dicegame.metier;

import java.util.Observable;
import java.util.Random;

public class Dice extends Observable{

    private int id;
    private int state;

    public Dice() {
    }

    public Dice(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void roll(){
        Random rand = new Random();
        // Random entre 1 et 6
        setState(rand.nextInt(6) + 1);
        // On notifie l'observateur
        this.setChanged();
        this.notifyObservers(state);
    }

}
