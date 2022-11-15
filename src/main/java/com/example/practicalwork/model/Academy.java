package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Academy {
    @TableId(value = "academy_no")
    private String academyNo;
    private String academyName;
    private String subject;

    public String getAcademyNo() {
        return academyNo;
    }

    public void setAcademyNo(String academyNo) {
        this.academyNo = academyNo;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Academy{" +
                "academyNo='" + academyNo + '\'' +
                ", academyName='" + academyName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
