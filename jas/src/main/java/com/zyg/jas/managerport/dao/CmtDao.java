package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CmtDao {

    public int insertCmt(Committee committee); //添加一个学委记录

    public Committee selectCmtByIdWith(String id);//根据id查询一条记录，联合查询

    Committee selectCmtById(String cmtId);

    Integer updateCmtPersonal(Committee committee); //仅跟新自己可以改变的信息

    public int updateCmtById(Committee committee); //根据id更新一条记录

    List<Committee> selectAllCmt();

    Integer selectCountOfCmt();

    Integer deleteCmtById(String comId);

    public List<Committee> selectCmtForSearch(Committee committee); //前端搜索框使用

    Committee selectCmtByAccount(String comId); //登录使用

    Integer  deleteByBatch(@Param("committeeList") List<Committee> committeeList);

    void updatePwdByAuthCode(@Param("stuNo") String stuNo,@Param("newPwd") String newPWD);

    // 查询本学期没有交作业的学生
    List<Student> selectStatisticsNotSumbmitByCourseId(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    // 查询已提交作业的学生
    List<Student> selectStatisticsSubmit(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    // 查询应该提交作业的学生
    List<Student> selectShouldSubmit(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    // 根据查询出来的学号和作业id查询未提交作业的学生信息
    Student selectBySnoAndHid(@Param("cmtId") String cmtId,@Param("courseId") String courseId,
                                     @Param("hId") Integer hId,@Param("sNo") String sNo);
}
