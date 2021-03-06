package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeaDao {
    public int insertToTeacher(Teacher teacher);
    public Teacher selectTeaByTno(String tNo);

    public int updateTea(Teacher teacher); //更新一条记录

    public List<Teacher> selectAllTea(); //查询所有的教师记录
    public Integer selectCountOfTea();

    public void deleteByTno(String tNo); //根据tno删除一条记录


    public List<Teacher> selectTeaForSearch(Teacher teacher); //前端搜索框使用

    Integer deleteByBatch(@Param("teacherList") List<Teacher> teacherList);
}
