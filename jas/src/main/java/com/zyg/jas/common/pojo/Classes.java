package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class Classes implements Serializable {
    private Integer id;

    private Integer speId;
    private Specialty specialty;

    private String grade;

    private Integer numClass;

    private String charge; // 班主任编号
    private Teacher teacher;

    private Integer stuNum;

    public Classes() {
    }

    public Classes(Integer id, Integer speId, Specialty specialty, String grade, Integer numClass, String charge, Teacher teacher, Integer stuNum) {
        this.id = id;
        this.speId = speId;
        this.specialty = specialty;
        this.grade = grade;
        this.numClass = numClass;
        this.charge = charge;
        this.teacher = teacher;
        this.stuNum = stuNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpeId() {
        return speId;
    }

    public void setSpeId(Integer speId) {
        this.speId = speId;
    }

    public void setNumClass(Integer numClass) {
        this.numClass = numClass;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge == null ? null : charge.trim();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getNumClass() {
        return numClass;
    }

    public Integer getStuNum() {
        return stuNum;
    }

    public void setStuNum(Integer stuNum) {
        this.stuNum = stuNum;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", speId=" + speId +
                ", specialty=" + specialty +
                ", grade='" + grade + '\'' +
                ", numClass=" + numClass +
                ", charge='" + charge + '\'' +
                ", teacher=" + teacher +
                ", stuNum=" + stuNum +
                '}';
    }
}