package com.example.practicalwork.service;

import com.example.practicalwork.model.AnswerSet;
import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.model.Teacher;

import java.util.List;

public interface TeacherService {
    boolean TeacherInspectionLogin(String TeacherNo, String password);
    Teacher getTeacher(String teacherNo);
    //创建题目集
    boolean creatQueSet(QuestionSet questionSet);
    //生成对应题目集学生的答卷
    List<AnswerSet> creatStuAnsSets(QuestionSet questionSet);
    //开设一门课程
    boolean creatCourseToClazz(Course course);
}
