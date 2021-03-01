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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Controller
@CrossOrigin
@RequestMapping("/mport/homework")
public class HomeworkController {
    private Logger logger = LoggerFactory.getLogger(HomeworkController.class);
    @Autowired
    private HomeworkService homeworkService;

    // 根据comId和hName查找作业
    @RequestMapping(value = "/getJobForSearch/{comId}/{hName}",method = RequestMethod.GET)
    @ResponseBody
    public List<Homework> getJobForSearchHandler(@PathVariable("comId")String comId,@PathVariable("hName")String hName){
        List<Homework> jobs = this.homeworkService.getForSearch(comId,hName);
        return jobs;
    }

    // 根据作业id删除作业记录，返回删除记录数
   @RequestMapping(value = "/deleteByhId/{hId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer deleteByHidHandler(@PathVariable("hId") Integer hId){
        Integer i=this.homeworkService.removeJobById(hId);
        return i;
    }

    // 根据comId获取该学委发布的作业总数
    @RequestMapping(value = "/getCount/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountHandler(@PathVariable("cmtId") String cmtId){
        Integer total=this.homeworkService.getCount(cmtId);
        logger.info("数量"+total);
        return total;
    }

    // 根据cmtId查找该学委发布的作业信息，分页显示
    @RequestMapping(value = "/getJobs/{cmtId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<Homework> getJobsHandler(@PathVariable("cmtId") String cmtId,@PathVariable("pageNo") Integer pageNo,
                                     @PathVariable("pageSize") Integer pageSize){
        return this.homeworkService.getAllJobs(cmtId,pageNo,pageSize);
    }

    // 保存作业
    @RequestMapping(value = "/saveJob",method = RequestMethod.POST)
    @ResponseBody
    public Integer saveJobHandler(@RequestParam("jobFile")MultipartFile multipartFile, @RequestParam("comId") String comId, @RequestParam("courseId")String courseId,
                                  @RequestParam("endDate") Date endDate, @RequestParam("mark") String mark) throws ParseException {
        Homework homework = new Homework();
        homework.setCourseId(courseId);
        homework.setComId(comId);

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        Date now = new Date();
        String nowTime = f.format(endDate);
        Date date = f.parse(nowTime);
        Timestamp time = new Timestamp(date.getTime());
        homework.setEndDate(time);

        homework.setMark(mark);
        logger.info("学委"+comId);
        logger.info("课程"+courseId);
        logger.info("截至时间"+endDate);
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
