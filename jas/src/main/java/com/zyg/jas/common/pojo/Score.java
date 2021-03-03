package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    private Integer id; // 学生提交的作业的id

    private Integer hId; // 学委发布的作业id(homework表的主键)
    private Homework homework;

    private String sNo;
    private Student student;

    private String comId;
    private Committee committee;

    private Double score;

    private String comment;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Shanghai")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//接受前台的时间格式 传到后台的格式
    private Date correctDate;

    private String commitedJobUrl;

    private String commitedJobPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gethId() {
        return hId;
    }

    public void sethId(Integer hId) {
        this.hId = hId;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCorrectDate() {
        return correctDate;
    }

    public void setCorrectDate(Date correctDate) {
        this.correctDate = correctDate;
    }

    public String getCommitedJobUrl() {
        return commitedJobUrl;
    }

    public void setCommitedJobUrl(String commitedJobUrl) {
        this.commitedJobUrl = commitedJobUrl == null ? null : commitedJobUrl.trim();
    }

    public String getCommitedJobPath() {
        return commitedJobPath;
    }

    public void setCommitedJobPath(String commitedJobPath) {
        this.commitedJobPath = commitedJobPath == null ? null : commitedJobPath.trim();
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", hId=" + hId +
                ", homework=" + homework +
                ", sNo='" + sNo + '\'' +
                ", student=" + student +
                ", comId='" + comId + '\'' +
                ", committee=" + committee +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", correctDate=" + correctDate +
                ", commitedJobUrl='" + commitedJobUrl + '\'' +
                ", commitedJobPath='" + commitedJobPath + '\'' +
                '}';
    }
}