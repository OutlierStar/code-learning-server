package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Clazz {
    @TableField("clazz")
    @TableId(value = "clazz_no")
    private String clazzNo;
    private String teacherNo;
    private String studentNo;
    private String academyNo;

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzNo='" + clazzNo + '\'' +
                ", teacherNo='" + teacherNo + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", academyNo='" + academyNo + '\'' +
                '}';
    }
}
