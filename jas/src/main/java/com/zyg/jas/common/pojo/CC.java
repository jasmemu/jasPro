package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class CC implements Serializable {
    private Integer id;
    private String comId;
    private String courseId; //课程名称

    public CC() {
    }

    public CC(Integer id, String comId, String courseId) {
        this.id = id;
        this.comId = comId;
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CC{" +
                "id=" + id +
                ", comId='" + comId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}
