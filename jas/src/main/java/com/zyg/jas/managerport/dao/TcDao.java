package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Tc;

import java.util.List;

public interface TcDao {
    public int insertToTc(Tc tc);

    public void deleteTcByTno(String tNo); //更新教师教授课程时，先根据tNo删除tc表中的记录，再重新插入

    public List<String> selectCourseByTno(String tNo); //根据教师编号查看该教师选了哪些课程
}
