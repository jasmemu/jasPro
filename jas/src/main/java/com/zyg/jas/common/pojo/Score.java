package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    private Integer id;

    private Integer hId;

    private String sNo;

    private Double score;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date correctDate;

    private String commitedJobUrl;

    public Score() {
    }

    public Score(Integer id, Integer hId, String sNo, Double score, Date correctDate, String commitedJobUrl) {
        this.id = id;
        this.hId = hId;
        this.sNo = sNo;
        this.score = score;
        this.correctDate = correctDate;
        this.commitedJobUrl = commitedJobUrl;
    }

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", hId=" + hId +
                ", sNo='" + sNo + '\'' +
                ", score=" + score +
                ", correctDate=" + correctDate +
                ", commitedJobUrl='" + commitedJobUrl + '\'' +
                '}';
    }
}