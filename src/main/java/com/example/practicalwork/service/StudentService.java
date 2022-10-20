package com.example.practicalwork.service;

import com.example.practicalwork.model.Student;

public interface StudentService {
    boolean StuInspectionLogin(String StuNo, String password);
    Student getOneStu(String StuNo);
}