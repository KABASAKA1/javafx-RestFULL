package com.javafx_restfull.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafx_restfull.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class StudentApiService implements IApiService<Student> {
    ApiService apiService = new ApiService();
    String url = "http://localhost:8080/students";

    CompletableFuture<Student> studentFuture;


    @Override
    public Student get_by_id(int id) {
        studentFuture = apiService.sendRequest(url+"/"+id, "GET" , null , new TypeReference<Student>() {});
        try {
            return studentFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public List<Student> get_all() {
        CompletableFuture<List<Student>> studentFuture = apiService.sendRequest(url, "GET" , null , new TypeReference<List<Student>>(){});
        try {
            return (List<Student>) studentFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void add(Student student) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data = new HashMap<>();
        data.put("student_id", student.getStudent_id());
        data.put("student_name", student.getStudent_name());
        data.put("student_age", student.getStudent_age());
        try {
            String json = objectMapper.writeValueAsString(data);
            studentFuture = apiService.sendRequest(url, "POST", json, new TypeReference<Student>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {

    }
}

