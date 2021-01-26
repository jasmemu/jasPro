package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.managerport.dao.CCDao;
import com.zyg.jas.managerport.service.CCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CCServiceImpl implements CCService {
    @Autowired
    private CCDao ccDao;

    @Override
    public List<String> getCourseByComId(String comId) {
        return this.ccDao.selectCourseByComId(comId);
    }
}
