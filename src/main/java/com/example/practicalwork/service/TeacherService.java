package com.example.practicalwork.service;

import com.example.practicalwork.model.Teacher;

public interface TeacherService {
    boolean TeacherInspectionLogin(String TeacherNo, String password);
    Teacher getTeacher(String teacherNo);
}
