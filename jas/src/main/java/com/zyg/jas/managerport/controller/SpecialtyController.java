package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.common.tool.constant.JasConstant;
import com.zyg.jas.common.tool.util.ResultEntity;
import com.zyg.jas.managerport.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

@Controller
@RequestMapping("/mport/spe")
public class SpecialtyController {
    @Autowired
    private SpecialtyService specialtyService;

    //根据专业添加或跟新专业（当专业编码已经存在，执行更新。不存在，执行插入）
    @RequestMapping(value = "/update/or/add",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ResultEntity<String> updateOrAddHandler(@RequestBody Specialty specialty){
        specialtyService.updateOrAdd(specialty);
        return ResultEntity.successWithoutData();
    }

    //根据专业删除专业
    @RequestMapping(value = "/delete/by/specialty",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public ResultEntity<String> deleteBySpeNumHandler(@RequestBody List<Specialty> specialties){
        List<Specialty> consequence =specialtyService.removeSpecialty(specialties);
        if (consequence == null){
            return ResultEntity.successWithoutData();
        }else {
            return ResultEntity.failed(JasConstant.DELETE_SPECIALTY_FAILED);
        }

    }

    // 获取所有专业信息
    @RequestMapping(value = "/getspecialties")
    @ResponseBody
    @CrossOrigin
    public List<Specialty> getSpecialties(){
        return this.specialtyService.getAllSpecialty();
    }

}
