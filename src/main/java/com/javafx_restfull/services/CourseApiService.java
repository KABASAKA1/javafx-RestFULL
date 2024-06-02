package com.javafx_restfull.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafx_restfull.model.Course;
import com.javafx_restfull.model.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CourseApiService implements IApiService<Course> {
    private final ApiService apiService = new ApiService();
    private CompletableFuture<Course> courseFuture;
    private final String url = "http://localhost:8080/courses";

    @Override
    public Course get_by_id(int id) {
        courseFuture = apiService.sendRequest(url+"/"+id, "GET" , null , new TypeReference<Course>() {});
        try {
            return courseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    @Override
    public List<Course> get_all() {
        CompletableFuture<List<Course>> courseFuture = apiService.sendRequest(url, "GET" , null , new TypeReference<List<Course>>(){});
        try {
            return (List<Course>) courseFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Course course) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> data = new HashMap<>();
        data.put("course_id", course.getCourse_id());
        data.put("course_name", course.getCourse_name());
        data.put("teacher_id", course.getTeacher_id());
        try {
            String json = objectMapper.writeValueAsString(data);
            courseFuture = apiService.sendRequest(url, "POST", json, new TypeReference<Course>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }
}
