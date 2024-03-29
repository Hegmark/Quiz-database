package com.example.quiz.Controller;

import com.example.quiz.QuizApp;
import com.example.quiz.modules.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuController{
    @FXML
    public Button btnEditData;
    @FXML
    private Label lblMenu;
    @FXML
    private Button btnProfile;
    @FXML
    private Button btnStatistics;
    @FXML
    private Button btnStartQuiz;
    @FXML
    private Button btnLeaderboard;

    private Player currentPlayer;


    public void loadStatistics(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("statistics.fxml"));
        Parent fxml = fxmlLoader.load();

        StatisticsController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        QuizApp.setRoot(fxml);
    }

    public void loadStartQuiz(ActionEvent event) {

    }
    public void loadLeaderboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("leaderboard.fxml"));
        Parent fxml = fxmlLoader.load();

        LeaderboardController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        QuizApp.setRoot(fxml);
    }

    public void setPlayer(Player player){
        this.currentPlayer = player;
        if(currentPlayer.getUserName().equals("admin")){
            btnEditData.setVisible(true);
            btnProfile.setVisible(false);
        }
        else{
            btnEditData.setVisible(false);
            btnProfile.setVisible(true);
        }
    }

    public void loadAdminCRUD(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("adminCRUD.fxml"));
        Parent fxml = fxmlLoader.load();

        AdminListController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        QuizApp.setRoot(fxml);
    }

    public void loadProfile(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("profile.fxml"));
        Parent fxml = fxmlLoader.load();

        ProfileController controller = fxmlLoader.getController();
        controller.setData(currentPlayer);

        QuizApp.setRoot(fxml);
    }
}
