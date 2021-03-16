package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.managerport.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/mport/score")
@CrossOrigin
public class ScoreController {
    @Autowired
    private ScoreService scoreService;


    // 根据作业id获取已提交的作业人数和未提交的作业人数
    @RequestMapping(value = "/jobSubmitStatus/{jobId}" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Integer> getJobSubmitStatusByJobId(@PathVariable("jobId") Integer jobId){
        List<Student> s1=scoreService.getStudentForSubmitJob(jobId);
        List<Student> s2=scoreService.getAllStudentForSubmitJob(jobId);
        Integer noCommittedCount = s2.size() - s1.size();
        Integer committedCount = s1.size();
        Map<String,Integer> map =new HashMap<>();
        map.put("noCommittedCount",noCommittedCount);
        map.put("committedCount",committedCount);
        return map;
    }
    // 根据作业id获取已提交作业的学生学号和姓名、手机号
    @RequestMapping(value = "/jobSubmitDetail/{jobId}" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getJobSubmitDetail(@PathVariable("jobId") Integer jobId){
        List<Student> s1=scoreService.getStudentForSubmitJob(jobId);
        return s1;
    }
    // 根据作业id获取没有提交作业的学生学号和姓名、手机号
    @RequestMapping(value = "/jobNoSubmitDetail/{jobId}" ,method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getJobNoSubmitDetail(@PathVariable("jobId") Integer jobId){
        List<Student> list = scoreService.getStudentForNoSubmitJob(jobId);
        return list;
    }

    // 根据学生提交的作业id为作业打分
    @RequestMapping(value = "/updateScore",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateScoreHandler(@RequestParam(value = "hId",required = true) Integer hId,
                                                 @RequestParam(value = "score",required = true) double score,
                                                 @RequestParam(value = "comment",required = true) String comment){
        return   scoreService.updateScoreById(hId,score,comment);

    }


    // 根据学委学号、课程编号、作业名称，作业是否已打分查询该学委在该课程中学生提交的作业的数量
    @RequestMapping(value = "/searchCount",method = RequestMethod.POST)
    @ResponseBody
    public Integer getScoreCountForSearchHandler(@RequestParam(value = "cmtId",required = true) String cmtId,
                                                 @RequestParam(value = "hName",required = true) String hName,
                                                 @RequestParam(value = "commit",required = false) String commit,
                                                 @RequestParam("courseId") String courseId){
        Map<String,String> map = new HashMap<>();
        map.put("cmtId",cmtId);
        String reHname= hName.trim();
        map.put("hName",reHname);
        map.put("commit",commit);
        map.put("courseId",courseId);
        return   scoreService.getCountForSearch(map);

    }

    // 根据学委学号、课程编号、作业名称，作业是否已打分查询该学委在该课程中学生提交的作业记录，分页显示
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public List<Score> getScorForSearchHandler(@RequestParam(value = "cmtId",required = true) String cmtId,
                                               @RequestParam(value = "hName",required = true) String hName,
                                               @RequestParam(value = "commit",required = false) String commit,
                                               @RequestParam(value = "courseId",required = true) String courseId,
                                               @RequestParam(value = "pageNo",required = true) Integer pageNo,
                                               @RequestParam(value = "pageSize",required = true) Integer pageSize){
        String reHname = hName.trim();
      return   scoreService.getScreForSearch(cmtId,reHname,commit,courseId,pageNo,pageSize);

    }

    // 根据学委学号和课程编号查询该学委在该课程中学生提交的作业记录，分页显示
    @RequestMapping(value = "/get/score/{cmtId}/{courseId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<Score> getScoreHandler(@PathVariable("cmtId") String cmtId,@PathVariable("courseId") String courseId,@PathVariable("pageNo") Integer pageNo,
                                       @PathVariable("pageSize") Integer pageSize){
        return scoreService.getScoresByCmtId(cmtId,courseId,pageNo,pageSize);
    }

    // 根据学委学号和课程编号查询该学委在该课程中学生提交的作业的数量
    @RequestMapping(value = "/get/count/{cmtId}/{courseId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountHandler(@PathVariable("cmtId") String cmtId,@PathVariable("courseId") String courseId){
        return scoreService.getCount(cmtId,courseId);
    }
}
