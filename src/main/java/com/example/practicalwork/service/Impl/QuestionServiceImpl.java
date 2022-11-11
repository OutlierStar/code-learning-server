package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.QuestionMapper;
import com.example.practicalwork.Mapper.QuestionSetMapper;
import com.example.practicalwork.model.Answer;
import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionSetMapper questionSetMapper;

    @Autowired
    QuestionMapper questionMapper;


    /*
        插入题目集中的所有题目
     */
    @Override
    public Integer addQuestion(List<Question> questionList) {
        int count = 0;
        for (Question question : questionList){
             count+= questionMapper.insert(question);
        }
        return count;
    }


    /*
        通过课程id和班级号获得获取该课程发布的所有题目集
     */
    @Override
    public List<QuestionSet> getQueSets(Integer courseId) {
        QueryWrapper<QuestionSet> qw = new QueryWrapper<>();
        QueryWrapper<QuestionSet> qwSet = qw.eq("course_id",courseId);
        List<QuestionSet> questionSets =  questionSetMapper.selectList(qwSet);

        ModelAndView mv = new ModelAndView();
        mv.addObject("questionSets",questionSets);

        return questionSets;
    }


    /*
        通过题目集id获取该题目集中包含的所有题目
     */
    @Override
    public List<Question> getAllQuestions(Integer setId) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("set_id",setId);
        return questionMapper.selectList(queryWrapper);
    }


}
