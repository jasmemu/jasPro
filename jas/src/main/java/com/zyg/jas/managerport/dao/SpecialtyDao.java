package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Specialty;

import java.util.List;

public interface SpecialtyDao {
    public List<Specialty> selectAllSpeCialty();

    Specialty selectSpeByName(String name);

    Specialty selectSpeBySpeId(Integer speId);

    void deleteSpecialty(String speId);

    void updateBySpeNum(Specialty specialty);

    void addSpecialty(Specialty specialty);

    Specialty selectSpecialtyBySpeNum(String speNum);
}
