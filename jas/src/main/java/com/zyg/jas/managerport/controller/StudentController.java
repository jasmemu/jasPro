package com.zyg.jas.managerport.controller;


import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Sc;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.common.tool.util.MultipartFileToFile;
import com.zyg.jas.managerport.service.ScService;
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
    @Autowired
    private ScService scService;

    @RequestMapping(value = "/test/{t}",method = RequestMethod.GET)
    @ResponseBody
    public String g(@PathVariable("t") Integer t) throws Exception{
        System.out.println(t.toString());
        if (t == 1){
            String s = null;
            System.out.println(s.length());
        }else if (t==2){
            String s2[] = {"1","2"};
            System.out.println(s2[5]);
        }else if (t==3){
            System.out.println(10/0);
        }

        return "success";
    }
    @RequestMapping(value = "/savestu",method = RequestMethod.POST)  //添加一个学生
    @ResponseBody
    public String send(@RequestBody Student student){
        System.out.println("接收到的学生信息");
        System.out.println(student);
        Integer r =this.studentService.addStudent(student);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }


    @RequestMapping(value = "/getAllStu/{pageNo}/{pageSize}",method = RequestMethod.GET) //获取student表所有记录
    @ResponseBody
    public List<Student> getAllStu(@PathVariable("pageNo") Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        List<Student> students = this.studentService.getAllStu(pageNo,pageSize);
        return students;
    }
    @RequestMapping("/getStuTotal") //获取student表所有记录
    @ResponseBody
    public String getStuTotal(){
        Integer stuCout = this.studentService.getStuTotal();
        if (stuCout!=null){
            return String.valueOf(stuCout);
        }else {
            return "";
        }

    }

    @RequestMapping(value = "/getStuBySno/{sNo}",method = RequestMethod.GET) //根据学号获取student并带有课程
    @ResponseBody
    public Student getStuBySno(@PathVariable("sNo") String sNo){
        Student student = this.studentService.getStuBySno(sNo);
        if (student!=null){
            List<Course> courseList = new ArrayList<>();
            List<String> courses = this.scService.getCourseBySno(student.getsNo());//.tcService.getCourseByTno(teacher.gettNo());
            for (int i=0;i<courses.size();i++){
                Course c = new Course();
                c.setName(courses.get(i));
                courseList.add(c);
            }
            student.setCourses(courseList);
        }
        return student;
    }

    @RequestMapping(value = "/deleteBySno/{sNo}",method = RequestMethod.DELETE) //根据sNo删除一条记录
    @ResponseBody
    public void deleteBySno(@PathVariable("sNo") String sno){
        this.studentService.deleteStuBySno(sno);
    }

    @RequestMapping("/getStuForSearch") //根据搜索框查找一条记录
    @ResponseBody
    public List<Student> getStuForSearch(@RequestBody Student student){
        List<Student> stuList = this.studentService.getStuForSearch(student);
        if (stuList.size()>0){
            for (int j=0;j<stuList.size();j++){
                List<String> courses = this.scService.getCourseBySno(stuList.get(j).getsNo());//.tcService.getCourseByTno(tea.gettNo()); //查询到的时该编号教师所教授的所有课程名称
                List<Course> c = new ArrayList<>();
                for (int i=0;i<courses.size();i++){
                    Course cou = new Course();
                    cou.setName(courses.get(i));
                    c.add(cou);
                }
                stuList.get(j).setCourses(c);
            }
//            List<String> courses = this.scService.getCourseBySno(stu.getsNo());//.tcService.getCourseByTno(tea.gettNo()); //查询到的时该编号教师所教授的所有课程名称
//            List<Course> c = new ArrayList<>();
//            for (int i=0;i<courses.size();i++){
//                Course cou = new Course();
//                cou.setName(courses.get(i));
//                c.add(cou);
//            }
//            stu.setCourses(c);
       }
        return stuList;
    }

    @RequestMapping(value = "/dealExcel",method = RequestMethod.POST) //处理上传的Excel文件
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        List<Student> studentList = this.studentService.dealExcelForStudents(file);
        int sum = this.studentService.saveStuFromExcel(studentList);
        return sum;

    }
}
