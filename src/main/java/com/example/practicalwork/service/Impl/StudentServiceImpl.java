package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.ClazzMapper;
import com.example.practicalwork.Mapper.QuestionSetMapper;
import com.example.practicalwork.Mapper.SelectCourseMapper;
import com.example.practicalwork.Mapper.StudentMapper;
import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.SelectCourse;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    QuestionSetMapper questionSetMapper;

    @Autowired
    ClazzMapper clazzMapper;

    @Autowired
    SelectCourseMapper selectCourseMapper;
    /*
        判断学生用户是否登录成功
     */
    @Override
    public boolean StuInspectionLogin(String StuNo, String password) {
        Student student = getOneStu(StuNo);
        return student.getStudentPassword().equals(password);
    }

    /*
        通过学号查找学生信息
     */
    @Override
    public Student getOneStu(String StuNo) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        QueryWrapper<Student> no = qw.eq("student_no",StuNo);
        return studentMapper.selectOne(no);
    }

    /*
        获取学生用户的班级信息
     */
    @Override
    public Clazz getClazzInfo(String StuNo) {
        QueryWrapper<Clazz> qw = new QueryWrapper<>();
        QueryWrapper<Clazz> clazzQw = qw.eq("student_no",StuNo);
        return clazzMapper.selectOne(clazzQw);
    }

    /*
        通过课程号获取选择该课程的班级列表
     */
    @Override
    public List<SelectCourse> getClazzNo(Integer courseId) {
        QueryWrapper<SelectCourse> queryWrapper = new QueryWrapper<>();
        return selectCourseMapper.selectList(queryWrapper.eq("course_id",courseId));
    }

    @Override
    public List<Student> getStus(String clazzNo) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("clazz_no",clazzNo);
        List<Student> studentList = studentMapper.selectList(queryWrapper);
        return studentList;
    }


}
