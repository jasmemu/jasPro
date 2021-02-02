package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

    private Integer id;
    private String comId;  //对应数据库中的学委id
    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date publishDate;
    private String noticeTitle;
    private String content;

//    private Committee committee; //

    public Notice() {
    }

    public Notice(String comId, Date publishDate, String noticeTitle, String content, Integer id) {
        this.comId = comId;
        this.publishDate = publishDate;
        this.noticeTitle = noticeTitle;
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

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", comId='" + comId + '\'' +
                ", publishDate=" + publishDate +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}