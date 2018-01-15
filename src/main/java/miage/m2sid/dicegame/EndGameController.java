package miage.m2sid.dicegame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import miage.m2sid.dicegame.control.Game;
import miage.m2sid.dicegame.persistance.EntityManager;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {
    @FXML
    private Label tvScore;

    @FXML
    private Button btMain;

    @FXML
    private Label tvBestPlayer;

    @FXML
    private Label tvBestScore;

    public void initialize(URL location, ResourceBundle resources) {
        tvScore.setText(Game.getInstance().getScore()+"");

        btMain.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                goToMain();
            }
        });

        chargerBDD();
    }

    private void goToMain(){
        Parent root = null;
        try {
            Stage primaryStage = (Stage)btMain.getScene().getWindow();

            root = FXMLLoader.load(getClass().getResource("/ihm/login_form.fxml"));
            primaryStage.setTitle("DiceGame");

            Scene scene = new Scene(root, 291, 324);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerBDD(){
        EntityManager entityManager = Game.getInstance().getEntityManager();
        Map<String, String> highscore = entityManager.charger();
        int score = Game.getInstance().getScore();
        String playerName = Game.getInstance().getPseudoJoueur();
        // sauvegarder(score)
        if (highscore == null || highscore.isEmpty() || Integer.parseInt(highscore.get("score")) <= score) {
            tvBestPlayer.setText(playerName);
            tvBestScore.setText(String.valueOf(score));
            entityManager.sauvegarder(score, playerName);
            System.out.println("----------------- GAGNER -------------------");
        }else{
            tvBestPlayer.setText(highscore.get("pseudo"));
            tvBestScore.setText(highscore.get("score"));
            System.out.println("----------------- PERDU -------------------");
        }
    }
}
