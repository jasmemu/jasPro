package com.zyg.jas.managerport.controller;


import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Teacher;
import com.zyg.jas.managerport.service.TcService;
import com.zyg.jas.managerport.service.TeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mport/tea")
@CrossOrigin
public class TeaController {

    @Autowired
    private TeaService teaService;

    @Autowired
    private TcService tcService;

    //批量删除教师,返回删除的记录条数
    @RequestMapping(value = "/delete/byBatch",method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteStuByBatchHandler(@RequestBody List<Teacher> teacherList) {
        return teaService.removeByBatch(teacherList);
    }
    // 添加一个老师
    @RequestMapping("/saveTea")
    @ResponseBody
    public String send(@RequestBody Teacher teacher){
        System.out.println("接收到的教师信息");
        System.out.println(teacher);
        int r = this.teaService.addTeacher(teacher);
        if (r==1){
            return "success";
        }else{
            return "false";
        }
    }

    // 获取老师信息，分页显示
    @RequestMapping("/getAllTea/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Teacher> getAllTea(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Teacher> students = this.teaService.getAllTea(pageNo,pageSize);
        return students;
    }

    // 获取老师表的记录条数
    @RequestMapping("/getTeaTotal")
    @ResponseBody
    public String getTeaTotal(){
        Integer teaCount = this.teaService.getTeaTotal();
        if (teaCount!=null){
            return String.valueOf(teaCount);
        }else {
            return "";
        }

    }

    // 根据教师名称搜索教师
    @RequestMapping("/getTeaForSearch")
    @ResponseBody
    public List< Teacher> getTeaForSearch(@RequestBody Teacher teacher){
        List<Teacher> teaList = this.teaService.getTeaForSearch(teacher);
        if (teaList.size()>0){
           for (int j=0;j<teaList.size();j++){
               List<String> courses = this.tcService.getCourseByTno(teaList.get(j).gettNo()); //查询到的时该编号教师所教授的所有课程名称
               List<Course> c = new ArrayList<>();
               for (int i=0;i<courses.size();i++){
                   Course cou = new Course();
                   cou.setName(courses.get(i));
                   c.add(cou);
               }
               teaList.get(j).setCourses(c);
           }


//            List<String> courses = this.tcService.getCourseByTno(tea.gettNo()); //查询到的时该编号教师所教授的所有课程名称
//            List<Course> c = new ArrayList<>();
//            for (int i=0;i<courses.size();i++){
//                Course cou = new Course();
//                cou.setName(courses.get(i));
//                c.add(cou);
//            }
//            tea.setCourses(c);
        }
        return  teaList;
    }

    // 根据教师编号获取教师并带有课程
    @RequestMapping("/getTeaByTno/{tNo}")
    @ResponseBody
    public Teacher getTeaByTno(@PathVariable("tNo") String tNo){
        Teacher teacher = this.teaService.getTeaByTno(tNo);
        if (teacher!=null){
            List<Course> courseList = new ArrayList<>();
            List<String> courses = this.tcService.getCourseByTno(teacher.gettNo());
            for (int i=0;i<courses.size();i++){
                Course c = new Course();
                c.setName(courses.get(i));
                courseList.add(c);
            }
            teacher.setCourses(courseList);
        }
        return teacher;
    }

    //根据教师编号删除一条记录
    @RequestMapping("/deleteByTno/{tNo}")
    @ResponseBody
    public void deleteByTno(@PathVariable("tNo") String tno){
        this.teaService.deleteTeaByTno(tno);
    }

    // 处理上传的Excel文件，存储教师信息
    @RequestMapping("/dealExcel")
    @ResponseBody
    public int dealExcel(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("接收到的Excel");
        System.out.println(file.getOriginalFilename());
        List<Teacher> teachers = this.teaService.dealExcelForTeas(file);
        int sum  = this.teaService.saveTeaFromExcel(teachers);
        return sum;

    }
}
