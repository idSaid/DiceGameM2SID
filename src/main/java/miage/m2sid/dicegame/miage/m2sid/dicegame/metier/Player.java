package miage.m2sid.dicegame.miage.m2sid.dicegame.metier;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Observable;

@Entity
@Data
public class Player extends Observable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    public Player() {
    }

    public Player(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
