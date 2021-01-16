package com.zyg.jas.managerport.controller;


import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.common.tool.util.MultipartFileToFile;
import com.zyg.jas.managerport.service.impl.StudentServiceImpl;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/mport/stu")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping("/savestu")  //添加一个学生
    @ResponseBody
    public String send(@RequestBody Student student){
        int r =this.studentService.addStudent(student);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }


    @RequestMapping("/getAllStu/{pageNo}/{pageSize}") //获取student表所有记录
    @ResponseBody
    public List<Student> getAllStu(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        List<Student> students = this.studentService.getAllStu(pageNo,pageSize);
        System.out.println("学生");
        System.out.println(students);
        return students;
    }
    @RequestMapping("/getStuTotal") //获取student表所有记录
    @ResponseBody
    public String getStuTotal(){
        return String.valueOf(this.studentService.getStuTotal());
    }

    @RequestMapping("/getStuBySno/{sNo}") //获取student表所有记录
    @ResponseBody
    public Student getStuBySno(@PathVariable("sNo") String sNo){
        Student student = this.studentService.getStuBySno(sNo);
        return student;
    }

    @RequestMapping("/deleteBySno/{sNo}") //根据sNo删除一条记录
    @ResponseBody
    public void deleteBySno(@PathVariable("sNo") String sno){
        this.studentService.deleteStuBySno(sno);
    }

    @RequestMapping("/getStuForSearch") //根据sNo删除一条记录
    @ResponseBody
    public Student getStuForSearch(@RequestBody Student student){
        Student stu = this.studentService.getStuForSearch(student);
        return stu;
    }

    @RequestMapping("/dealExcel") //根据sNo删除一条记录
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        List<Student> studentList = this.studentService.dealExcelForStudents(file);
        int sum = this.studentService.saveStuFromExcel(studentList);
        return sum;

    }
}
