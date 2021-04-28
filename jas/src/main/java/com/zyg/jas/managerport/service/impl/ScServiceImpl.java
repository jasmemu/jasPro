package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Sc;
import com.zyg.jas.managerport.dao.ScDao;
import com.zyg.jas.managerport.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScServiceImpl implements ScService {
    @Autowired
    private ScDao scDao;

    @Override
    public int AddSc(Sc sc) {
        return this.scDao.insertToSc(sc);
    }

    @Override
    public List<String> getCourseBySno(String sNo) {
        return this.scDao.selectCourseBySno(sNo);
    }

    @Override
    public List<String> getCourseIdBySno(String sNo) {
        return this.scDao.selectCourseIdBySno(sNo);
    }
}
