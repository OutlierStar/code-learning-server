package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.AnswerMapper;
import com.example.practicalwork.Mapper.AnswerSetMapper;
import com.example.practicalwork.Mapper.QuestionMapper;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerSetMapper answerSetMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public boolean judgeFirstAns(Integer stuId, Integer setId) {
        Map<String,Object> map = new HashMap<>();
        map.put("student_id",stuId);
        map.put("set_id",setId);
        QueryWrapper<AnswerSet> queryWrapper = new QueryWrapper<>();
        QueryWrapper<AnswerSet> qwAnsSet = queryWrapper.allEq(map);

        return answerSetMapper.selectOne(qwAnsSet) == null ;
    }

    /*
          收集一份答卷
     */
    @Override
    public Msg getAnswerSet(AnswerSet answerSet) {
        Msg msg = new Msg();
        int count = answerSetMapper.insert(answerSet);
        if (count > 0) {
            msg.add("ansSet",answerSet);
        }else{
            msg.add("ansSet",null);
        }
        return msg;
    }

    /*
       收集每一份答卷的每个答案
    */
    @Override
    public Answer getAnswers(String oneAnswer, Integer answerSetId,Integer questionId) {

        Map<String,Object> map = new HashMap<>();
        map.put("answer_set_id",answerSetId);
        map.put("question_id",questionId);
        QueryWrapper<Answer> queryWrapperOne = new QueryWrapper<>();
        QueryWrapper<Answer> allAns = queryWrapperOne.allEq(map);
        Answer answer =  new Answer();
        answer.setAnswerSerId(answerSetId);
        answer.setQuestionId(questionId);
        answer.setAnswer(oneAnswer);
        answer.setIsAnswered(1);
        answer.setScore(null);
        answer.setIsRead(0);
        answerMapper.insert(answer);

        return answer;
    }

    @Override
    public AnswerSet getStuAnswerSet(Integer setId, Integer studentId) {
        Map<String,Object> map = new HashMap<>();
        map.put("set_id",setId);
        map.put("student_id",studentId);
        QueryWrapper<AnswerSet> queryWrapper = new QueryWrapper<>();
        return answerSetMapper.selectOne(queryWrapper.allEq(map));
    }

    /*
        批改答卷
     */
    @Override
    public Msg checkAnswers(Integer StuId, Integer setId) {

        Map<String,Object> map = new HashMap<>();
        map.put("student_id",StuId);
        map.put("set_id",setId);

        QueryWrapper<AnswerSet> queWrapperOne = new QueryWrapper<>();
        QueryWrapper<AnswerSet> set = queWrapperOne.allEq(map,true);
        AnswerSet answerSet = answerSetMapper.selectOne(set);

        QueryWrapper<Answer> queWrapperTwo = new QueryWrapper<>();
        QueryWrapper<Answer> ans = queWrapperTwo.eq("answer_set_id",answerSet.getAnswerSerId());
        List<Answer> answerList = answerMapper.selectList(ans);

        QueryWrapper<Question> queWrapperThree = new QueryWrapper<>();
        QueryWrapper<Question> standardAns = queWrapperThree.eq("set_id",setId);
        List<Question> questionList = questionMapper.selectList(standardAns);

        QueryWrapper<Answer> queWrapperFour = new QueryWrapper<>();

        QueryWrapper<Answer> queryWrapperFive = new QueryWrapper<>();

        List<Answer> ChoiceAnswerList = new ArrayList<>();
        List<Answer> CompletionAnswerList = new ArrayList<>();
        List<Answer> ShortAnswerList = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {
            Question question = questionList.get(i);
            QueryWrapper<Answer> typeAns = queryWrapperFive.eq("question_id",question.getId());
            Answer answer = answerMapper.selectOne(typeAns);

            if (question.getType().equals("选择题")){
                ChoiceAnswerList.add(answer);
            }else if (question.getType().equals("填空题")){
                CompletionAnswerList.add(answer);
            }else if (question.getType().equals("简答题")){
                ShortAnswerList.add(answer);
            }
        }

        checkChoiceAnswers(ChoiceAnswerList);
        checkCompletionAnswers(CompletionAnswerList);
        checkShortAnswers(ShortAnswerList);


        int sum = checkChoiceAnswers(ChoiceAnswerList)+
                checkCompletionAnswers(CompletionAnswerList)+
                checkShortAnswers(ShortAnswerList);
//        for (int i = 0; i < answerList.size(); i++){
//            Answer answer = answerList.get(i);
//            Question question = questionList.get(i);
//            if (answer.getAnswer().equals(question.getAnswer())){
//                answer.setScore(question.getScore());
//                QueryWrapper<Answer> updateScore = queWrapperFour.eq("question_id",answer.getQuestionId());
//                answerMapper.update(answer,updateScore);
//                sum+=question.getScore();
//            }else{
//                answer.setScore(0);
//                QueryWrapper<Answer> updateScore = queWrapperFour.eq("question_id",answer.getQuestionId());
//                answerMapper.update(answer,updateScore);
//            }
//       }

        /*
             批改完后给出总分数
         */
        answerSet.setScore(sum);
        answerSetMapper.update(answerSet,set);

        return null;
    }

    /*
       自动批改选择题
    */
    @Override
    public Integer checkChoiceAnswers(List<Answer> answerList) {
        if (answerList == null) return 0;
        int sum = 0;
        QueryWrapper<Question> queWrapperOne = new QueryWrapper<>();
        QueryWrapper<Answer> queWrapperTwo = new QueryWrapper<>();

        for (Answer answer : answerList){
            QueryWrapper<Question> standardAns = queWrapperOne.eq("id",answer.getQuestionId());
            Question question = questionMapper.selectOne(standardAns);
            Map<String,Object> map = new HashMap<>();

            if (answer.getIsAnswered()==1){
                if (answer.getAnswer().equals(question.getAnswer())){
                    answer.setScore(question.getScore());
                    answer.setIsRead(1);
                    map.put("question_id",answer.getQuestionId());
                    map.put("answer_set_id",answer.getAnswerSerId());
                    QueryWrapper<Answer> updateScore = queWrapperTwo.allEq(map);
                    answerMapper.update(answer,updateScore);
                    sum+=question.getScore();
                }
            }
        }

        return sum;
    }

    /*
           自动批改填空题
     */
    @Override
    public Integer checkCompletionAnswers(List<Answer> answerList) {

        if (answerList == null) return 0;
        int sum = 0;
        QueryWrapper<Question> queWrapperOne = new QueryWrapper<>();
        QueryWrapper<Answer> queWrapperTwo = new QueryWrapper<>();
        for(Answer answer : answerList){
            QueryWrapper<Question> standardAns = queWrapperOne.eq("id",answer.getQuestionId());
            Question question = questionMapper.selectOne(standardAns);
            Map<String,Object> map = new HashMap<>();

            if (answer.getIsAnswered() == 1){
                String[] stuAns = answer.getAnswer().split(",");
                String[] queAns = question.getAnswer().split(",");

                float OneComScore = question.getScore()/queAns.length;
                float AllComScore = 0;
                answer.setIsRead(1);
                for (int i = 0; i < queAns.length; i++){
                    if (stuAns[i].equals(queAns[i])){
                        AllComScore+=OneComScore;
                    }
                }
                answer.setScore(Math.round(AllComScore));
                map.put("question_id",answer.getQuestionId());
                map.put("answer_set_id",answer.getAnswerSerId());
                QueryWrapper<Answer> updateScore = queWrapperTwo.allEq(map);
                answerMapper.update(answer,updateScore);
                sum+=Math.round(AllComScore);
            }

        }

        return sum;
    }

    /*
           手动批改简答题
    */
    @Override
    public Integer checkShortAnswers(List<Answer> answerList) {
        int sum = 0;


        return sum;
    }

    /*
               教师判分
         */
    @Override
    public boolean Award(Integer answerId, Integer questionId, Integer score) {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("answer_set_id",answerId);
        queryWrapper.eq("question_id",questionId);
        Answer answer = answerMapper.selectOne(queryWrapper);
        answer.setScore(score);
        int count = answerMapper.update(answer,queryWrapper);
        return count >0;
    }
    /*
    算总分
     */
    @Override
    public boolean CountTotalScore(Integer answerSetId) {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("answer_set_id",answerSetId);
        List<Answer> answers = answerMapper.selectList(queryWrapper);
        int total = 0;
        for(Answer answer :answers){
            total += answer.getScore();
        }
        QueryWrapper<AnswerSet> wrapper = new QueryWrapper<>();
        wrapper.eq("answer_set_id",answerSetId);
        AnswerSet answerSet = answerSetMapper.selectOne(wrapper);
        answerSet.setScore(total);
        int count = answerSetMapper.update(answerSet,wrapper);
        return count>0;
    }
}
