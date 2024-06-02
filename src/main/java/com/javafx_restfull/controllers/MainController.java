package com.javafx_restfull.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    MenuBar mainMenuBar;
    @FXML
    MenuItem studentMenuItem;
    @FXML
    MenuItem teacherMenuItem;
    @FXML
    AnchorPane contentPane;

//    @FXML
//    public void initialize() {
//        loadStudentScene();
//    }
    @FXML
    public void loadStudentScene(){
        loadScene("/com/javafx_database/StudentView.fxml");
    }
    @FXML
    public void loadTeacherScene(){
        loadScene("/com/javafx_database/TeacherView.fxml");
    }
    @FXML
    public void loadCourseScene(){
        loadScene("/com/javafx_database/CourseView.fxml");
    }


    private void loadScene(String fxmlFile) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        try {
            Node node = fxmlLoader.load();
            contentPane.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
