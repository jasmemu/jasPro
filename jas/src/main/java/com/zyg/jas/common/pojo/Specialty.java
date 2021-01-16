package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class Specialty implements Serializable {
    private Integer speId;

    private String speName;

    public Integer getSpeId() {
        return speId;
    }

    public void setSpeId(Integer speId) {
        this.speId = speId;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName == null ? null : speName.trim();
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "speId=" + speId +
                ", speName='" + speName + '\'' +
                '}';
    }
}