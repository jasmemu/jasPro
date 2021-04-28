package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Sc;

import java.util.List;

public interface ScDao {

    public int insertToSc(Sc sc);

    public List<String> selectCourseBySno(String sNo); //根据学号查看该学生选了哪些课程

    public void deleteScBySno(String sNo); //更新学生所选课程时，先根据sNo删除sc表中的记录，再重新插入
//学生端使用
    public List<String> selectCourseIdBySno(String sNo); //根据学号查看该学生的课程号
}
