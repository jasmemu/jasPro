package com.zyg.jas.managerport.service;

import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface CmtService {
    int saveCmt(Committee committee);

    List<Committee> getAllCmt(Integer pageNo, Integer pageSize);
    public Integer getCmtTotal(); //获取记录总数

    public List<Committee> getCmtForSearch(String speName,String cGrade,Integer cClass);  //前端搜索框使用


    Committee getCmtByComIdWith(String cmtId);

    Committee getCmtById(String id);

    void removeCmtByComId(String cmtId);

    List<Committee> dealExcelForCommittee(MultipartFile file);

    int saveCmtFromExcel(List<Committee> cmtList);

    Committee getCmtByAccount(String LoginAccount,String loginPassword);

    Integer saveCmtPersonal(Committee committee);

    Integer removeByBatch(List<Committee> committeeList);

    String getAuthCode(String email,String stuNo);

    void resetPwdByAuthCode(String stuNo, String newPWD);

    List<Student> getStatisticsSubmit(String cmtId,String courseId);

    List<Student> getShouldSubmit(String cmtId,String courseId);

    List<String> getNoSubmit(String cmtId,String courseId);

    String getStatisticsSubmitForExcel(String cmtId, String courseId,String courseName);

    String getNoSubmitForExcel(String cmtId, String courseId, String courseName);

//    List<Student> getStatisticsNoSubmit(String cmtId, String courseId);
}
