package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;

public class Teacher {
    @TableId("teacher_no")
    private String teacherNo;

    private String teacherName;

    private String teacherPassword;

    private String academyNo;

    private String memo;

    private String teacherAge;

    private String teacherSex;

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    public String getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(String teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", academyNo='" + academyNo + '\'' +
                ", memo='" + memo + '\'' +
                ", teacherAge='" + teacherAge + '\'' +
                ", teacherSex='" + teacherSex + '\'' +
                '}';
    }
}
