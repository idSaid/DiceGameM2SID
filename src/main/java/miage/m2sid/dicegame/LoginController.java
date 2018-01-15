package miage.m2sid.dicegame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import miage.m2sid.dicegame.control.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField inputPseudo;

    @FXML
    private Button btJouer;

    @FXML
    private Button btHighScore;

    public void initialize(URL location, ResourceBundle resources) {
        btJouer.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(inputPseudo.getText().length()>0){
                    startGame(inputPseudo.getText());
                }
            }
        });

        btHighScore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("btHighScore");
            }
        });
    }

    private void startGame(String pseudoJoueur){
        Parent root = null;
        try {
            Stage primaryStage = (Stage)btJouer.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/ihm/dice_game.fxml"));
            primaryStage.setTitle("DiceGame");

            Scene scene = new Scene(root, 640, 400);

            primaryStage.setScene(scene);
            primaryStage.show();

            Game game = Game.getInstance();
            game.init(pseudoJoueur);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
