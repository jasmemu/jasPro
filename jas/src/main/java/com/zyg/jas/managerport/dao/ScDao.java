package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Sc;

import java.util.List;

public interface ScDao {

    public int insertToSc(Sc sc);

    public List<String> selectCourseBySno(String sNo); //根据学号查看该学生选了哪些课程
}
