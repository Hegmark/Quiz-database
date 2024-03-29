package com.example.quiz.Controller;

import com.example.quiz.QuizApp;
import com.example.quiz.modules.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ProfileController {
    @FXML private TextField txtfUsername;
    @FXML private TextField txtfPassword;
    @FXML private TextField txtfEmail;
    @FXML private Button btnModify;
    @FXML private Button btnCancel;
    @FXML private Label label;
    private Player currentPlayer= null;

    public void btnCancelAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();
        controller.setPlayer(currentPlayer);

        QuizApp.setRoot(fxml);
    }

    public void btnModifyAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("addPlayer.fxml"));
        Parent fxml = fxmlLoader.load();

        AddPlayerController controller = fxmlLoader.getController();
        controller.setFrom(true);
        controller.setData(currentPlayer);


        QuizApp.setRoot(fxml);
    }

    public void setData(Player player){
        this.currentPlayer = player;
        txtfUsername.setText(player.getUserName());
        txtfPassword.setText(player.getPassword());
        txtfEmail.setText(player.getEmail());
        txtfEmail.setEditable(false);
        txtfPassword.setEditable(false);
        txtfUsername.setEditable(false);
    }
}
