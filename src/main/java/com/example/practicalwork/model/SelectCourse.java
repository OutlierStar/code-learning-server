package com.example.practicalwork.model;

public class SelectCourse {
    private int courseId;
    private String clazzNo;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getClazzNo() {
        return clazzNo;
    }

    public void setClazzNo(String clazzNo) {
        this.clazzNo = clazzNo;
    }

    @Override
    public String toString() {
        return "SelectCourse{" +
                "courseId=" + courseId +
                ", clazzNo='" + clazzNo + '\'' +
                '}';
    }
}
