package com.javafx_restfull.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private IntegerProperty student_id;
    private StringProperty student_name;
    private IntegerProperty student_age;

    public Student() {
        student_id = new SimpleIntegerProperty();
        student_name = new SimpleStringProperty();
        student_age = new SimpleIntegerProperty();
    }
    public Student(int student_id, String student_name, int student_age) {
        this.student_id = new SimpleIntegerProperty(student_id);
        this.student_name = new SimpleStringProperty(student_name);
        this.student_age = new SimpleIntegerProperty(student_age);
    }

    public int getStudent_id() {
        return student_id.get();
    }

    public IntegerProperty student_idProperty() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id.set(student_id);
    }

    public String getStudent_name() {
        return student_name.get();
    }

    public StringProperty student_nameProperty() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name.set(student_name);
    }

    public int getStudent_age() {
        return student_age.get();
    }

    public IntegerProperty student_ageProperty() {
        return student_age;
    }

    public void setStudent_age(int student_age) {
        this.student_age.set(student_age);
    }
}