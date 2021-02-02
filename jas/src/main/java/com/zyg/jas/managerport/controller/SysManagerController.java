package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.SysManager;
import com.zyg.jas.managerport.service.SysManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/mport/sys")
@CrossOrigin
public class SysManagerController {

    private static final Logger logger = LoggerFactory.getLogger(SysManagerController.class);

    @Autowired
    private SysManagerService sysManagerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public SysManager sysLogin(@RequestParam("loginAccount") String loginAccount, @RequestParam("loginPassword") String loginPassword,HttpServletResponse response ){
        logger.info("登录信息");
        logger.info(loginAccount+"  "+ loginPassword);
        SysManager sysManager = this.sysManagerService.getSysManagerForLogin(loginAccount,loginPassword);
        return sysManager;
    }

    @RequestMapping(value = "getSysByAccount/{account}",method = RequestMethod.GET)
    @ResponseBody
    public SysManager getSysByAccountHandler(@PathVariable("account") String account){
        return this.sysManagerService.getSysManagerByAccount(account);
    }

    @RequestMapping(value = "/saveSys",method = RequestMethod.POST)
    @ResponseBody
    public String saveSysHandler(@RequestBody SysManager sysManager){
        System.out.println("接收到的管理员信息");
        System.out.println(sysManager);
        Integer r=this.sysManagerService.saveSys(sysManager);
        if (r==1){
            return "success";
        }else {
            return "false";
        }
    }

}
