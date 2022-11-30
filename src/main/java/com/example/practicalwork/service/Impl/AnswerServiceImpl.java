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
        Answer answer =  new Answer();
        answer.setAnswerSerId(answerSetId);
        answer.setQuestionId(questionId);
        answer.setAnswer(oneAnswer);
        answer.setIsAnswered(1);
        answer.setScore(0);
        answer.setIsRead(0);
        answerMapper.insert(answer);
        return answer;
    }

    /*
        获取学生的答卷信息
     */
    @Override
    public AnswerSet getStuAnswerSet(Integer setId,Integer studentId) {
        QueryWrapper<AnswerSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("set_id",setId);
        queryWrapper.eq("student_id",studentId);
        return answerSetMapper.selectOne(queryWrapper);
    }

    /*
        获取学生答卷中的所有答案
     */
    @Override
    public List<Answer> getStuAnsList(Integer answerSetId) {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("answer_set_id",answerSetId);
        return answerMapper.selectList(queryWrapper);
    }


    /*
        批改答卷
     */
    @Override
    public AnswerSet checkAnswers(Integer StuId, List<Integer> scoreList,Integer setId) {

        Map<String,Object> map = new HashMap<>();
        map.put("student_id",StuId);
        map.put("set_id",setId);

        QueryWrapper<AnswerSet> queWrapperOne = new QueryWrapper<>();
        QueryWrapper<AnswerSet> set = queWrapperOne.allEq(map,true);
        AnswerSet answerSet = answerSetMapper.selectOne(set);

        QueryWrapper<Answer> queWrapperTwo = new QueryWrapper<>();
        List<Answer> answerList = answerMapper.selectList(queWrapperTwo.eq("answer_set_id",answerSet.getAnswerSetId()));

        QueryWrapper<Question> queWrapperThree = new QueryWrapper<>();
        List<Question> questionList = questionMapper.selectList(queWrapperThree.eq("set_id",setId));

        List<Answer> ChoiceAnswerList = new ArrayList<>();
        List<Answer> CompletionAnswerList = new ArrayList<>();
        List<Answer> ShortAnswerList = new ArrayList<>();
        List<Answer> ProQueAnswerList = new ArrayList<>();

        System.out.println("questionList:"+questionList);

        for (Question question : questionList) {
            QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("question_id", question.getId());
            queryWrapper.eq("answer_set_id", answerSet.getAnswerSetId());
            Answer answer = answerMapper.selectOne(queryWrapper);
            switch (question.getType()) {
                case "选择题":
                    ChoiceAnswerList.add(answer);
                    break;
                case "填空题":
                    CompletionAnswerList.add(answer);
                    break;
                case "简答题":
                case "编程题":
                    ShortAnswerList.add(answer);
                    break;
            }
        }

        System.out.println("ChoiceAnswerList:"+ChoiceAnswerList);
        System.out.println("CompletionAnswerList:"+CompletionAnswerList);
        System.out.println("ShortAnswerList:"+ShortAnswerList);
        System.out.println("scoreList:"+scoreList);

        System.out.println(checkChoiceAnswers(ChoiceAnswerList));
        System.out.println(checkCompletionAnswers(CompletionAnswerList));
        System.out.println(checkShortAnswers(ShortAnswerList,scoreList));


        int sum = checkChoiceAnswers(ChoiceAnswerList)+
                checkCompletionAnswers(CompletionAnswerList)+
                checkShortAnswers(ShortAnswerList,scoreList);

        // 批改完后给出总分数
        answerSet.setScore(sum);
        answerSet.setIsRead(1);
        answerSetMapper.update(answerSet,set);

        return answerSet;
    }

    /*
       自动批改选择题
    */
    @Override
    public Integer checkChoiceAnswers(List<Answer> answerList) {
        if (answerList == null) return 0;
        int sum = 0;

        for (Answer answer : answerList){
            System.out.println("选择题");
            System.out.println(answer);
            QueryWrapper<Question> queWrapperOne = new QueryWrapper<>();

            int id= answer.getQuestionId();

            Question question = questionMapper.selectOne(queWrapperOne.eq("id",id));
            if (answer.getIsAnswered()==1){
                if (answer.getAnswer().equals(question.getAnswer())){
                    answer.setScore(question.getScore());
                    answer.setIsRead(1);
                    QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("question_id",answer.getQuestionId());
                    queryWrapper.eq("answer_set_id",answer.getAnswerSerId());
                    answerMapper.update(answer,queryWrapper);
                    sum+=question.getScore();
                }else{
                    answer.setScore(0);
                    answer.setIsRead(1);
                    QueryWrapper<Answer> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("question_id",answer.getQuestionId());
                    queryWrapper.eq("answer_set_id",answer.getAnswerSerId());
                    answerMapper.update(answer,queryWrapper);
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
            Question question = questionMapper.selectOne(queWrapperOne.eq("id",answer.getQuestionId()));
            Map<String,Object> map = new HashMap<>();

            if (answer.getIsAnswered() == 1){
                String[] stuAns = answer.getAnswer().split(",");
                String[] queAns = question.getAnswer().split(",");
                float AllComScore = 0;
                answer.setIsRead(1);
                if (queAns.length > 1){
                    double num = question.getScore() * 1.0;
                    double OneComScore = num/queAns.length;
                    System.out.println(OneComScore);
                    for (int i = 0; i < queAns.length; i++){
                        if (stuAns[i].equals(queAns[i])){
                            AllComScore+=OneComScore;
                        }
                    }
                }else{
                    AllComScore = question.getScore();
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
    public Integer checkShortAnswers(List<Answer> answerList,List<Integer> scoreList) {
        if (answerList == null) return 0;
        int sum = 0;
        int j = 0;

        for(Answer answer : answerList) {
            QueryWrapper<Answer> queWrapperTwo = new QueryWrapper<>();
            queWrapperTwo.eq("question_id", answer.getQuestionId());
            queWrapperTwo.eq("answer_set_id", answer.getAnswerSerId());
            if (answer.getIsAnswered() == 1) {
                answer.setIsRead(1);
                answer.setScore(scoreList.get(j));
                answerMapper.update(answer, queWrapperTwo);
                sum += scoreList.get(j);
                System.out.println("-----"+sum);
            }
            j++;
        }
        return sum;
    }

}
