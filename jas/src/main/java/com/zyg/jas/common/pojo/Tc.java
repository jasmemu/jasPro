package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class Tc implements Serializable {
    private Integer tcId;

    //courseId课程名称
    private String courseId;

    private String tNo;

    public Tc() { }

    public Tc(Integer tcId, String courseId, String tNo) {
        this.tcId = tcId;
        this.courseId = courseId;
        this.tNo = tNo;
    }

    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo == null ? null : tNo.trim();
    }

    @Override
    public String toString() {
        return "Tc{" +
                "tcId=" + tcId +
                ", courseId='" + courseId + '\'' +
                ", tNo='" + tNo + '\'' +
                '}';
    }
}