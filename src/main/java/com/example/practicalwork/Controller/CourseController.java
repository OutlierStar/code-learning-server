package com.example.practicalwork.Controller;

import com.example.practicalwork.model.Course;
import com.example.practicalwork.service.Impl.CourseServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {
//    @RequestMapping("/selectCourse")
//    public List<Course> selectCourse(HttpServletRequest request, @RequestParam("classNO")String classNo){
//        CourseServiceImpl service = new CourseServiceImpl();
//        Course course = service.queryCourse(classNo);
//        return course;
//    }


}
