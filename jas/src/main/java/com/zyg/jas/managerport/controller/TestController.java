package com.zyg.jas.managerport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
@CrossOrigin
public class TestController {
    // 上传excel文件，存储学生信息
    @RequestMapping(value = "/t1",method = RequestMethod.POST) //处理上传的Excel文件
    @ResponseBody
    public String dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        return "ok";

    }
}
