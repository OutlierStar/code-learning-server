package com.example.practicalwork.service;

import com.example.practicalwork.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> queryCourse(String clazzNO);
    void addCourse(Course course);
}
