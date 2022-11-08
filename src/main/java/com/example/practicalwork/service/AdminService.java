package com.example.practicalwork.service;

import com.example.practicalwork.model.Student;
import com.example.practicalwork.model.Teacher;

import java.util.List;

public interface AdminService {
    boolean addTeacher(Teacher teacher);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean updateTeacher(Teacher teacher);
    List<Student>  searchStudentForId(Integer id);
    List<Student>  searchStudentForName(String name);
    List<Student>  searchStudentForClazz(String clazz);
    List<Teacher>  searchTeacherForId(String id);
    List<Teacher>  searchTeacherForName(String name);
    boolean deleteStudent(Student student);
    boolean deleteTeacher(Teacher teacher);
 }
