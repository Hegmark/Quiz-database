package com.example.quiz.Controller;

import com.example.quiz.QuizApp;
import com.example.quiz.database.IQuizDAO;
import com.example.quiz.database.QuizDAO;
import com.example.quiz.modules.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{
    @FXML public ComboBox<Integer> quizIdComboBox;
    @FXML public ListView<String> questionsListView;
    @FXML private ListView<String> topPlayers;
    @FXML private TableView<QuestionCount> popularQuestionTable;
    @FXML private TableColumn<QuestionCount, String> popularQuestionColumn;
    @FXML private TableColumn<QuestionCount, Integer> nmAppColumn;
    @FXML private TableView<RankingByTopic> acrossTable;
    @FXML private TableColumn<RankingByTopic,String> AcrosstopicColumn;
    @FXML private TableColumn<RankingByTopic, Float> acrossAverageColumn;
    @FXML private TableView<SubtopicDescByTopic> subtopicDescTable;
    @FXML private TableColumn<SubtopicDescByTopic, String> topicColumn;
    @FXML private TableColumn<SubtopicDescByTopic, String> subtopicColumn;
    @FXML private TableColumn<SubtopicDescByTopic, String> descColumn;



    @FXML private Label lblStatistics;
    @FXML private Button btnBack;
    private IQuizDAO dao;
    private Player currentPlayer;
    private ObservableList<Integer> quizIds = FXCollections.observableArrayList();

    public void loadBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApp.class.getResource("menu.fxml"));
        Parent fxml = fxmlLoader.load();

        MenuController controller = fxmlLoader.getController();
        controller.setPlayer(currentPlayer);

        QuizApp.setRoot(fxml);
    }



    public void listQuestions(ActionEvent actionEvent) {
        try{
            var results = dao.questionsOfPlayedQuiz(quizIdComboBox.getValue());
            questionsListView.getItems().setAll(results);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setData(Player player){
        this.currentPlayer = player;
        List<Integer> quizzes = new ArrayList<>();
        try {
            quizzes = dao.playedQuizId(currentPlayer);
            var popularQuest= dao.listMostFrequentQuestionsPlayedUser(currentPlayer.getUserName());
            var across = dao.rankingByTopic(currentPlayer.getUserName());

            acrossTable.getItems().setAll(across);
            popularQuestionTable.getItems().setAll(popularQuest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        quizIds.addAll(quizzes);
        quizIdComboBox.getItems().setAll(quizIds);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new QuizDAO();

        popularQuestionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nmAppColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topicNamePlayer"));

        topicColumn.setCellValueFactory(new PropertyValueFactory<>("topicName"));
        subtopicColumn.setCellValueFactory(new PropertyValueFactory<>("subtopicName"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        AcrosstopicColumn.setCellValueFactory(new PropertyValueFactory<>("topic"));
        acrossAverageColumn.setCellValueFactory(new PropertyValueFactory<>("point"));
        try {
            var subtopicDesc = dao.subtopicDescriptionByTopic();
            var top = dao.playersWithBigRankingPoints();

            subtopicDescTable.getItems().setAll(subtopicDesc);
            topPlayers.getItems().setAll(top);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
