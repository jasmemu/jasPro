package com.zyg.jas.studentport.service;

import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Student;

import java.util.Date;
import java.util.List;

public interface StuService {
    Student getStudentByAccount(String LoginAccount, String loginPassword);
    int saveStu(Student student);
    List<Student> getAllStu(Integer pageNo, Integer pageSize);
    public List<Student> getStuForSearch(String speName, String sGrade, Integer sClass);  //前端搜索框使用
    Student getStudentByIdWith(String sNo);
    Student getStudentById(String sNo);
    Integer saveStudentPersonal(Student student);
    //公告查询等
//    List<Notice> getNoticeByCmtId(String cmtId, Integer pageNo, Integer pageSize);
//    Integer getTotal(String cmtId);
//    Notice getNoticeByIdWith(Integer id);
//    List<Notice> getNoticesForSearch(String cmtId,String publishDate,String noticeTitle);

    Committee getComid(int speid, String sgrade, int sclass);

    void insertMessage(String comId, String sNo, String leaveMessage, Date lmDate);
}
