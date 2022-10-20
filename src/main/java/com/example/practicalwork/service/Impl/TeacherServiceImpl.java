package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.model.Teacher;
import com.example.practicalwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public boolean TeacherInspectionLogin(String TeacherNo, String password) {
        Teacher teacher = getTeacher(TeacherNo);
        System.out.println(teacher.toString());
        if (teacher.getTeacherPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Teacher getTeacher(String teacherNo) {
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        QueryWrapper<Teacher> no = qw.eq("teacher_no",teacherNo);
        Teacher teacher = teacherMapper.selectOne(no);
        return teacher;
    }
}
