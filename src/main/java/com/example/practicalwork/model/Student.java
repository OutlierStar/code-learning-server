package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

public class Student {
    @TableId(value = "student_id")
    private Integer studentId;

    private String studentNo;

    private String studentName;

    private String studentPassword;

    private Data studentBirth;

    private String memo;

    private String clazzNo;

    private String school;

    private String major;



    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Data getStudentBirth() {
        return studentBirth;
    }

    public void setStudentBirth(Data studentBirth) {
        this.studentBirth = studentBirth;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentBirth=" + studentBirth +
                ", memo='" + memo + '\'' +
                ", clazzNo='" + clazzNo + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}