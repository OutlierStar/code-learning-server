package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class Answer {
    @TableField("answer")
    @TableId(value = "answer_set_id")
    private Integer answerSerId;
    private Integer questionId;
    private String answer;
    private Integer isAnswer;
    private Integer score;
    private Integer isRead;

    public Integer getAnswerSerId() {
        return answerSerId;
    }

    public void setAnswerSerId(Integer answerSerId) {
        this.answerSerId = answerSerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "AnswerMapper{" +
                "answerSerId=" + answerSerId +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", isAnswer=" + isAnswer +
                ", score=" + score +
                ", isRead=" + isRead +
                '}';
    }
}
