package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;

public class Course {
    @TableId(value = "course_id")
    private Integer courseId;
    private String teacherNo;
    private String courseName;
    private String Memo;
    private String courseDate;
    private String invitationCode;
    private String clazzNO;

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", teacherNo='" + teacherNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Memo='" + Memo + '\'' +
                ", courseDate='" + courseDate + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", clazzNO='" + clazzNO + '\'' +
                '}';
    }

    public String getClazzNO() {
        return clazzNO;
    }

    public void setClazzNO(String clazzNO) {
        this.clazzNO = clazzNO;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

}
