package com.zyg.jas.common.tool.vo;

import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.common.pojo.Teacher;

import java.io.Serializable;

public class ClassVO implements Serializable {
    private Integer speId;
    private Specialty specialty;
    private String sGrade;
    private Integer sClass;
    private Teacher teacher;
    private Integer num;
    private Classes classes;

    public ClassVO() {
    }

    public ClassVO(Integer speId, Specialty specialty, String sGrade, Integer sClass, Teacher teacher, Integer num, Classes classes) {
        this.speId = speId;
        this.specialty = specialty;
        this.sGrade = sGrade;
        this.sClass = sClass;
        this.teacher = teacher;
        this.num = num;
        this.classes = classes;
    }

    public Integer getSpeId() {
        return speId;
    }

    public void setSpeId(Integer speId) {
        this.speId = speId;
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade;
    }

    public Integer getsClass() {
        return sClass;
    }

    public void setsClass(Integer sClass) {
        this.sClass = sClass;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "ClassVO{" +
                "speId=" + speId +
                ", specialty=" + specialty +
                ", sGrade='" + sGrade + '\'' +
                ", sClass=" + sClass +
                ", teacher=" + teacher +
                ", num=" + num +
                ", classes=" + classes +
                '}';
    }
}
