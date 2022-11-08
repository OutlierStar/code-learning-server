package com.example.practicalwork.Controller;

import com.example.practicalwork.model.Student;
import com.example.practicalwork.model.Teacher;
import com.example.practicalwork.service.AdminService;
import com.example.practicalwork.service.Impl.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/addStudent")
    public Msg addStudent(@RequestBody Student student) {
        if (student == null)
            return  Msg.fail();
        if(adminService.addStudent(student))
            return  Msg.success();
        return Msg.fail();
    }

    @RequestMapping(value = "/addTeacher")
    public Msg addTeacher(@RequestBody Teacher teacher) {
        if (teacher == null)
            return  Msg.fail();
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
