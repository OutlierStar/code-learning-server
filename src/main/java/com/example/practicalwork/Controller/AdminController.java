package com.example.practicalwork.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practicalwork.Mapper.*;
import com.example.practicalwork.model.Academy;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.model.Teacher;
import com.example.practicalwork.service.AdminService;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AcademyMapper academyMapper;
    @Autowired
    AnswerSetMapper answerSetMapper;
    @Autowired
    AnswerMapper answerMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    MessageMapper messageMaper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    QuestionSetMapper questionSetMapper;
    @Autowired
    SelectCourseMapper selectCourseMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TStudentItemMapper tStudentItemMapper;

    /**
     * getAll
     *
     * @author wdx
     * @return
     */


    @RequestMapping(value = "/getAllStudent")
    public Msg getAllStudent() {
        return  Msg.success().add("students",studentMapper.selectList(null));
    }

    @RequestMapping(value = "/getAllAcademy")
    public Msg getAllAcademy() {
        return  Msg.success().add("students",academyMapper.selectList(null));
    }

    @RequestMapping(value = "/getAllTeacher")
    public Msg getAllTeacher() {
        return  Msg.success().add("students",teacherMapper.selectList(null));
    }
    /**
     * 返回全部班级
     *
     * @author wdx
     */
    @RequestMapping(value = "/getAllClazz")
    public Msg getAllClazz(){
        
        return Msg.success().add("clazzs",clazzMapper.selectList(null));
    }

    /**
     * 增加academy
     *
     * @author wdx
     */
    @RequestMapping(value = "/addAcademy")
    public Msg addAcademy(@RequestBody Academy academy) {
        if (academy == null)
            return  Msg.fail();
        QueryWrapper<Academy> wrapper = new QueryWrapper<>();
        if (academyMapper.exists(wrapper.eq("academy_no",academy.getAcademyNo())))
            return Msg.fail().add("error","该学院号已经存在");
        if(academyMapper.insert(academy)>0)
            return  Msg.success().add("academy",academyMapper.selectOne(new QueryWrapper<Academy>().eq("academy_no",academy.getAcademyNo())));
        return Msg.fail();
    }

    /**
     * 删除academy
     *
     * @author wdx
     */
    @RequestMapping(value = "/deleteAcademy")
    public Msg deleteStudent(@RequestBody Academy academy){
        if (academy == null)
            return  Msg.fail().add("error","academy不能为空");
        QueryWrapper<Academy> wrapper = new QueryWrapper<>();
        wrapper.eq("academy_no",academy.getAcademyNo());
        int count = academyMapper.delete(wrapper);

        if(count>0){
            return Msg.success();
        }
        return Msg.fail();
    }

    /**
     * 更新academy
     *
     * @author wdx
     */
    @RequestMapping(value = "/updateAcademy")
    public Msg updateStudent(@RequestBody Academy academy) {
        if (academy == null)
            return  Msg.fail();
        QueryWrapper<Academy> wrapper = new QueryWrapper<>();
        wrapper.eq("academy_no",academy.getAcademyNo());
        int count = academyMapper.update(academy,wrapper);
        if(count>0)
            return  Msg.success().add("academy",academyMapper.selectOne(new QueryWrapper<Academy>().eq("academy_no",academy.getAcademyNo())));
        return Msg.fail();
    }

    @RequestMapping(value = "/addStudent")
    public Msg addStudent(@RequestBody Student student) {
        if (student == null)
            return  Msg.fail();
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if (studentMapper.exists(wrapper.eq("student_no",student.getStudentNo())))
            return Msg.fail().add("error","该学号学生已经存在");
        if(adminService.addStudent(student))
            return  Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/addTeacher")
    public Msg addTeacher(@RequestBody Teacher teacher) {
        if (teacher == null)
            return  Msg.fail();

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (teacherMapper.exists(wrapper.eq("teacher_no",teacher.getTeacherNo())))
            return Msg.fail().add("error","该工号老师已经存在");
        if(adminService.addTeacher(teacher))
            return  Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/updateStudent")
    public Msg updateStudent(@RequestBody Student student) {
        if (student == null)
            return  Msg.fail();
        if(adminService.updateStudent(student))
            return  Msg.success();
        return Msg.fail();
    }
    @RequestMapping(value = "/updateTeacher")
    public Msg updateTeacher(@RequestBody Teacher teacher) {
        if (teacher == null)
            return  Msg.fail();
        if(adminService.updateTeacher(teacher))
            return  Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/searchStudentForId")
    public Msg searchStudentForId(@RequestParam Integer id) {
        List<Student> students = adminService.searchStudentForId(id);
        if( students.size()>0)
            return  Msg.success().add("student",students);
        return Msg.fail();
    }


    @RequestMapping(value = "/searchStudentForName")
    public Msg searchStudentForName(@RequestParam String id) {
        List<Student> students = adminService.searchStudentForName(id);
        if( students.size()>0)
            return  Msg.success().add("student",students);
        return Msg.fail();
    }

    @RequestMapping(value = "/searchStudentForClazz")
    public Msg searchStudentForClazz(@RequestParam String  clazz) {
        List<Student> students = adminService.searchStudentForClazz(clazz);
        if( students.size()>0)
            return  Msg.success().add("student",students);
        return Msg.fail();
    }
    @RequestMapping(value = "/searchTeacherForId")
    public Msg searchTeacherForId(@RequestParam String id) {
        List<Teacher> teacher = adminService.searchTeacherForId(id);
        if( teacher.size()>0)
            return  Msg.success().add("teacher",teacher);
        return Msg.fail();
    }

    @RequestMapping(value = "/searchTeacherForName")
    public Msg searchTeacherForName(@RequestParam String name) {
        List<Teacher> teacher = adminService.searchTeacherForName(name);
        if( teacher.size()>0)
            return  Msg.success().add("teacher",teacher);
        return Msg.fail();
    }
    @RequestMapping(value = "/deleteStudent")
    public Msg deleteStudent(@RequestBody Student student){
        if(adminService.deleteStudent(student)){
            return Msg.success();
        }
        return Msg.fail();
    }
    @RequestMapping(value = "/deleteTeacher")
    public Msg deleteTeacher(@RequestBody Teacher teacher){
        if(adminService.deleteTeacher(teacher)){
            return Msg.success();
        }
        return Msg.fail();
    }


}
