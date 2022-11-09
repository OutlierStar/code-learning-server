package com.example.practicalwork.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.ClazzMapper;
import com.example.practicalwork.Mapper.SelectCourseMapper;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.CourseService;
import com.example.practicalwork.service.Impl.Msg;
import com.example.practicalwork.service.StudentService;
import com.example.practicalwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    SelectCourseMapper selectCourseMapper;

    @Autowired
    ClazzMapper clazzMapper;

    /*
        获取学生主页展示信息
     */
    @RequestMapping(value = "/index/login")
    public Msg Loginto(HttpServletRequest request,
                       @RequestParam("account")String account,
                       @RequestParam("password") String password,
                       @RequestParam("type") Integer type){
        ModelAndView mv = new ModelAndView();
        //type==1 学生用户登录
        if (type == 1){
            if (studentService.StuInspectionLogin(account,password)){
                //当前学生用户信息
                Student student = studentService.getOneStu(account);
                System.out.println(student.toString());
                request.getSession().setAttribute("stuInfo",student);
                mv.addObject("stu",student);
                // 拿到当前用户所有的课程信息
                request.getSession().setAttribute("clazzNo",student.getClazzNo());
                List<Course> courseList = courseService.queryCourse(student.getClazzNo());
                mv.addObject("course",courseList);
                System.out.println(courseList.toString());
                return Msg.success().add("student",student).add("course",courseList);
            }else{
                return Msg.fail().add("student",null);
            }
        }else if (type == 2){//type==2 教职工用户登录
            Teacher teacher = teacherService.getTeacher(account);
            List<Course> courseList = courseService.getTeacherCourse(account);
            request.getSession().setAttribute("teacherInfo",teacher);
            mv.addObject("teacherInfo",teacher);
            mv.addObject("courseList",courseList);
            if (teacherService.TeacherInspectionLogin(account,password))
                return Msg.success().add("teacher",teacher).add("courseList",courseList);
            return Msg.fail();
        }
        return Msg.fail();
    }

    /*
         学生个人资料基本信息
     */
    @RequestMapping(value = "/student/get")
    public Msg StuInfo(HttpServletRequest request){
           Student student = (Student) request.getSession().getAttribute("stuInfo");
           ModelAndView mv = new ModelAndView();
           mv.addObject("studentInfo",student);
           return Msg.success().add("student",student);
    }



    /**
     *插入选课表
     *
     * @author wdx
     */
    @RequestMapping(value = "/addSelectCourse")
    public Msg addSelectCourse(@RequestBody List<SelectCourse> selectCourseList){
        Msg msg = Msg.fail().add("error","插入失败");
        int count = 0;
        int flag = 0;
        if (selectCourseList==null){
            return msg;
        }
        for (SelectCourse selectCourse : selectCourseList){
            QueryWrapper<SelectCourse> sc = new QueryWrapper<>(selectCourse);
            if (selectCourseMapper.exists(sc)){
                flag++;
            }else{
                count+= selectCourseMapper.insert(selectCourse);
            }
        }
        if (flag>0){
            msg.add("error","包含"+flag+"条重复插入");
        }
        if(count>0){
            msg.add("result","包含"+count+"条成功插入");
            msg.besuccess();
        }
        return msg;
    }


}
