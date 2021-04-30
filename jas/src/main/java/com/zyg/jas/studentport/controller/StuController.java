package com.zyg.jas.studentport.controller;

import com.zyg.jas.common.pojo.*;
import com.zyg.jas.studentport.service.CourseService2;
import com.zyg.jas.studentport.service.HomeworkService2;
import com.zyg.jas.studentport.service.LeaveMessageService2;
import com.zyg.jas.studentport.service.ScService2;
import com.zyg.jas.studentport.service.StuService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sport/student")
@CrossOrigin
public class StuController {
    private static final Logger logger = LoggerFactory.getLogger(StuController.class);
    @Autowired
    private StuService2 stuService;
    @Autowired
    private ScService2 scService;
    @Autowired
    private CourseService2 courseService;
    @Autowired
    private HomeworkService2 homeworkService;
    @Autowired
    private LeaveMessageService2 leaveMessageService;

    //根据loginAccount，password登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Student studentLoginHandler(@RequestParam("loginAccount") String loginAccount , @RequestParam("password") String password){
        Student student = this.stuService.getStudentByAccount(loginAccount,password);
        return student;
    }

    //根据sNo获取学生信息
    @RequestMapping(value = "/getStuBySnoWith/{sno}",method = RequestMethod.GET)
    @ResponseBody
    public Student getStuBySnoHandler(@PathVariable("sno") String sno){
        Student student = this.stuService.getStudentById(sno);
        return student;
    }


    //通过学生信息与committee的信息比对，确定学委id并插入一条新的message
    @RequestMapping(value = "/getComidByStudent/{sno}",method = RequestMethod.GET)
    @ResponseBody
    public String getComidByStudent(@PathVariable("sno") String sno) {
        Student student = this.stuService.getStudentById(sno);
//        String comid = stuService.getComid(student.getSpeId(),student.getsGrade(),student.getsClass());
//        System.out.println(student);
//        System.out.println(student.getSpeId());
//        System.out.println(student.getsGrade());
//        System.out.println(student.getsClass());
        Committee committee = this.stuService.getComid(student.getSpeId(),student.getsGrade(),student.getsClass());
        String comid = committee.getComId();
        System.out.println("hello"+committee);
       return comid;
    }


    //插入一条新的message
//    @RequestMapping(value = "/insertMessage/{comid}/{stuid}/{content}/{date}",method = RequestMethod.GET)
//    @ResponseBody
////    @CrossOrigin
//    public void insertMessage(@RequestParam("comid") String comId, @RequestParam("stuid") String sNo, @RequestParam("content") String leaveMessage,@RequestParam("date") Date lmDate){
//        System.out.println(comId);
//        System.out.println(sNo);
//        System.out.println(leaveMessage);
//        System.out.println(lmDate);
//
//
//        this.stuService.insertMessage(comId,sNo,leaveMessage,lmDate);
//    }
    @RequestMapping(value = "/insertMessage",method = RequestMethod.POST)
    @ResponseBody
    public void insertMessage(@RequestParam("comid") String comid, @RequestParam("stuid") String stuid, @RequestParam("content") String content,@RequestParam("date") Date date){
        System.out.println(comid);
        System.out.println(stuid);
        System.out.println(content);
        System.out.println(date);


        this.stuService.insertMessage(comid,stuid,content,date);
    }





    //修改学生信息
    @RequestMapping(value = "/updateStu",method = RequestMethod.POST)
    @ResponseBody
    public String updateHandler(@RequestBody Student student){
        Integer integer = this.stuService.saveStudentPersonal(student);
        if(integer == 1){
            return "success";
        }else{
            return "false";
        }
    }

    //根据学号获取student并带有课程信息
    @RequestMapping(value = "/getCourseBySno/{sNo}")
    @ResponseBody
    public List<Course> getCourseBySno(@PathVariable("sNo") String sNo){
        Student student = this.stuService.getStudentById(sNo);
        if(student.getsNo()!=null){
            List<Course> courseList = new ArrayList<>();
            List<String> courses = this.scService.getCourseBySno(sNo);
            List<String> courseId = this.scService.getCourseIdBySno(sNo);

            System.out.println(courses);
            for (int i=0;i<courses.size();i++){
                Course courseTable = this.courseService.getCourseByName(courses.get(i));

                Course c = new Course();
                c.setName(courses.get(i));
//                c.setCourseId(courseId.get(i));
                c.setCourseId(courseTable.getCourseId());
                c.setCredit(courseTable.getCredit());
                c.setBeginDate(courseTable.getBeginDate());
                c.setEndDate(courseTable.getEndDate());
                c.setPeriod(courseTable.getPeriod());
                System.out.println(courses.get(i));
                courseList.add(c);
                System.out.println(c);
            }
            student.setCourses(courseList);
            System.out.println(courseList);
            return courseList;
        }
        else
            return null;
        //控制台输出一下
    }

    //根据courseid查询当前的homework具体信息
    @RequestMapping(value = "/findMyHomework/{courseid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Homework> getMyHomework(@PathVariable("courseid") String courseid){
        List<Homework> homeworkList = this.homeworkService.getHomeworkByCourseid(courseid);
        System.out.println(homeworkList);
        return homeworkList;
    }
//根据学生sno获取所有message信息
    @RequestMapping(value = "/getMyResponse/{sno}",method = RequestMethod.GET)
    @ResponseBody
    public List<LeaveMessage> getMyResponse(@PathVariable("sno") String sno){
        List<LeaveMessage> leaveMessages = this.leaveMessageService.selectMessageBySno(sno);
        System.out.println(leaveMessages);
        return leaveMessages;
    }




//下面是关于获取公告的

//    // 根据账号获取学生详细信息
//    @RequestMapping(value = "getStuByAccount/{account}",method = RequestMethod.GET)
//    @ResponseBody
//    public StuManager getStuByAccountHandler(@PathVariable("account") String account){
//        return this.stuService.getSysManagerByAccount(account);
//    }



}
