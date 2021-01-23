package com.zyg.jas.common.pojo;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {
    private String tNo;

    private String password;

    private String name;

    private String identify;

    private String phone;

    private String email;

    private List<Course> courses; //教师的课程

    public Teacher() { }

    public Teacher(String tNo, String password, String name, String identify, String phone, String email, List<Course> courses) {
        this.tNo = tNo;
        this.password = password;
        this.name = name;
        this.identify = identify;
        this.phone = phone;
        this.email = email;
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo == null ? null : tNo.trim();
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

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify == null ? null : identify.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tNo='" + tNo + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", identify='" + identify + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses +
                '}';
    }
}