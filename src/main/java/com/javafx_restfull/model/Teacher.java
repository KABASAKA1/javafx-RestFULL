package com.javafx_restfull.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher {
    private IntegerProperty teacher_id;
    private StringProperty teacher_name;
    private IntegerProperty teacher_age;

    public Teacher() {
        teacher_id = new SimpleIntegerProperty();
        teacher_name = new SimpleStringProperty();
        teacher_age = new SimpleIntegerProperty();
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

    public String getTeacher_name() {
        return teacher_name.get();
    }

    public StringProperty teacher_nameProperty() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name.set(teacher_name);
    }

    public int getTeacher_age() {
        return teacher_age.get();
    }

    public IntegerProperty teacher_ageProperty() {
        return teacher_age;
    }

    public void setTeacher_age(int teacher_age) {
        this.teacher_age.set(teacher_age);
    }
}
