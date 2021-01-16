package com.zyg.jas.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Score  implements Serializable {
    private String hId;

    private String sNo;

    private Double score;

    private Date correctdate;

    private String commitedjobUrl;

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId == null ? null : hId.trim();
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

    public Date getCorrectdate() {
        return correctdate;
    }

    public void setCorrectdate(Date correctdate) {
        this.correctdate = correctdate;
    }

    public String getCommitedjobUrl() {
        return commitedjobUrl;
    }

    public void setCommitedjobUrl(String commitedjobUrl) {
        this.commitedjobUrl = commitedjobUrl == null ? null : commitedjobUrl.trim();
    }
}