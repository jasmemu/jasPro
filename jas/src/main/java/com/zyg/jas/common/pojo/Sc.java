package com.zyg.jas.common.pojo;

import java.io.Serializable;

public class Sc  implements Serializable {
    private String courseId;  //不是课程id，而是课程名称（也唯一）

    private String sNo;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo == null ? null : sNo.trim();
    }

    @Override
    public String toString() {
        return "Sc{" +
                "courseId='" + courseId + '\'' +
                ", sNo='" + sNo + '\'' +
                '}';
    }
}