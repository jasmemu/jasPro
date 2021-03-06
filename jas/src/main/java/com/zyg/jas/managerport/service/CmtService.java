package com.zyg.jas.managerport.service;

import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Committee;
import org.springframework.web.multipart.MultipartFile;

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
}
