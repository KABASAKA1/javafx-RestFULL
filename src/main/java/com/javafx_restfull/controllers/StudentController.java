package com.javafx_restfull.controllers;


import com.javafx_restfull.model.Student;
import com.javafx_restfull.services.StudentApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
    private final StudentApiService studentApiService= new StudentApiService();

    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student,Integer> studentIDColumn;
    @FXML
    private TableColumn<Student,String> studentNameColumn;
    @FXML
    private TableColumn<Student,Integer> studentAgeColumn;
    @FXML
    private Button buttonList;
    @FXML
    private Button buttonReply;
    @FXML
    private TextField tfStudentId;
    @FXML
    private Button btnFindId;
    @FXML
    private TextField tfStudentName;
    @FXML
    private TextField tfStudentAge;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnDeleteStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        studentAgeColumn.setCellValueFactory(new PropertyValueFactory<>("student_age"));
    }

    @FXML
    public void loadStudentsData(){
        List<Student> studentList = studentApiService.get_all();
        ObservableList<Student> observableList = FXCollections.observableArrayList(studentList);
        tableView.setItems(observableList);
    }

    @FXML
    public void yenile(){
        tableView.getItems().clear();
    }

    @FXML
    public void findStudentById(){
        Student student = studentApiService.get_by_id(Integer.parseInt(tfStudentId.getText()));
        tfStudentId.clear();
        ObservableList<Student> observableList = FXCollections.observableArrayList(student);
        tableView.setItems(observableList);
    }

    @FXML
    public void addStudent(){
        Student student = new Student();
        student.setStudent_id(Integer.parseInt(tfStudentId.getText()));
        student.setStudent_name(tfStudentName.getText());
        student.setStudent_age(Integer.parseInt(tfStudentAge.getText()));
        studentApiService.add(student);
    }
    @FXML
    public void deleteStudent(){
        studentApiService.delete(Integer.parseInt(tfStudentId.getText()));
        tfStudentId.clear();
    }

}

