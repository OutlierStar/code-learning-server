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
        List<SelectCourse> selectCourseList = selectCourseMapper.selectList(qSelCourse.eq("clazz_no",clazzNO));

        for (SelectCourse selectcourse : selectCourseList){
            QueryWrapper<Course> qCourse = new QueryWrapper<>();
            Course course = courseMapper.selectOne(qCourse.eq("course_id",selectcourse.getCourseId()));
            System.out.println(course);
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

    /*
        添加课程
     */
    @Override
    public boolean addCourse(Course course) {
        int count = courseMapper.insert(course);;
        return count > 0;
    }
    /*
        插入选课表
     */
    @Override
    public boolean addSelectCourse(Integer courseId, String clazzNo) {
        SelectCourse selectCourse = new SelectCourse();
        selectCourse.setCourseId(courseId);
        selectCourse.setClazzNo(clazzNo);
        int count = selectCourseMapper.insert(selectCourse);
        return count > 0;
    }


}