package com.example.practicalwork.service;

import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.model.Student;

public interface StudentService {
    //学生登录
    boolean StuInspectionLogin(String StuNo, String password);
    //获取登录学生对象信息
    Student getOneStu(String StuNo);
}