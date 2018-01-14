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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {
    @FXML
    private Label tvScore;

    @FXML
    private Button btMain;

    @FXML
    private Button btHighScore;

    public void initialize(URL location, ResourceBundle resources) {
        tvScore.setText(Game.getInstance().getScore()+"");

        btMain.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                goToMain();
            }
        });

        btHighScore.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("btHighScore");
            }
        });
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
}
