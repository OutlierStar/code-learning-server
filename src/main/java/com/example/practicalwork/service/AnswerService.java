package com.example.practicalwork.service;

import com.example.practicalwork.model.Answer;
import com.example.practicalwork.model.AnswerSet;
import com.example.practicalwork.service.Impl.Msg;

import java.util.List;

public interface AnswerService {


    /*
        判断是否是第一次作答
     */
    boolean judgeFirstAns(Integer stuId,Integer setId);

    /*
        插入一份新的答卷
     */
    Msg getAnswerSet(AnswerSet answerSet);

    /*
        收集每一份答卷的每个答案
     */
    Answer getAnswers(String oneAnswer, Integer answerSetId,Integer questionId);

    /*
        批改所有题目
     */
    Msg checkAnswers(Integer StuNo, Integer setId);
    /*
        自动批改选这题
     */
    Integer checkChoiceAnswers(List<Answer> answerList);
    /*
        自动批改选择题
     */
    Integer checkCompletionAnswers(List<Answer> answerList);

    /*
        手动批改简答题
     */
    Integer checkShortAnswers(List<Answer> answerList);
}