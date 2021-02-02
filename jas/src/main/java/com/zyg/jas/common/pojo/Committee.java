package com.zyg.jas.common.pojo;

import java.io.Serializable;
import java.util.List;

public class Committee implements Serializable {
    private String comId;

    private String password;

    private String name;

    private String phone;

    private String email;

    private  Specialty specialty;
    
    private String cGrade;
    private Integer cClass;

    private List<Course> courses; //一个学习委员可能管理多门课程的作业

    public Committee() {
    }

    public Committee(String comId, String password, String name, String phone, String email, Specialty specialty, String cGrade, Integer cClass, List<Course> courses) {
        this.comId = comId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.specialty = specialty;
        this.cGrade = cGrade;
        this.cClass = cClass;
        this.courses = courses;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getcGrade() {
        return cGrade;
    }

    public void setcGrade(String cGrade) {
        this.cGrade = cGrade;
    }

    public Integer getcClass() {
        return cClass;
    }

    public void setcClass(Integer cClass) {
        this.cClass = cClass;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Committee{" +
                "comId='" + comId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", specialty=" + specialty +
                ", cGrade='" + cGrade + '\'' +
                ", cClass=" + cClass +
                ", courses=" + courses +
                '}';
    }
}