package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("question")
public class Question {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer setId;
    private String answer;
    private String content;
    private Integer score;
    private String type;
    @TableField(value = "question_options")
    private String questionOptions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = questionOptions;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", setId=" + setId +
                ", answer='" + answer + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", type='" + type + '\'' +
                ", questionOptions='" + questionOptions + '\'' +
                '}';
    }
}
