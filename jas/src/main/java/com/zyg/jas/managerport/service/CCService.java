package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.CC;

import java.util.List;

public interface CCService {


    public List<String> getCourseByComId(String speId);

    List<CC> getCourseForCmtByCmtIdWith(String cmtId);
}
