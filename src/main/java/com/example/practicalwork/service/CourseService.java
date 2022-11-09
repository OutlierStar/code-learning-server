package com.example.practicalwork.service;

import com.example.practicalwork.model.Course;

import java.util.List;

public interface CourseService {
    //学生获取当前班级的全部课程
    List<Course> queryCourse(String clazzNO);
    //老师添加课程
    void addCourse(Course course);
    //获取老师所授课程
    List<Course> getTeacherCourse(String teacherNo);


}
