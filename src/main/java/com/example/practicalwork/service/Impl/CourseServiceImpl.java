package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.CourseMapper;
import com.example.practicalwork.Mapper.SelectCourseMapper;
import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.SelectCourse;
import com.example.practicalwork.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private SelectCourseMapper selectCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course queryCourse(String clazzNO) {
        QueryWrapper<SelectCourse> qSelCourse = new QueryWrapper<>();
        QueryWrapper<SelectCourse> selCourse = qSelCourse.eq("clazz_no",clazzNO);
        SelectCourse selectCourse = selectCourseMapper.selectOne(selCourse);

        QueryWrapper<Course> qCourse = new QueryWrapper<>();
        QueryWrapper<Course> getCourse = qCourse.eq("course_id",selectCourse.getCourseId());
        Course course = courseMapper.selectOne(getCourse);

        return course;
    }
}
