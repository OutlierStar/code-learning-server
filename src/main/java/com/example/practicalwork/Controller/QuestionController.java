package com.example.practicalwork.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.AnswerSetMapper;
import com.example.practicalwork.Mapper.QuestionMapper;
import com.example.practicalwork.Mapper.QuestionSetMapper;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.Impl.AnswerServiceImpl;
import com.example.practicalwork.service.Impl.Msg;
import com.example.practicalwork.service.Impl.QuestionServiceImpl;
import com.example.practicalwork.service.Impl.StudentServiceImpl;
import com.example.practicalwork.service.QuestionService;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;
@CrossOrigin
@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerServiceImpl answerService;



    /*
        获取当前课程的作业列表
     */
    @RequestMapping(value = "/IntoQuestionSetInfo")
    public Msg IntoQuestionSetInfo( @RequestParam("courseId") Integer courseId){
        List<QuestionSet> questionSetList = questionService.getQueSets(courseId);
        for(QuestionSet questionSet: questionSetList){
            System.out.println(questionSet);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("questSetList",questionSetList);
        return Msg.success().add("questionSets",questionSetList);
    }


    /*
            获取当前题目集的所有题目
     */
    @RequestMapping(value = "/showAllQuestions")
    public Msg showAllQuestions(HttpServletRequest request,@RequestParam("setId") int setId){
        List<Question> questionList = questionService.getAllQuestions(setId);

        request.getSession().setAttribute("setId",setId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("questionsList",questionList);

        return Msg.success().add("questions",questionList);
    }

    /*
        将学生提交的答案插入答案表
     */
    @RequestMapping(value = "/creatStuAnsSet")
    public Msg getStuAnsSet(HttpServletRequest request,@RequestBody List<String> StuAnswers,
                            @RequestParam("setId") Integer setId,@RequestParam("studentId") Integer studentId){
//        Integer setId = (Integer) request.getSession().getAttribute("setId");

        System.out.println(setId);
        System.out.println(studentId);
        List<Question> questionList = questionService.getAllQuestions(setId);
//        Student student = (Student) request.getSession().getAttribute("stuInfo");
        AnswerSet answerSet = answerService.getStuAnswerSet(setId,studentId);
        Msg msg = new Msg();
        int i = 0;
        for (String answer : StuAnswers){
            msg.add(answer,answerService.getAnswers(answer,
                    answerSet.getAnswerSetId(),questionList.get(i).getId()));
            i++;
        }
        return Msg.success().add("msg",msg);
    }

}
