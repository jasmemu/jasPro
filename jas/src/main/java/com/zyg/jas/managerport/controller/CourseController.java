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

    @RequestMapping("/saveCourse")  //添加一个课程信息
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

    @RequestMapping("/getAllCourse/{pageNo}/{pageSize}") //获取student表所有记录
    @ResponseBody
    public List<Course> getAllCourse(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Course> courses = this.courseService.getAllCourse(pageNo,pageSize);
        return courses;
    }
    @RequestMapping("/getCourseTotal") //获取student表所有记录
    @ResponseBody
    public String getCourseTotal(){
        Integer courseCount = this.courseService.getCourseTotal();
        if (courseCount!=null){
            return String.valueOf(courseCount);
        }else {
            return "";
        }
    }

    @RequestMapping("/getCourseById/{courseId}")
    @ResponseBody
    public Course getCourseById(@PathVariable("courseId") String courseId){
        Course course = this.courseService.getCourseById(courseId);

        return course;
    }

    @RequestMapping("/deleteByCourseId/{courseId}") //根据sNo删除一条记录
    @ResponseBody
    public void deleteByCourseId(@PathVariable("courseId") String courseId){
        this.courseService.deleteCourseById(courseId);
    }

    @RequestMapping("/getCourseForSearch")
    @ResponseBody
    public Course getCourseForSearch(@RequestBody Course course){
        Course c = this.courseService.getCourseForSearch(course);
        return c;
    }

    @RequestMapping("/dealExcelToCourse") //根据sNo删除一条记录
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        List<Course> courseList = this.courseService.dealExcelForCourse(file);
        int sum = this.courseService.saveCourseFromExcel(courseList);
        return sum;

    }
}
