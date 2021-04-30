package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class CC implements Serializable {
    private Integer id;
    private String comId;
    private Committee committee;
    //courseId课程名称
    private String courseId;
    private Course course;

    public CC() {
    }

    public CC(Integer id, String comId, Committee committee, String courseId, Course course) {
        this.id = id;
        this.comId = comId;
        this.committee = committee;
        this.courseId = courseId;
        this.course = course;
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
                ", committee=" + committee +
                ", courseId='" + courseId + '\'' +
                ", course=" + course +
                '}';
    }
}
