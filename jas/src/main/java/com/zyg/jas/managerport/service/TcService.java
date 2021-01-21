package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Tc;

import java.util.List;

public interface TcService {
    public int AddTc(Tc tc);
    public List<String> getCourseByTno(String tNo);  //查出该教师编号所教的所有课程名称
}
