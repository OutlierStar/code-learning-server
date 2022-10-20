package com.example.practicalwork.Controller;

import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.service.CourseService;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    CourseService courseService;
    @RequestMapping(value = "/stuLogin")
    public ModelAndView Loginto(@RequestParam("studentNo")String studentNo,@RequestParam("studentPassword") String studentPassword){
        ModelAndView mv = new ModelAndView();
        if (studentService.StuInspectionLogin(studentNo,studentPassword)){
            Student student = studentService.getOneStu(studentNo);
            System.out.println(student.toString());
            mv.addObject("stu",student);
            Course course = courseService.queryCourse(student.getClazzNo());
            System.out.println(course.toString());
            mv.addObject("course",course);
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
        return mv;
    }

}
