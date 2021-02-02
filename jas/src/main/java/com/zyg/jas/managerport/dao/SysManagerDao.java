package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.SysManager;
import org.apache.ibatis.annotations.Param;

public interface SysManagerDao {

    SysManager selectSysForLogin(@Param("account") String account,@Param("password")String password);

    SysManager selectSysByAccount(String account);

    Integer updateSys(SysManager sysManager);
}
