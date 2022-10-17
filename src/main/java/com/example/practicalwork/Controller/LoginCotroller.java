package com.example.practicalwork.Controller;

import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginCotroller {

    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/sayHello")
    public ModelAndView say(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        String pass = "20201301";
        Integer acc = 20201301;

        if (studentService.InspectionLogin(acc,pass)){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
        return mv;
    }
}
