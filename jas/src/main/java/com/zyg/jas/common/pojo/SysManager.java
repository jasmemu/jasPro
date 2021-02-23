package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class SysManager implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private String account;

    private String password;

    private String email;

    private String identify;

    public SysManager() { }

    public SysManager(Integer id, String name, String phone, String account, String password, String email, String identify) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.account = account;
        this.password = password;
        this.email = email;
        this.identify = identify;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify == null ? null : identify.trim();
    }

    @Override
    public String toString() {
        return "SysManager{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", identify='" + identify + '\'' +
                '}';
    }
}