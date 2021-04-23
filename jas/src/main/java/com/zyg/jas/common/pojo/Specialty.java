package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Specialty implements Serializable {
    private Integer speId;

    private String speName;

    // 开办日期 （21.4.22）
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Asia/Shanghai")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date setDate;

    // 专业编码（21.4.22）
    private String speNum;

    public Specialty() {
    }

    public Specialty(String speName) {
        this.speName = speName;
    }

    public Specialty(Integer speId, String speName) {
        this.speId = speId;
        this.speName = speName;
    }

    public Specialty(Integer speId, String speName, Date setDate) {
        this.speId = speId;
        this.speName = speName;
        this.setDate = setDate;
    }

    public Specialty(Integer speId, String speName, Date setDate, String speNum) {
        this.speId = speId;
        this.speName = speName;
        this.setDate = setDate;
        this.speNum = speNum;
    }

    public Integer getSpeId() {
        return speId;
    }

    public void setSpeId(Integer speId) {
        this.speId = speId;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName == null ? null : speName.trim();
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public String getSpeNum() {
        return speNum;
    }

    public void setSpeNum(String speNum) {
        this.speNum = speNum;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "speId=" + speId +
                ", speName='" + speName + '\'' +
                ", setDate=" + setDate +
                ", speNum='" + speNum + '\'' +
                '}';
    }
}