package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    private String sNo;

    private String name;

    private String password;

    private String phone;

    private String identify;

    private Integer speId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date enrollment;

    private String email;

    private String sGrade;

    private Integer sClass;

    private List<Course> courses; //学生所选课程

    public Student() {
    }

    public Student(String sNo, String name, String password, String phone, String identify, Integer speId, List<Course> courses, Date enrollment, String email, String sGrade, Integer sClass) {
        this.sNo = sNo;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.identify = identify;
        this.speId = speId;
        this.courses = courses;
        this.enrollment = enrollment;
        this.email = email;
        this.sGrade = sGrade;
        this.sClass = sClass;
    }


    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify == null ? null : identify.trim();
    }

    public Integer getSpeId() {
        return speId;
    }

    public void setSpeId(Integer speId) {
        this.speId = speId;
    }

    public Date getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Date enrollment) {
        this.enrollment = enrollment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getsGrade() {
        return sGrade;
    }

    public void setsGrade(String sGrade) {
        this.sGrade = sGrade == null ? null : sGrade.trim();
    }

    public Integer getsClass() {
        return sClass;
    }

    public void setsClass(Integer sClass) {
        this.sClass = sClass;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sNo='" + sNo + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", identify='" + identify + '\'' +
                ", speId=" + speId +
                ", enrollment=" + enrollment +
                ", email='" + email + '\'' +
                ", sGrade='" + sGrade + '\'' +
                ", sClass=" + sClass +
                ", courses=" + courses +
                '}';
    }
}