package com.example.practicalwork.Controller;

import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.service.CourseService;
import com.example.practicalwork.service.Impl.Msg;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    /*
        获取学生主页展示信息
     */
    @RequestMapping(value = "/index/login")
    public Msg Loginto(HttpServletRequest request, @RequestParam("studentNo")String studentNo, @RequestParam("studentPassword") String studentPassword){
        ModelAndView mv = new ModelAndView();

        if (studentService.StuInspectionLogin(studentNo,studentPassword)){
            //当前学生用户信息
            Student student = studentService.getOneStu(studentNo);
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
    }

    /*
         学生个人资料基本信息
     */
    @RequestMapping(value = "/student/get")
    public Msg StuInfo(HttpServletRequest request){
           Student student = (Student) request.getSession().getAttribute("stuInfo");
           String ans = "获取失败";
           if (student != null){
               ans = "获取成功，芜湖";
           }
           ModelAndView mv = new ModelAndView();
           mv.addObject("studentInfo",student);
           return Msg.success().add("student",student);
    }


}
