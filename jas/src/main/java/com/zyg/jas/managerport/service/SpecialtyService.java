package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Specialty;

import java.util.List;

public interface SpecialtyService {
     List<Specialty> getAllSpecialty();

    List<Specialty> removeSpecialty(List<Specialty> specialties);

    void updateOrAdd(Specialty specialty);
}
