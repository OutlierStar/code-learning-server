package com.example.practicalwork.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.Impl.QuestionServiceImpl;
import com.example.practicalwork.service.Impl.StudentServiceImpl;
import com.example.practicalwork.service.QuestionService;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /*
           老师发布一个作业
           传入一个习题集对象
     */
    @RequestMapping(value = "/creatQuestionSet")
    public String creatQuestionSet(@RequestBody QuestionSet questionSet){
        return questionService.creatQuestionSet(questionSet);
    }

    /*
            获取当前课程所有的题目集
     */
    @RequestMapping(value = "/showQuestionSet")
    public String showQuestionSet(@RequestParam("courseId") int courseId){
        List<QuestionSet> questionSetList = questionService.getQueSets(courseId);

        String flag = "获取失败";
        if (questionSetList != null) flag = "获取成功";

        ModelAndView mv = new ModelAndView();
        mv.addObject("questSetList",questionSetList);
        System.out.println(questionSetList);
        return flag;
    }

    
    /*
            获取当前题目集的所有题目
     */
    @RequestMapping(value = "/showAllQuestions")
    public String showAllQuestions(@RequestParam("itemId") int itemId){
        List<Question> questionList = questionService.getAllQuestions(itemId);

        String flag = "获取失败";
        if (questionList != null) flag = "获取成功";

        ModelAndView mv = new ModelAndView();
        mv.addObject("questionsList",questionList);
        System.out.println(questionList);

        return flag;
    }
}
