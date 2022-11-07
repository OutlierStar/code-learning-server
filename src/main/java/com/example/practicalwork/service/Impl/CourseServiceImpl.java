package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.CourseMapper;
import com.example.practicalwork.Mapper.SelectCourseMapper;
import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.SelectCourse;
import com.example.practicalwork.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private SelectCourseMapper selectCourseMapper;

    @Autowired
    private CourseMapper courseMapper;


    /*
        获取一个班的所有课程
     */
    @Override
    public List<Course> queryCourse(String clazzNO) {
        List<Course> courseList = new ArrayList<>();
        QueryWrapper<SelectCourse> qSelCourse = new QueryWrapper<>();
        QueryWrapper<SelectCourse> selCourse = qSelCourse.eq("clazz_no",clazzNO);
        List<SelectCourse> selectCourses = selectCourseMapper.selectList(selCourse);
        System.out.println(selCourse.toString());

        QueryWrapper<Course> qCourse = new QueryWrapper<>();
        for (SelectCourse selectCourse : selectCourses){
            QueryWrapper<Course> getCourse = qCourse.eq("course_id",selectCourse.getCourseId());
            Course course = courseMapper.selectOne(getCourse);
            courseList.add(course);
        }
        return courseList;
    }

    /*
        获取当前老师所授全部课程
     */
    @Override
    public List<Course> getTeacherCourse(String teacherNo){
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        return courseMapper.selectList(queryWrapper.eq("teacher_no",teacherNo));
    }


    @Override
    public void addCourse(Course course) {
        int count = courseMapper.insert(course);
        System.out.println("插入成功");
    }


}
