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
    public Msg addQuestion(List<Question> questionList) {

        for (Question question : questionList){
            int count = questionMapper.insert(question);
            if (count <= 0) {
                return Msg.fail();
            }
        }
        return Msg.success();
    }


    /*
        通过课程id和班级号获得获取该课程发布的所有题目集
     */
    @Override
    public List<QuestionSet> getQueSets(Integer courseId,String clazzNo) {
        Map<String,Object> map = new HashMap<>();
        map.put("course_id",courseId);
        map.put("clazz_no",clazzNo);

        QueryWrapper<QuestionSet> qw = new QueryWrapper<>();
        QueryWrapper<QuestionSet> qwSet = qw.allEq(map);
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
        QueryWrapper<Question> queryQuestion = queryWrapper.eq("set_id",setId);

        return questionMapper.selectList(queryQuestion);
    }


}
