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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

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
    public Msg IntoQuestionSetInfo(HttpServletRequest request, @RequestParam("courseId") Integer courseId){
        String clazzNO = (String) request.getSession().getAttribute("clazzNo");
        List<QuestionSet> questionSetList = questionService.getQueSets(courseId, clazzNO);

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

        Student student = (Student) request.getSession().getAttribute("stu");

        ModelAndView mv = new ModelAndView();
        mv.addObject("questionsList",questionList);

//        if (answerService.judgeFirstAns(student.getStudentId(),setId)){
//            Date date = new Date();
//            AnswerSet answerSet = new AnswerSet();
//            answerSet.setAnswerSerId(0);
//            answerSet.setSetId(setId);
//            answerSet.setStudentId(student.getStudentId());
//            answerSet.setAnswerTime(date);
//            answerSet.setSubmitTime(null);
//            answerSet.setScore(null);
//            answerSet.setIsAnswered(0);
//            answerSet.setIsSubmit(0);
//            answerService.getAnswerSet(answerSet);
//            request.getSession().setAttribute("ansSet",answerSet);
//        }else{
//            request.getSession().setAttribute("ansSet",answerService.judgeFirstAns(student.getStudentId(),setId));
//        }
        return Msg.success().add("questions",questionList);
    }

    /*
        获取学生提交的答卷
     */
    @RequestMapping(value = "/creatStuAnsSet")
    public Msg getStuAnsSet(HttpServletRequest request,List<String> StuAnswers){
        AnswerSet answerSet = (AnswerSet) request.getSession().getAttribute("ansSet");
        List<Question> questionList = questionService.getAllQuestions(answerSet.getSetId());
        Msg msg = new Msg();
        int i = 0;
        for (String answer : StuAnswers){
            msg.add(answer,answerService.getAnswers(answer,
                    answerSet.getAnswerSerId(),questionList.get(i).getId()));
            i++;
        }
        return Msg.success().add("msg",msg);
    }

}
