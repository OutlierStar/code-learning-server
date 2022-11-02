package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AnswerSet {
    @TableField("answer_set")
    @TableId(value = "answer_set_id")
    private Integer answerSerId;
    private Integer SetId;
    private Integer studentId;
    private Date submitTime;
    private Date answerTime;
    private String answer;
    private Integer isAnswered;
    private Integer score;
    private Integer isSubmit;
    private Integer isRead;


    public Integer getAnswerSerId() {
        return answerSerId;
    }

    public void setAnswerSerId(Integer answerSerId) {
        this.answerSerId = answerSerId;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Integer isAnswered) {
        this.isAnswered = isAnswered;
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
                "answerSerId=" + answerSerId +
                ", SetId=" + SetId +
                ", studentId=" + studentId +
                ", submitTime=" + submitTime +
                ", answerTime=" + answerTime +
                ", answer='" + answer + '\'' +
                ", isAnswered=" + isAnswered +
                ", score=" + score +
                ", isSubmit=" + isSubmit +
                ", isRead=" + isRead +
                '}';
    }
}
