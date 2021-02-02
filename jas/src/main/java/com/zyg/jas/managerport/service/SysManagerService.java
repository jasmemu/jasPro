package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.SysManager;

public interface SysManagerService {
    SysManager getSysManagerForLogin(String account,String password);

    SysManager getSysManagerByAccount(String account);

    Integer saveSys(SysManager sysManager);
}
