package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Clazz {
    @TableField("clazz")
    @TableId(value = "clazz_no")
    private String clazzNo;
    private String academyNo;
    private Integer studentNumber;

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }


    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "clazzNo='" + clazzNo + '\'' +
                ", academyNo='" + academyNo + '\'' +
                ", studentNumber=" + studentNumber +
                '}';
    }

}
