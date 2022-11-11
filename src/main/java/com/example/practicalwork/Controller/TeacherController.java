package com.example.practicalwork.Controller;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.*;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    StudentService studentService;
    @Autowired
    QuestionService questionService;
    @Autowired
    AnswerService answerService;

    /*
        老师开设一门课程
     */
    @RequestMapping("/teaCreatOneCourse")
    public Msg teaCreatOneCourse(@RequestBody Course course){
        if (teacherService.creatCourseToClazz(course)) return Msg.success().add("course",course);
        return Msg.fail();
    }

    /*
        插入选课表
     */
    @RequestMapping("/addSelectCourse")
    private Msg IntoSelectCourse(@RequestParam("courseId") Integer courseId ,@RequestBody List<String> clazzNoList ){
        for (String clazzNo : clazzNoList){
            courseService.addSelectCourse(courseId,clazzNo);
        }
        return Msg.success().add("courseId",courseId).add("clazzNoList",clazzNoList);
    }

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

    /*
        老师插入向题目集插入题目
     */
    @RequestMapping(value = "/addQuestionIntoSet")
    public Msg addQuestionIntoSet(@RequestBody List<Question> questionList){
        return Msg.success().add("questionNum",questionService.addQuestion(questionList));
    }

    /*
        获取对应题目集所有学生的答卷列表
     */
    @RequestMapping(value = "/gerAllStuAnswerSet")
    public Msg gerAllStuAnswerSet(HttpServletRequest request, @RequestParam("courseId") Integer courseId){
        Teacher teacher = (Teacher) request.getSession().getAttribute("teacherInfo");
        List<List<Student>> clazzStuList = new ArrayList<>();
        List<Clazz> clazzList = studentService.getClazzNo(courseId);
        int i = 0;
        for (Clazz clazz : clazzList){
            for (int j = 0; j < clazz.getStudentNumber(); j++){

            }
//            clazzStuList.get(i).add()
        }

    /*
        获取题集的所有题目和学生答卷的所有答案
     */
    @RequestMapping(value = "/getStuAnswerSetInfo")
    public Msg getStuAnswerSetInfo(@RequestParam("setId") Integer setId,@RequestParam("answerSetId") Integer answerSetId){
        List<Question> questionList = questionService.getAllQuestions(setId);
        List<Answer> answerList = answerService.getStuAnsList(answerSetId);
        return Msg.success().add("questionList",questionList).add("answerList",answerList);
    }


    /*
        批改学生答卷
     */
    @RequestMapping(value = "/correctingStuAnswers")
    public Msg correctingStuAnswers(@RequestBody List<Integer> scoreList,@RequestParam("setId") Integer setId,@RequestParam("studentId") Integer studentId ){
        AnswerSet answerSet = answerService.checkAnswers(studentId,scoreList,setId);
        return Msg.success().add("stuAnsSet",answerSet);
    }



}
