package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.managerport.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mport/spe")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    @RequestMapping("/getspecialties")
    @ResponseBody
    @CrossOrigin
    public List<Specialty> getSpecialties(){
        return this.specialtyService.getAllSpecialty();
    }

}
