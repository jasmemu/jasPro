package com.zyg.jas.managerport.service.impl;


import com.zyg.jas.common.pojo.Tc;
import com.zyg.jas.managerport.dao.TcDao;
import com.zyg.jas.managerport.service.TcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TcServiceImpl implements TcService {

    @Autowired
    private TcDao tcDao;
    @Override
    public int AddTc(Tc tc) {
       return this.tcDao.insertToTc(tc);
    }

    @Override
    public List<String> getCourseByTno(String tNo) {
        return this.tcDao.selectCourseByTno(tNo);
    }
}
