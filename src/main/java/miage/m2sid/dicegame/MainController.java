package miage.m2sid.dicegame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable {
    @FXML
    Button btLancerDe;

    @FXML
    ImageView imageDe1;

    @FXML
    ImageView imageDe2;

    @FXML
    Label score;

    public void initialize(URL location, ResourceBundle resources) {
        btLancerDe.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                lancerDe();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            public void run() {
                                afficherDe(2,6);
                            }
                        });
                    }
                }, 3000);
                btLancerDe.setDisable(true);
            }
        });
    }

    public void lancerDe(){
        imageDe1.setImage(new Image(getClass().getResourceAsStream("/images/animation.gif")));
        imageDe2.setImage(new Image(getClass().getResourceAsStream("/images/animation.gif")));
        score.setText(String.valueOf(0));
    }

    public void afficherDe(int valeurDe1, int valeurDe2){
        btLancerDe.setDisable(false);
        imageDe1.setImage(new Image(getClass().getResourceAsStream("/images/"+valeurDe1+".png")));
        imageDe2.setImage(new Image(getClass().getResourceAsStream("/images/"+valeurDe2+".png")));
        score.setText(String.valueOf(valeurDe1+valeurDe2));
    }
}
