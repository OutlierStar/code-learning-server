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
    public boolean InspectionLogin(Integer account, String password) {

        QueryWrapper<Student> qw = new QueryWrapper<>();
        QueryWrapper<Student> no = qw.eq("student_no",account);

        Student student = studentMapper.selectOne(no);
        System.out.println(student.toString());
        if (student.getStudentPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
