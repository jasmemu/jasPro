package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.SysManager;
import com.zyg.jas.managerport.dao.SysManagerDao;
import com.zyg.jas.managerport.service.SysManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysManagerServiceImpl implements SysManagerService {
    @Autowired
    private SysManagerDao sysManagerDao;
    @Override
    public SysManager getSysManagerForLogin(String account, String password) {
        return sysManagerDao.selectSysForLogin(account,password);
    }

    @Override
    public SysManager getSysManagerByAccount(String account) {
        return this.sysManagerDao.selectSysByAccount(account);
    }

    @Override
    public Integer saveSys(SysManager sysManager) {
        return this.sysManagerDao.updateSys(sysManager);
    }


}
