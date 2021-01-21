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

    private List<Course> courses; //所教课程

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
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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