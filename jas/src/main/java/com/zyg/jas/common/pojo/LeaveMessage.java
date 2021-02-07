package com.zyg.jas.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class LeaveMessage implements Serializable{
    private Integer id;

    private String comId; // 回复留言的学委学号
    private Committee committee;

    private String sNo; // 留言的学生学号
    private Student student;

    private String leaveMessage; // 留言内容

    private String replyMessage; // 回复内容

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date lmDate; // 留言日期

    @JsonFormat(pattern="yyyy-MM-dd",timezone="Locale.US")//后台的时间 格式化 发送到前台
    @DateTimeFormat(pattern="yyyy-MM-dd")//接受前台的时间格式 传到后台的格式
    private Date rmDate; // 回复日期
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComId() {
        return comId;
    }

    public LeaveMessage() {
    }

    public void setComId(String comId) {
        this.comId = comId == null ? null : comId.trim();
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage == null ? null : leaveMessage.trim();
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage == null ? null : replyMessage.trim();
    }

    public Date getLmDate() {
        return lmDate;
    }

    public void setLmDate(Date lmDate) {
        this.lmDate = lmDate;
    }

    public Date getRmDate() {
        return rmDate;
    }

    public void setRmDate(Date rmDate) {
        this.rmDate = rmDate;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "LeaveMessage{" +
                "id=" + id +
                ", comId='" + comId + '\'' +
                ", committee=" + committee +
                ", sNo='" + sNo + '\'' +
                ", student=" + student +
                ", leaveMessage='" + leaveMessage + '\'' +
                ", replyMessage='" + replyMessage + '\'' +
                ", lmDate=" + lmDate +
                ", rmDate=" + rmDate +
                '}';
    }
}