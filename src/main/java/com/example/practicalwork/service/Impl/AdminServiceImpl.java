package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.practicalwork.Mapper.StudentMapper;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.model.Teacher;
import com.example.practicalwork.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentMapper studentMapper;
    @Override
    public boolean addTeacher(Teacher teacher) {
        int count = teacherMapper.insert(teacher);
        return count>0;
    }

    @Override
    public boolean addStudent(Student student) {
        int count=0;
        try {
            count = studentMapper.insert(student);
        }catch (Exception e){
            return count>0;
        }

        return count>0;
    }

    @Override
    public boolean updateStudent(Student student) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.eq("student_id",student.getStudentId());
        int count = studentMapper.update(student,wrapper);
        return count>0;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        UpdateWrapper<Teacher> wrapper = new UpdateWrapper<>();
        wrapper.eq("teacher_no",teacher.getTeacherNo());
        int count =  teacherMapper.update(teacher,wrapper);
        return count>0;
    }

    @Override
    public List<Student> searchStudentForId(Integer id) {
        QueryWrapper<Student> columnMap = new QueryWrapper<>();
        columnMap.eq("student_id",id);
        List<Student> students = studentMapper.selectList(columnMap);
        return students;

    }

    @Override
    public List<Student> searchStudentForName(String name) {
        QueryWrapper<Student> columnMap = new QueryWrapper<>();
        columnMap.eq("student_name",name);
        List<Student> students = studentMapper.selectList(columnMap);
        return students;
    }

    @Override
    public List<Student> searchStudentForClazz(String clazz) {
        QueryWrapper<Student> columnMap = new QueryWrapper<>();
        columnMap.eq("clazz_no",clazz);
        List<Student> students = studentMapper.selectList(columnMap);
        return students;

    }

    @Override
    public List<Teacher> searchTeacherForId(String id) {
        QueryWrapper<Teacher> columnMap = new QueryWrapper<>();
        columnMap.eq("teacher_no",id);
        List<Teacher> teachers = teacherMapper.selectList(columnMap);
        return teachers;
    }

    @Override
    public List<Teacher> searchTeacherForName(String name) {
        QueryWrapper<Teacher> columnMap = new QueryWrapper<>();
        columnMap.eq("teacher_name",name);
        List<Teacher> teachers = teacherMapper.selectList(columnMap);
        return teachers;
    }

    @Override
    public boolean deleteStudent(Student student) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("student_id",student.getStudentId());
        int count = studentMapper.delete(wrapper);
        return count>0;
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_no",teacher.getTeacherNo());
        int count = teacherMapper.delete(wrapper);
        return count>0;
    }


}
