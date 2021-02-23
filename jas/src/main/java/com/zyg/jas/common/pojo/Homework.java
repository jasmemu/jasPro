package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Homework implements Serializable {
    private Integer hId;

    private String hName;
    private String comId;
    private Committee committee;

    private String courseId;
    private Course course;

    private String hUrl;

    private String hPath;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//接受前台的时间格式 传到后台的格式
    private Timestamp publishDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//接受前台的时间格式 传到后台的格式
    private Timestamp endDate;

    private String mark;


    public Homework() {
    }




    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String gethUrl() {
        return hUrl;
    }

    public void sethUrl(String hUrl) {
        this.hUrl = hUrl == null ? null : hUrl.trim();
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String gethPath() {
        return hPath;
    }

    public void sethPath(String hPath) {
        this.hPath = hPath;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "hId=" + hId +
                ", hName='" + hName + '\'' +
                ", comId='" + comId + '\'' +
                ", committee=" + committee +
                ", courseId='" + courseId + '\'' +
                ", course=" + course +
                ", hUrl='" + hUrl + '\'' +
                ", hPath='" + hPath + '\'' +
                ", publishDate=" + publishDate +
                ", endDate=" + endDate +
                ", mark='" + mark + '\'' +
                '}';
    }
}