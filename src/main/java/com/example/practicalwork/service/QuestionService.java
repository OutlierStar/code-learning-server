package com.example.practicalwork.service;

import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;

import java.util.List;

public interface QuestionService {
    //添加题目
    void addQuestion(int itemId, Question question);
    //创建题目集
    String creatQuestionSet(QuestionSet questionSet);
    //获取课程的已发布的题目集
    List<QuestionSet> getQueSets(Integer courseId);
    //获取当前题目集中的所有题目
    List<Question> getAllQuestions(Integer itemId);


}
