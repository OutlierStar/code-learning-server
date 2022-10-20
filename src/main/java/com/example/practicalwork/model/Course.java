package com.example.practicalwork.model;

public class Course {
    private Integer courseId;
    private String teacherNo;
    private String courseName;
    private String Memo;
    private String courseDate;

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

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", teacherNo='" + teacherNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Memo='" + Memo + '\'' +
                ", courseDate='" + courseDate + '\'' +
                '}';
    }
}
