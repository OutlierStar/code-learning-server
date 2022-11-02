package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

public class Student {
    @TableId(value = "student_id")
    private Integer studentId;
    private String studentName;
    private String studentPassword;
    private Data studentBirth;
    private String memo;
    private String academyNo;
    private String studentSex;
    private String studentNo;

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentBirth=" + studentBirth +
                ", memo='" + memo + '\'' +
                ", academyNo='" + academyNo + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentNo='" + studentNo + '\'' +
                '}';
    }
}