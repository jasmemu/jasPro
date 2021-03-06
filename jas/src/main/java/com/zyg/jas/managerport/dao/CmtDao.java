package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Committee;
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
}
