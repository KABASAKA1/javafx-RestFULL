package com.javafx_restfull.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafx_restfull.model.Student;
import com.javafx_restfull.model.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TeacherApiService implements IApiService<Teacher> {
    private final ApiService apiService = new ApiService();
    private final String url = "http://localhost:8080/teachers";
    private CompletableFuture<Teacher> teacherFuture;


    @Override
    public Teacher get_by_id(int id) {
        teacherFuture = apiService.sendRequest(url+"/"+id, "GET" , null , new TypeReference<Teacher>() {});
        try {
            return teacherFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Teacher> get_all() {
        CompletableFuture<List<Teacher>> teacherFuture = apiService.sendRequest(url, "GET" , null , new TypeReference<List<Teacher>>(){});
        try {
            return (List<Teacher>) teacherFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Teacher teacher) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data = new HashMap<>();
        data.put("teacher_id", teacher.getTeacher_id());
        data.put("teacher_name", teacher.getTeacher_name());
        data.put("teacher_age", teacher.getTeacher_age());
        try {
            String json = objectMapper.writeValueAsString(data);
            teacherFuture = apiService.sendRequest(url, "POST", json, new TypeReference<Teacher>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }
}
