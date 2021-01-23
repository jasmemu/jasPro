package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {
    private String comId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date publishDate;

    private String content;

    private Integer id;

    public Notice() {
    }

    public Notice(String comId, Date publishDate, String content, Integer id) {
        this.comId = comId;
        this.publishDate = publishDate;
        this.content = content;
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "comId='" + comId + '\'' +
                ", publishDate=" + publishDate +
                ", content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}