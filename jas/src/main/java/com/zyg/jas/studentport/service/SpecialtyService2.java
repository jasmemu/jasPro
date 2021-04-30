package com.zyg.jas.studentport.service;

import com.zyg.jas.common.pojo.Specialty;

import java.util.List;

public interface SpecialtyService2 {
     List<Specialty> getAllSpecialty();

    List<Specialty> removeSpecialty(List<Specialty> specialties);

    void updateOrAdd(Specialty specialty);
}
