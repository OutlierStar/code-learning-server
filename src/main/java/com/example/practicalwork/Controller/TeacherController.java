package com.example.practicalwork.Controller;
import com.example.practicalwork.Mapper.TeacherMapper;
import com.example.practicalwork.model.*;
import com.example.practicalwork.service.CourseService;
import com.example.practicalwork.service.Impl.Msg;
import com.example.practicalwork.service.Impl.QuestionServiceImpl;
import com.example.practicalwork.service.QuestionService;
import com.example.practicalwork.service.StudentService;
import com.example.practicalwork.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

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

    /*
        判断登录是否成功，并返回主页信息
     */
    @RequestMapping(value = "/index/teacherLogin")
    public Msg teacherLogin(HttpServletRequest request, @RequestParam("teacherNo") String teacherNo,@RequestParam("teacherPassword") String teacherPassword){
        Teacher teacher = teacherService.getTeacher(teacherNo);
        List<Course> courseList = courseService.getTeacherCourse(teacherNo);
        request.getSession().setAttribute("teacherInfo",teacher);
        ModelAndView mv = new ModelAndView();
        mv.addObject("teacherInfo",teacher);
        mv.addObject("courseList",courseList);
        if (teacherService.TeacherInspectionLogin(teacherNo,teacherPassword)) return Msg.success().add("teacher",teacher);
        return Msg.fail().add("teacher",null);
    }

    /*
        老师开设一门课程
     */
    @RequestMapping("/teaCreatOneCourse")
    public Msg teaCreatOneCourse(@RequestBody Course course){
        if (teacherService.creatCourseToClazz(course)) return Msg.success().add("course",course);
        return Msg.fail();
    }

    /*
        获取对应题目集学生的答卷
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

            return null;
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


//    /*
//          老师判分模块
//     */
//    @RequestMapping(value = "/Award")
//    public Msg award(@RequestParam("answerSetId") Integer answerId,@RequestParam("questionId") Integer questionId,@RequestParam("score")Integer score){
//        if(teacherService.Award(answerId,questionId,score)){
//            return  Msg.success();
//        }else {
//            return Msg.fail();
//        }
//    }
//
//    @RequestMapping(value = "/CountTotalScore")
//    public  Msg CountTotalScore(@RequestParam("answerSetId")Integer answerId){
//        if(teacherService.CountTotalScore(answerId)){
//            return  Msg.success();
//        }else {
//            return Msg.fail();
//        }
//    }


}
