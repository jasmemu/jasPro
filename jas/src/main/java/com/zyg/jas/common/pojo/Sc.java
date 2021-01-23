package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class Sc implements Serializable {
    private Integer scId;

    private String courseId;

    private String sNo;

    public Sc() {
    }

    public Sc(Integer scId, String courseId, String sNo) {
        this.scId = scId;
        this.courseId = courseId;
        this.sNo = sNo;
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    @Override
    public String toString() {
        return "Sc{" +
                "scId=" + scId +
                ", courseId='" + courseId + '\'' +
                ", sNo='" + sNo + '\'' +
                '}';
    }
}