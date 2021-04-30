package com.zyg.jas.studentport.service.impl;

import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.studentport.service.SpecialtyService2;
import com.zyg.jas.studentport.dao.ClassesDao2;
import com.zyg.jas.studentport.dao.SpecialtyDao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialtyServiceImpl2 implements SpecialtyService2 {
    @Autowired
    private SpecialtyDao2 specialtyDao;

    @Autowired
    private ClassesDao2 classesDao;
    @Override
    public List<Specialty> getAllSpecialty() {
        List<Specialty> specialties = this.specialtyDao.selectAllSpeCialty();
        return specialties;
    }

    @Override
    public List<Specialty> removeSpecialty(List<Specialty> specialties) {
        List<Specialty> noDeleteSpecialties =new ArrayList<>();
        boolean flag= false;
        // 删除专业时，要先判断是否有该专业的班级，有的话不执行删除
        for (int i=0;i<specialties.size();i++){
           Specialty specialty=specialtyDao.selectSpecialtyBySpeNum(specialties.get(i).getSpeNum());
           Integer consequence = classesDao.selectClassesBySpeId(specialty.getSpeId());
           if (consequence==0){
               specialtyDao.deleteSpecialty(specialties.get(i).getSpeNum());
           }else {
               noDeleteSpecialties.add(specialties.get(i));
               flag = true;
           }
        }
        if (flag){
            return noDeleteSpecialties;
        }else {
            return null;
        }
    }

    @Override
    public void updateOrAdd(Specialty specialty) {
        List<Specialty> specialtyList = specialtyDao.selectAllSpeCialty();
        boolean flag = false;
        for (int i=0;i<specialtyList.size();i++){
            if (specialty.getSpeNum().equals(specialtyList.get(i).getSpeNum())){
                flag = true;
                break;
            }
        }
        if (flag == true){
            specialtyDao.updateBySpeNum(specialty);
        }else {
            specialtyDao.addSpecialty(specialty);
        }
    }
}
