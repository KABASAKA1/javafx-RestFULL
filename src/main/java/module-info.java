module com.javafx_restfull {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.javafx_restfull.model to javafx.base;
    opens com.javafx_restfull to javafx.fxml;
    opens com.javafx_restfull.controllers to javafx.fxml;
    exports com.javafx_restfull;
    exports com.javafx_restfull.controllers;
    exports com.javafx_restfull.model;
    exports com.javafx_restfull.services;
}