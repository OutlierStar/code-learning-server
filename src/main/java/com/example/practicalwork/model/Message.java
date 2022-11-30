package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Message {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String studentNo;
    private String content;
    private Integer state;
    private Integer type;
    

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Message() {
    }

    public Message(String studentNo, String content, Integer state,Integer type
) {
        this.studentNo = studentNo;
        this.content = content;
        this.state = state;
        this.type = type;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", studentNo='" + studentNo + '\'' +
                ", content='" + content + '\'' +
                ", state=" + state +
                '}';
    }

    public String simpleToString(){
        return "{\"id\":"+this.id+"," +
                "\"studentNo\":\""+ this.studentNo +"\"," +
                "\"content\":"+ this.content +"," +
                "\"state\":"+this.state +"" +
                "}";
    }
}
