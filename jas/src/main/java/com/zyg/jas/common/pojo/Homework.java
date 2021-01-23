package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class Homework implements Serializable {
    private String hId;

    private String comId;

    private String courseId;

    private String hUrl;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private String publishDate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private String endDate;

    private Integer id;

    public Homework() {
    }

    public Homework(String hId, String comId, String courseId, String hUrl, String publishDate, String endDate, Integer id) {
        this.hId = hId;
        this.comId = comId;
        this.courseId = courseId;
        this.hUrl = hUrl;
        this.publishDate = publishDate;
        this.endDate = endDate;
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId == null ? null : hId.trim();
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

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate == null ? null : publishDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "hId='" + hId + '\'' +
                ", comId='" + comId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", hUrl='" + hUrl + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", id=" + id +
                '}';
    }
}