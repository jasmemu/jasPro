package com.zyg.jas.common.pojo;

import java.util.Date;

public class Score {
    private Integer id;

    private Integer hId;

    private String sNo;

    private String comId;

    private Double score;

    private String comment;

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
}