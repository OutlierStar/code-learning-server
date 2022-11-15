package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class SelectCourse {

    @TableId(value = "course_id")
    private Integer courseId;
    @TableField(value = "clazz_no")
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
