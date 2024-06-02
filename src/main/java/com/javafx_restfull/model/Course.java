package com.javafx_restfull.model;

import javafx.beans.property.*;

public class Course {
    private IntegerProperty course_id;
    private StringProperty course_name;
    private IntegerProperty teacher_id;

    public Course() {
        course_id = new SimpleIntegerProperty();
        course_name = new SimpleStringProperty();
        teacher_id = new SimpleIntegerProperty();
    }
    public Course(Integer course_id, String course_name, Teacher teacher) {
        this.course_id = new SimpleIntegerProperty(course_id);
        this.course_name = new SimpleStringProperty(course_name);
        this.teacher_id = new SimpleIntegerProperty(teacher.getTeacher_id());
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public IntegerProperty course_idProperty() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id.set(course_id);
    }

    public int getTeacher_id() {
        return teacher_id.get();
    }

    public IntegerProperty teacher_idProperty() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id.set(teacher_id);
    }

    public String getCourse_name() {
        return course_name.get();
    }

    public StringProperty course_nameProperty() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name.set(course_name);
    }

}
