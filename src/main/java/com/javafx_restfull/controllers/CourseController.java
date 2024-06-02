package com.javafx_restfull.controllers;

import com.javafx_restfull.model.Course;
import com.javafx_restfull.services.CourseApiService;
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

public class CourseController implements Initializable {
    private final CourseApiService courseApiService = new CourseApiService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        teacherIdColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty().get().teacher_idProperty().asObject());
        teacherNameColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty().get().teacher_nameProperty());
        teacherAgeColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty().get().teacher_ageProperty().asObject());
    }

    @FXML
    TableView<Course> tableViewCourse;
    @FXML
    TableColumn<Course, Integer> courseIdColumn;
    @FXML
    TableColumn<Course, String> courseNameColumn;
    @FXML
    TableColumn<Course, Integer> teacherIdColumn;
    @FXML
    TableColumn<Course, String> teacherNameColumn;
    @FXML
    TableColumn<Course, Integer> teacherAgeColumn;
    @FXML
    TextField tfCourseId;
    @FXML
    TextField tfCourseName;
    @FXML
    TextField tfTeacherId;
    @FXML
    Button btnListAll;
    @FXML
    Button btnClearTable;
    @FXML
    Button btnFindCourse;
    @FXML
    Button btnAddCourse;
    @FXML
    Button btnDeleteCourse;



    @FXML
    public void loadCoursesData(){
        List<Course> courseList = courseApiService.get_all();
        ObservableList<Course> courseObservableList = FXCollections.observableList(courseList);
        tableViewCourse.setItems(courseObservableList);
    }

    @FXML
    public void yenile(){
        tableViewCourse.getItems().clear();
    }

    @FXML
    public void findCourseById(){
        Course course = courseApiService.get_by_id(Integer.parseInt(tfCourseId.getText()));
        tfTeacherId.clear();
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
        courseObservableList.add(course);
        tableViewCourse.setItems(courseObservableList);
    }

    @FXML
    public void addCourse(){
        Course course = new Course();
        course.setCourse_name(tfCourseName.getText());
        // burada teacher_id eklenmesi yapılıyordu
        courseApiService.add(course);
    }
    @FXML
    public void deleteCourse(){
        courseApiService.delete(Integer.parseInt(tfCourseId.getText()));
    }



}
