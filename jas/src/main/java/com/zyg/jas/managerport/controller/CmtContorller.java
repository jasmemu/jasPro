package com.zyg.jas.managerport.controller;


import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.CC;
import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.constant.JasConstant;
import com.zyg.jas.common.tool.util.JedisPoolUtil;
import com.zyg.jas.common.tool.util.RedisUtil;
import com.zyg.jas.common.tool.util.ResultEntity;
import com.zyg.jas.managerport.service.CCService;
import com.zyg.jas.managerport.service.CmtService;
import com.zyg.jas.managerport.service.SpecialtyService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // 下载生成的excel(未提交的学生信息)
    @RequestMapping(value = "/download/no/submitted",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> downloadForNoSubmitHandler(@RequestParam("cmtId") String cmtId,@RequestParam("courseId") String courseId,@RequestParam("name") String courseName) {
        System.out.println("接收到的："+cmtId+"-"+courseId+"-"+courseName);
        String excelUrl = cmtService.getNoSubmitForExcel(cmtId,courseId,courseName);
        return ResultEntity.successWithData(excelUrl);
    }

    // 下载生成的excel(已提交的学生信息)
    @RequestMapping(value = "/download/submitted",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> download(@RequestParam("cmtId") String cmtId,@RequestParam("courseId") String courseId,@RequestParam("name") String courseName) {
       String excelUrl = cmtService.getStatisticsSubmitForExcel(cmtId,courseId,courseName);
        return ResultEntity.successWithData(excelUrl);
    }

    // 根据学委学号和课程编号统计提交过作业的学生(21.4.14)
    @RequestMapping(value = "/statistics/by/{cmtId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Student>> statisticsByCourseId(@PathVariable("courseId") String courseId,@PathVariable("cmtId") String cmtId){

       List<Student> list= cmtService.getStatisticsSubmit(cmtId,courseId);
     return ResultEntity.successWithData(list);
    }

    // 根据学委学号和课程编号统计学生应该提交的作业(21.4.14)
    @RequestMapping(value = "/statistics/should/{cmtId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Student>> statisticsShouldSubByCourseId(@PathVariable("courseId") String courseId,@PathVariable("cmtId") String cmtId){
        List<Student> list= cmtService.getShouldSubmit(cmtId,courseId);
        return ResultEntity.successWithData(list);
    }

    // 没有提交
    @RequestMapping(value = "/statistics/no/{cmtId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<String>> statisticsNoSubByCourseId(@PathVariable("courseId") String courseId,@PathVariable("cmtId") String cmtId){
        List<String> list= cmtService.getNoSubmit(cmtId,courseId);
        return ResultEntity.successWithData(list);
    }



    // 根据学号修改密码为newPWD  (21.4.13)
    @RequestMapping(value = "/set/pwd/by/authcode",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> resetPwdByAuthCodeHandler(@RequestParam("account") String account,@RequestParam("newPWD") String newPWD){
        cmtService.resetPwdByAuthCode(account,newPWD);
        System.out.println("设置新密码："+account+"-"+newPWD);
        return ResultEntity.successWithData(account+newPWD);
    }
    //根据填写的邮箱获取验证码  (21.4.13)
    @RequestMapping(value="/get/auth/code",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> getAuthCodeHandler(@RequestParam("email") String email,@RequestParam("account") String stuNo){
        System.out.println("接收到的邮箱是："+email+"；学号:"+stuNo);
        String authCode = cmtService.getAuthCode(email,stuNo);
        return ResultEntity.successWithData(authCode);
    }

    @RequestMapping(value = "/delete/byBatch",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteStuByBatchHandler(@RequestBody List<Committee> committeeList) {
        return cmtService.removeByBatch(committeeList);
    }


    @RequestMapping(value = "/getCourseForCmtByCmtIdWith/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public List<CC> getCourseForCmtByCmtIdWith(@PathVariable("cmtId") String cmtId){
        return ccService.getCourseForCmtByCmtIdWith(cmtId);
    }
    // 添加学委
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
    // 修改学委信息
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

    // 学委信息分页显示
    @RequestMapping(value = "/getAllCmt/{pageNo}/{pageSize}",method = RequestMethod.GET) //获取student表所有记录
    @ResponseBody
    public List<Committee> getAllCmt(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Committee> committees = this.cmtService.getAllCmt(pageNo,pageSize);
        return committees;
    }
    // 获取学委总记录数
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

    // 根据speName、cGrade、cClass搜索学委
    @RequestMapping("/getCmtForSearch") // 根据搜索框查找一条记录
    @ResponseBody
    public List<Committee> getStuForSearchHandler(@RequestParam("speName") String speName, @RequestParam("cGrade") String cGrade, @RequestParam("cClass") String cClass){
        Integer cmtClass = null;
        if (cClass !=null &&  !"".equals(cClass)){
            cmtClass =Integer.parseInt(cClass);
        }
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

    // 根据cmtId获取学委信息
    @RequestMapping(value = "/getCmtByComIdNoWith/{cmtComId}",method = RequestMethod.GET)
    @ResponseBody
    public  Committee getCmtByComIdHandler(@PathVariable("cmtComId") String cmtId){
        Committee committee = this.cmtService.getCmtById(cmtId);
        return committee;
    }

    // 根据学委学号删去学委
    @RequestMapping(value = "/deleteByComId/{cmtId}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteByComIdHandler(@PathVariable("cmtId") String cmtId){
        this.cmtService.removeCmtByComId(cmtId);
    }

    // 上传excel中的学委信息
    @RequestMapping(value = "/dealExcel",method = RequestMethod.POST) //处理上传的Excel文件
    @ResponseBody
    public Map<String, String> dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        List<Committee> cmtList = this.cmtService.dealExcelForCommittee(file);
        logger.info("学委行数："+ cmtList.size());
        String status = cmtList.get(0).getName();
        Map<String,String> map = new HashMap<>();
        if ("error".equals(status)){
            map.put("status",cmtList.get(0).getPassword());
            return map;
        }else {
            this.cmtService.saveCmtFromExcel(cmtList);
            map.put("status","success");
            return map;
        }
    }

    // 根据loginAccount、loginPassword学委登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Committee cmtLoginHandler(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword){
        Committee committee= this.cmtService.getCmtByAccount(loginAccount,loginPassword);
        // 执行sql时如果查不到会报空指针异常，这里的committee并不是null
        return committee;
    }
}
