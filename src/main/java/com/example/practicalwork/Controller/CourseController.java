package com.example.practicalwork.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.CourseMapper;
import com.example.practicalwork.Mapper.QuestionMapper;
import com.example.practicalwork.Mapper.QuestionSetMapper;
import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.Course;
import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.Impl.CourseServiceImpl;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    CourseMapper courseMapper;

    /*

     */


    /*
            获取课程详情信息
    */
    @RequestMapping(value = "/IntoCourseInfo")
    public Msg IntoCourseInfo( @RequestParam int courseId){
        return Msg.success().add("course",courseMapper.selectOne(new QueryWrapper<Course>().eq("course_id",courseId)));
    }

    /*
        获取一个班的所有课程
     */
    @RequestMapping(value = "/student/getAllCourse")
    public Msg getAllCourse(@RequestParam ("clazzNo") String clazzNo){
        return Msg.success().add("courseList",courseService.queryCourse(clazzNo));
    }



}
