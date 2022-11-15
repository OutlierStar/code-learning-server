package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("answer_set")
public class AnswerSet {
    @TableId(value = "answer_set_id",type = IdType.AUTO)
    private Integer answerSetId;
    private Integer SetId;
    private Integer studentId;
    private Date submitTime;
    private Date answerTime;
    private Integer score;
    private Integer isSubmit;
    private Integer isRead;


    public Integer getAnswerSetId() {
        return answerSetId;
    }

    public void setAnswerSetId(Integer answerSerId) {
        this.answerSetId = answerSerId;
    }

    public Integer getSetId() {
        return SetId;
    }

    public void setSetId(Integer SetId) {
        this.SetId = SetId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }



    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Integer isSubmit) {
        this.isSubmit = isSubmit;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "AnswerSet{" +
                "answerSerId=" + answerSetId +
                ", SetId=" + SetId +
                ", studentId=" + studentId +
                ", submitTime=" + submitTime +
                ", answerTime=" + answerTime +
                ", score=" + score +
                ", isSubmit=" + isSubmit +
                ", isRead=" + isRead +
                '}';
    }
}
