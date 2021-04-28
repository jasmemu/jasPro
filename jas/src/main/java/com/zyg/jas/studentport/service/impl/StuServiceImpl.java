package com.zyg.jas.studentport.service.impl;

import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.managerport.dao.CmtDao;
import com.zyg.jas.managerport.dao.LeaveMessageDao;
import com.zyg.jas.studentport.dao.StuDao;
import com.zyg.jas.studentport.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuDao stuDao;
    @Autowired
    private CmtDao cmtDao;
    @Autowired
    private LeaveMessageDao leaveMessageDao;
//    @Autowired
//    private NoticeDao noticeDao;
//    @Autowired
//    private SpecialtyDao specialtyDao;
//    @Autowired
//    private CCDao ccDao;

    @Override
    public Student getStudentByAccount(String loginAccount,String loginPassword) {
        Student student = this.stuDao.selectStudentById(loginAccount);
        if(student.getPassword().equals(loginPassword)){
            return student;
        }else{
            return null;
        }
    }

    @Override
    public int saveStu(Student student) {
        return 0;
    }
//
//    @Override
//    public int saveStu(Student student) {
//        List<Specialty> specialties = this.specialtyDao.selectAllSpeCialty();
//        for (int i=0;i<specialties.size();i++){
//            if(specialties.get(i).getSpeName().equals(student.get))
//        }
//        return 0;
//    }

    @Override
    public List<Student> getAllStu(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public List<Student> getStuForSearch(String speName, String sGrade, Integer sClass) {
        return null;
    }

    @Override
    public Student getStudentByIdWith(String sNo) {
        return this.stuDao.selectStudentByIdWith(sNo);
    }

    @Override
    public Student getStudentById(String sNo) {
        return this.stuDao.selectStudentById(sNo);
    }

    @Override
    public Integer saveStudentPersonal(Student student) {
        return this.stuDao.updateStudentPersonal(student);
    }

    @Override
    public Committee getComid(int speid, String sgrade, int sclass) {
        return this.cmtDao.searchComidByStu(speid,sgrade,sclass);
    }

    @Override
    public void insertMessage(String comId, String sNo, String leaveMessage, Date lmDate) {
        this.leaveMessageDao.sendMessageByStu(comId,sNo,leaveMessage,lmDate);
    }




    //下面是公告查询等部分

//    @Override
//    public List<Notice> getNoticeByCmtId(String cmtId, Integer pageNo, Integer pageSize) {
//        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
//        List<Notice> list = this.noticeDao.selectAllNotice(cmtId);
//        return list;
//    }
//
//    @Override
//    public Integer getTotal(String cmtId) {
//        Integer total = this.noticeDao.selectTotal(cmtId);
//        return total;
//    }
//
//    @Override
//    public Notice getNoticeByIdWith(Integer id) {
//        return  this.noticeDao.selectByIdWith(id);
//    }
//
//    @Override
//    public List<Notice> getNoticesForSearch(String cmtId, String publishDate, String noticeTitle) {
//        Map paramsMap = new HashMap();
//        paramsMap.put("cmtId",cmtId);
//        paramsMap.put("publishDate",publishDate);
//        paramsMap.put("noticeTitle",noticeTitle);
//        return this.noticeDao.selectForSearch(paramsMap);
//    }
}
