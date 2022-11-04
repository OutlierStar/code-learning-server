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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {


    /*
            获取课程详情信息
    */
    @RequestMapping(value = "/IntoCourseInfo")
    public Msg IntoCourseInfo( @RequestBody Course course){
        ModelAndView mv = new ModelAndView();
        mv.addObject("CourseInfo",course);
        return Msg.success().add("Course",course);
    }


}
