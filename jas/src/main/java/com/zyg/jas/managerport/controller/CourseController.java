package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.managerport.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Controller
@RequestMapping("/mport/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    // 获取所有课程的名称
    @RequestMapping(value = "/getNames",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getNamesHandler(){
        return this.courseService.getForName();
    }

    //添加一个课程信息
    @RequestMapping("/saveCourse")
    @ResponseBody
    public String saveCourseFromForm(@RequestBody Course course){
        System.out.println("课程信息");
        System.out.println(course);
        int r = this.courseService.addCourse(course);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }

    //获取course表所有记录，分页
    @RequestMapping("/getAllCourse/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Course> getAllCourse(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Course> courses = this.courseService.getAllCourse(pageNo,pageSize);
        return courses;
    }
    //获取course表记录数量
    @RequestMapping("/getCourseTotal")
    @ResponseBody
    public String getCourseTotal(){
        Integer courseCount = this.courseService.getCourseTotal();
        if (courseCount!=null){
            return String.valueOf(courseCount);
        }else {
            return "";
        }
    }

    // 根据courseId获取Course
    @RequestMapping("/getCourseById/{courseId}")
    @ResponseBody
    public Course getCourseById(@PathVariable("courseId") String courseId){
        Course course = this.courseService.getCourseById(courseId);

        return course;
    }

    //根据courseId删除一条记录
    @RequestMapping("/deleteByCourseId/{courseId}")
    @ResponseBody
    public void deleteByCourseId(@PathVariable("courseId") String courseId){
        this.courseService.deleteCourseById(courseId);
    }

    // 根据课程名称查找Course
    @RequestMapping("/getCourseForSearch")
    @ResponseBody
    public Course getCourseForSearch(@RequestBody Course course){
        Course c = this.courseService.getCourseForSearch(course);
        return c;
    }

    // 上传excel文件，获取其中的Course对象存储到数据库
    @RequestMapping("/dealExcelToCourse")
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        List<Course> courseList = this.courseService.dealExcelForCourse(file);
        int sum = this.courseService.saveCourseFromExcel(courseList);
        return sum;

    }
}
