package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Homework;
import com.zyg.jas.managerport.service.HomeworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/mport/homework")
public class HomeworkController {
    private Logger logger = LoggerFactory.getLogger(HomeworkController.class);
    @Autowired
    private HomeworkService homeworkService;

    // 根据作业id删除作业记录，返回删除记录数
   @RequestMapping(value = "/deleteByhId/{hId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer deleteByHidHandler(@PathVariable("hId") Integer hId){
        Integer i=this.homeworkService.removeJobById(hId);
        return i;
    }

    @RequestMapping(value = "/getCount/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountHandler(@PathVariable("cmtId") String cmtId){
        Integer total=this.homeworkService.getCount(cmtId);
        logger.info("数量"+total);
        return total;
    }

    @RequestMapping(value = "/getJobs/{cmtId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<Homework> getJobsHandler(@PathVariable("cmtId") String cmtId,@PathVariable("pageNo") Integer pageNo,
                                     @PathVariable("pageSize") Integer pageSize){
        return this.homeworkService.getAllJobs(cmtId,pageNo,pageSize);
    }

    @RequestMapping(value = "/saveJob",method = RequestMethod.POST)
    @ResponseBody
    public Integer saveJobHandler(@RequestParam("jobFile")MultipartFile multipartFile,@RequestParam("comId") String comId, @RequestParam("courseId")String courseId,
                                  @RequestParam("endDate")Date endDate,@RequestParam("mark") String mark)  {
        Homework homework = new Homework();
        homework.setCourseId(courseId);
        homework.setComId(comId);
        homework.setEndDate(endDate);
        homework.setMark(mark);
        logger.info("学委"+comId);
        logger.info("课程"+courseId);
        logger.info("文件"+multipartFile.getOriginalFilename());

        try {
            return this.homeworkService.saveJob(multipartFile,homework);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
