package com.zyg.jas.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private String AId; //文章的id
    private String AName; //发布人的名字
    private Date publishData; //发布时间
    private String AContent; //发布内容

    public Article(String AId, String AName, Date publishData, String AContent) {
        this.AId = AId;
        this.AName = AName;
        this.publishData = publishData;
        this.AContent = AContent;
    }

    public String getAId() {
        return AId;
    }

    public void setAId(String AId) {
        this.AId = AId;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public Date getPublishData() {
        return publishData;
    }

    public void setPublishData(Date publishData) {
        this.publishData = publishData;
    }

    public String getAContent() {
        return AContent;
    }

    public void setAContent(String AContent) {
        this.AContent = AContent;
    }
}
