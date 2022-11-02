package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("t_student_item")
public class TStudentItem {
    @TableId("student_item_Id")
    private Integer studentItemId;
    private Integer studentId;
    private Integer itemId;
    private String content;
    private Date fillTime;

    @Override
    public String toString() {
        return "TStudentItem{" +
                "studentItemId=" + studentItemId +
                ", studentId=" + studentId +
                ", itemId=" + itemId +
                ", content='" + content + '\'' +
                ", fillTime=" + fillTime +
                '}';
    }

    public int getStudentItemId() {
        return studentItemId;
    }

    public void setStudentItemId(int studentItemId) {
        this.studentItemId = studentItemId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFillTime() {
        return fillTime;
    }

    public void setFillTime(Date fillTime) {
        this.fillTime = fillTime;
    }
}
