package com.example.practicalwork.Controller;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.AnswerSet;
import com.example.practicalwork.model.QuestionSet;
import com.example.practicalwork.service.Impl.Msg;
import com.example.practicalwork.service.Impl.QuestionServiceImpl;
import com.example.practicalwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /*
          老师在课程中创建题目集并且给班级中的每一个学生创建一份答卷
     */
    @RequestMapping(value = "/creatQuestionSet")
    public Msg creatQuestionSet(@RequestBody QuestionSet questionSet){
        if (teacherService.creatQueSet(questionSet)){
            List<AnswerSet> stuSets = teacherService.creatStuAnsSets(questionSet.getSetId(), questionSet.getClazzNo());
            return Msg.success().add("stuSets",stuSets);
        }else{
            return Msg.fail();
        }
    }
}
