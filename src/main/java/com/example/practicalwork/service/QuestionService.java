package com.example.practicalwork.service;

import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.Impl.Msg;

import java.util.List;

public interface QuestionService {
    //添加题目
    Integer addQuestion(List<Question> questionList);
    //获取课程的已发布的题目集
    List<QuestionSet> getQueSets(Integer courseId);
    //获取当前题目集中的所有题目
    List<Question> getAllQuestions(Integer itemId);

}
