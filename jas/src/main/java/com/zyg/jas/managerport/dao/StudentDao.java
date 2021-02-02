package com.zyg.jas.managerport.dao;



import com.zyg.jas.common.pojo.Student;

import java.util.List;

public interface StudentDao {
    public int insertStudent(Student student); //添加一个学生

    public List<Student> selectAllStu(); //查询所有的学生记录
    public Integer selectCountOfStu();

    public Student selectStuBySno(String sNO);//根据sNO查询一条记录

    public int updateStudent(Student student); //更新一条记录

    public void deleteBySno(String sNo); //根据sno删除一条记录

    public List<Student> selectStuForSearch(Student student); //前端搜索框使用


}
