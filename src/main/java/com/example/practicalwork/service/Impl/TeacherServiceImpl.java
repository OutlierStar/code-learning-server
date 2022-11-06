package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.*;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private QuestionSetMapper questionSetMapper;

    @Autowired
    private AnswerSetMapper answerSetMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private CourseMapper courseMapper;
   /*
        判断老师用户是否登录成功
    */
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

    /*
       获取老师的个人信息
    */
    @Override
    public Teacher getTeacher(String teacherNo) {
        QueryWrapper<Teacher> qw = new QueryWrapper<>();
        QueryWrapper<Teacher> no = qw.eq("teacher_no",teacherNo);
        return teacherMapper.selectOne(no);
    }

    /*

     */


    /*
        创建一个题目集
     */
    @Override
    public boolean creatQueSet(QuestionSet queSet) {
        System.out.println(queSet.toString());
        int count = questionSetMapper.insert(queSet);
        return count > 0;
    }


    /*
        生成对应题目集学生的答卷
     */
    @Override
    public List<AnswerSet> creatStuAnsSets(Integer setId, String clazzNo) {
       QueryWrapper<Student> queryWrapperOne = new QueryWrapper<>();
       QueryWrapper<Student> queStudents = queryWrapperOne.eq("clazz_no",clazzNo);
       List<Student> studentList = studentMapper.selectList(queStudents);

       List<AnswerSet> arrayList = new ArrayList<>();

       int count = 0;
       for (Student student : studentList){
           AnswerSet answerSet = new AnswerSet();
           answerSet.setAnswerSerId(0);
           answerSet.setSetId(setId);
           answerSet.setStudentId(student.getStudentId());
           Date date = new Date();
           answerSet.setSubmitTime(null);
           answerSet.setAnswerTime(date);
           answerSet.setScore(null);
           answerSet.setIsSubmit(null);
           answerSet.setIsRead(0);
           answerSetMapper.insert(answerSet);
           arrayList.add(answerSet);
       }
        return arrayList;
    }

    /*
        老师开设一门课程
     */
    @Override
    public boolean creatCourseToClazz(Course course) {
        return courseMapper.insert(course)>0;
    }


}

