package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.SysManager;
import com.zyg.jas.common.tool.util.ResultEntity;
import com.zyg.jas.managerport.service.SysManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mport/sys")
@CrossOrigin
public class SysManagerController {

    private static final Logger logger = LoggerFactory.getLogger(SysManagerController.class);

    @Value("${cmtExcelUrl}")
    private String cmtExcelUrl;
    @Value("${stuExcelUrl}")
    private String stuExcelUrl;
    @Value("${teaExcelUrl}")
    private String teaExcelUrl;
    @Value("${courseExcelUrl}")
    private String courseExcelUrl;


    @Autowired
    private SysManagerService sysManagerService;

    // 根据学号修改密码为newPWD
    @RequestMapping(value = "/set/pwd/by/authcode",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> resetPwdByAuthCodeHandler(@RequestParam("account") String account, @RequestParam("newPWD") String newPWD){
        sysManagerService.resetPwdByAuthCode(account,newPWD);
        return ResultEntity.successWithData(account+newPWD);
    }
    //根据填写的邮箱和账号获取验证码
    @RequestMapping(value="/get/auth/code",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<String> getAuthCodeHandler(@RequestParam("email") String email,@RequestParam("account") String accout){
        System.out.println("接收到的邮箱是："+email+"；系统管理员账号:"+accout);
        String authCode = sysManagerService.getAuthCode(email,accout);
        return ResultEntity.successWithData(authCode);
    }

    @RequestMapping(value = "/downloadExcel",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,String>> downloadExcelHandler(){
        Map<String,String> map = new HashMap<>();
        List<Map<String,String>> excelList = new ArrayList<>();
        map.put("cmtExcelUrl",cmtExcelUrl);
       // excelList.add(map);
        map.put("stuExcelUrl",stuExcelUrl);
       // excelList.add(map);
        map.put("teaExcelUrl",teaExcelUrl);
        //excelList.add(map);
        map.put("courseExcelUrl",courseExcelUrl);
        excelList.add(map);
        System.out.println("excel有");
        System.out.println(excelList);
        return excelList;
    }

    // 系统管理员登录使用
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public SysManager sysLogin(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword,HttpServletResponse response ){
        logger.info("登录信息");
        logger.info(loginAccount+"  "+ loginPassword);
        SysManager sysManager = this.sysManagerService.getSysManagerForLogin(loginAccount,loginPassword);
        return sysManager;
    }

    // 根据账号获取系统管理员详细信息
    @RequestMapping(value = "getSysByAccount/{account}",method = RequestMethod.GET)
    @ResponseBody
    public SysManager getSysByAccountHandler(@PathVariable("account") String account){
        return this.sysManagerService.getSysManagerByAccount(account);
    }

    // 跟新系统管理员信息
    @RequestMapping(value = "/saveSys",method = RequestMethod.POST)
    @ResponseBody
    public String saveSysHandler(@RequestBody SysManager sysManager){
        System.out.println("接收到的管理员信息");
        System.out.println(sysManager.toStringWithAuthCode());
        Integer r=this.sysManagerService.saveSys(sysManager);
        if (r==1){
            return "success";
        }else {
            return "false";
        }
    }

}
