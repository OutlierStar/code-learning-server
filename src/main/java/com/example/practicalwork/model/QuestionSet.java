package com.example.practicalwork.model;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 喻涛
 * @since 2022-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "set_id", type = IdType.AUTO)
    private Integer setId;

    private String setName;

    private Date startTime;

    private Date endTime;

    private Integer totalScore;

    private Integer totalItems;

    private Integer courseId;

    private Integer setType;

    public Integer getSetType() {
        return setType;
    }

    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSetId() {
        return setId;
    }

    public void setSetId(Integer setId) {
        this.setId = setId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "QuestionSet{" +
                "setId=" + setId +
                ", setName='" + setName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalScore=" + totalScore +
                ", totalItems=" + totalItems +
                ", courseId=" + courseId +
                ", setType=" + setType +
                '}';
    }
}
