package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("question")
public class Question {
    public Question(int itemId, String answer, int itemScore, String itemType, int id) {
        this.itemId = itemId;
        this.answer = answer;
        this.itemScore = itemScore;
        this.itemType = itemType;
        this.id = id;
    }

    public Question() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getItemScore() {
        return itemScore;
    }

    public void setItemScore(Integer itemScore) {
        this.itemScore = itemScore;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @TableId
    private Integer itemId;
    private String answer;
    private  Integer itemScore;
    private String itemType;

    private Integer id;


    @Override
    public String toString() {
        return "Question{" +
                "itemId=" + itemId +
                ", answer='" + answer + '\'' +
                ", itemScore=" + itemScore +
                ", itemType='" + itemType + '\'' +
                ", id=" + id +
                '}';
    }
}
