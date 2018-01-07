package miage.m2sid.dicegame.miage.m2sid.dicegame.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Result implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
