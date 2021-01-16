package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.managerport.dao.SpecialtyDao;
import com.zyg.jas.managerport.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyDao specialtyDao;
    @Override
    public List<Specialty> getAllSpecialty() {
        List<Specialty> specialties = this.specialtyDao.selectAllSpeCialty();
        return specialties;
    }
}
