package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.CC;

import java.util.List;

public interface CCDao2 {

    public int insertToCc(CC cc);

    public List<String> selectCourseByComId(String id); //根据学委学号查看该学委管理哪些课程

    public Integer deleteCcByComid(String id); //更新学委管理课程时，先根据comId删除cc表中的记录，再重新插入

    List<CC> selectCourseForCmtByCmtIdWith(String cmtId);// 根据学委学号查询管理了哪些课程带有课程信息
}
