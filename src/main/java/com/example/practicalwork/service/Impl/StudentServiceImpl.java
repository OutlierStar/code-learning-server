package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.StudentMapper;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public boolean StuInspectionLogin(String StuNo, String password) {


        Student student = getOneStu(StuNo);
        System.out.println(student.toString());
        if (student.getStudentPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Student getOneStu(String StuNo) {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        QueryWrapper<Student> no = qw.eq("student_no",StuNo);
        Student stu = studentMapper.selectOne(no);

        return stu;
    }
}
