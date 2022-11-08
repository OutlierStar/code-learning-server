package com.example.practicalwork.Controller;

import com.example.practicalwork.Mapper.StudentMapper;
import com.example.practicalwork.model.Clazz;
import com.example.practicalwork.model.Student;
import com.example.practicalwork.service.Impl.StudentServiceImpl;
import com.example.practicalwork.service.Impl.WordServiceImpl;
import com.example.practicalwork.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
@CrossOrigin
@RestController
public class WordFileController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/getWord")
    public void getZbrzWord(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("stuNo") String stuNO,@RequestParam("clazzNo") String clazzNo) {
        Student student = studentService.getOneStu(stuNO);
        HashMap<String,Object> params = new HashMap<>();
        //word模板地址
        String wordModelPath = "C:\\Users\\喻涛\\Documents\\test\\text01.docx";
        //生成临时文件地址
        String filePath = "C:\\Users\\喻涛\\Documents\\test";
        params.put("clazz",clazzNo);
        params.put("name",student.getStudentName());
        params.put("stuNo",student.getStudentNo());
        params.put("workName","vue训练");
        WordServiceImpl.exportWord(wordModelPath,filePath,"前端技术实验报告.docx",params,request,response);
    }
}
