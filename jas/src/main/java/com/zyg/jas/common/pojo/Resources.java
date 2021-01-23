package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Resources implements Serializable {
    private String comId;

    private String resourceName;

    private String resourceUrl;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date uploadTime;

    private String resourceType;

    private Integer id;

    public Resources() {
    }

    public Resources(String comId, String resourceName, String resourceUrl, Date uploadTime, String resourceType, Integer id) {
        this.comId = comId;
        this.resourceName = resourceName;
        this.resourceUrl = resourceUrl;
        this.uploadTime = uploadTime;
        this.resourceType = resourceType;
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "comId='" + comId + '\'' +
                ", resourceName='" + resourceName + '\'' +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", uploadTime=" + uploadTime +
                ", resourceType='" + resourceType + '\'' +
                ", id=" + id +
                '}';
    }
}