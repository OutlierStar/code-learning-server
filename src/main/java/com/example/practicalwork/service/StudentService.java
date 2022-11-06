package com.example.practicalwork.service;

import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.model.Student;

import java.util.List;

public interface StudentService {
    //学生登录
    boolean StuInspectionLogin(String StuNo, String password);
    //获取登录学生对象信息
    Student getOneStu(String StuNo);
    //获取学生所在班级信息
    Clazz getClazzInfo(String StuNo);
    //通过课程号获取选择该课程的班级列表
    List<Clazz> getClazzNo(Integer courseId);

}