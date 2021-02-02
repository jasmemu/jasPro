package com.zyg.jas.managerport.controller;


import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.managerport.service.CCService;
import com.zyg.jas.managerport.service.CmtService;
import com.zyg.jas.managerport.service.SpecialtyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mport/cmt")
@CrossOrigin
public class CmtContorller {
    private static final Logger logger = LoggerFactory.getLogger(CmtContorller.class);
    @Autowired
    private CmtService cmtService;

    @Autowired
    private CCService ccService;

    @Autowired
    private SpecialtyService specialtyService;


    @RequestMapping(value = "/saveCmt",method = RequestMethod.POST)  //添加一个学委
    @ResponseBody
    public String send(@RequestBody Committee committee){
       logger.info("接收到的学委信息");
        logger.info(committee.toString());
        Integer r =this.cmtService.saveCmt(committee);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }
    @RequestMapping(value = "/updateCmt",method = RequestMethod.POST)  //学委修改个人可管理的信息
    @ResponseBody
    public String updateHandler(@RequestBody Committee committee){
        Integer r =this.cmtService.saveCmtPersonal(committee);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }

    @RequestMapping(value = "/getAllCmt/{pageNo}/{pageSize}",method = RequestMethod.GET) //获取student表所有记录
    @ResponseBody
    public List<Committee> getAllCmt(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Committee> committees = this.cmtService.getAllCmt(pageNo,pageSize);
        return committees;
    }
    @RequestMapping("/getCmtTotal") //获取student表所有记录
    @ResponseBody
    public String getCmtTotal(){
        Integer cmtCout = this.cmtService.getCmtTotal();
        if (cmtCout!=null){
            return String.valueOf(cmtCout);
        }else {
            return "";
        }
    }

    @RequestMapping("/getCmtForSearch") // 根据搜索框查找一条记录
    @ResponseBody
    public List<Committee> getStuForSearchHandler(@RequestParam("speName") String speName, @RequestParam("cGrade") String cGrade, @RequestParam("cClass") String cClass){
        logger.info("iiii");
        logger.info(speName+" "+cGrade+" "+ cClass);
        Integer cmtClass = null;
        if (cClass !=null &&  !"".equals(cClass)){
            cmtClass =Integer.parseInt(cClass);
        }
        logger.info(speName+" "+cGrade+" "+ cClass);
        List<Committee> cmtList = this.cmtService.getCmtForSearch(speName,cGrade,cmtClass);
        return cmtList;
    }

    // 根据cmtId获取学委带有课程的信息
    @RequestMapping(value = "/getCmtByComId/{cmtComId}",method = RequestMethod.GET)
    @ResponseBody
    public  Committee getCmtByComIdWithHandler(@PathVariable("cmtComId") String cmtId){
        Committee committee = this.cmtService.getCmtByComIdWith(cmtId);
        return committee;
    }

    // 根据cmtId获取学委带有课程的信息
    @RequestMapping(value = "/getCmtByComIdNoWith/{cmtComId}",method = RequestMethod.GET)
    @ResponseBody
    public  Committee getCmtByComIdHandler(@PathVariable("cmtComId") String cmtId){
        Committee committee = this.cmtService.getCmtById(cmtId);
        return committee;
    }

    @RequestMapping(value = "/deleteByComId/{cmtId}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteByComIdHandler(@PathVariable("cmtId") String cmtId){
        this.cmtService.removeCmtByComId(cmtId);
    }

    @RequestMapping(value = "/dealExcel",method = RequestMethod.POST) //处理上传的Excel文件
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<Committee> cmtList = this.cmtService.dealExcelForCommittee(file);
        int sum = this.cmtService.saveCmtFromExcel(cmtList);
        return sum;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ResponseBody
    public Committee cmtLoginHandler(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword){
        Committee committee= this.cmtService.getCmtByAccount(loginAccount,loginPassword);
        return committee;
    }
}
