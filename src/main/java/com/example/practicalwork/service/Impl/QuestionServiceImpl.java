package com.example.practicalwork.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.QuestionMapper;
import com.example.practicalwork.Mapper.QuestionSetMapper;
import com.example.practicalwork.model.Question;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionSetMapper questionSetMapper;

    @Autowired
    QuestionMapper questionMapper;

    /*
        插入一个题目到当前创建的题目集
     */
    @Override
    public void addQuestion(int itemId, Question question) {
        QueryWrapper<QuestionSet> querySet = new QueryWrapper<>();
        QueryWrapper<QuestionSet> getSet = querySet.eq("item_id",itemId);

        QuestionSet questionSet = questionSetMapper.selectOne(getSet);
        question.setItemId(questionSet.getItemId());
        int count = questionMapper.insert(question);
    }


    @Override
    public String creatQuestionSet(QuestionSet queSet) {
        System.out.println(queSet.toString());
        int count = questionSetMapper.insert(queSet);
        if (count > 0){
            return "添加成功";
        }return "添加失败";
    }


    @Override
    public List<QuestionSet> getQueSets(Integer courseId) {
        QueryWrapper<QuestionSet> queryWrapper = new QueryWrapper();
        QueryWrapper<QuestionSet> querySet = queryWrapper.eq("course_id",courseId);
        List<QuestionSet> questionSetList = questionSetMapper.selectList(querySet);
        return questionSetList;
    }

    @Override
    public List<Question> getAllQuestions(Integer itemId) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Question> queryQuestion = queryWrapper.eq("item_id",itemId);
        List<Question> questionList = questionMapper.selectList(queryQuestion);
        return questionList;
    }
}
