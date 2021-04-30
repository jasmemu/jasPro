package com.zyg.jas.studentport.service.impl;

import com.zyg.jas.common.pojo.Sc;
import com.zyg.jas.studentport.dao.ScDao2;
import com.zyg.jas.studentport.service.ScService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScServiceImpl2 implements ScService2 {
    @Autowired
    private ScDao2 scDao;

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
