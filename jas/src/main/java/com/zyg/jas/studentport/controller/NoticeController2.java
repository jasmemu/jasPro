package com.zyg.jas.studentport.controller;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.service.NoticeService;
import com.zyg.jas.studentport.service.NoticeService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/sport/notice")
@CrossOrigin
public class NoticeController2 {

    private  static  final Logger logger = LoggerFactory.getLogger(com.zyg.jas.managerport.controller.NoticeController.class);

    @Autowired
    private NoticeService2 noticeService;


    @RequestMapping(value = "/delete/byBatch",method = RequestMethod.POST)
    @ResponseBody
    public  Integer deleteStuByBatchHandler(@RequestBody List<Notice> noticeList) {
        return noticeService.removeByBatch(noticeList);
    }
    // 根据cmtId,noticeTitle搜索公告
    @RequestMapping(value = "/getNoticesForSearch",method = RequestMethod.POST)
    @ResponseBody
    public List<Notice> getNoticesForSearchhandler(@RequestParam("account")String cmtId,
                                                   @RequestParam(value = "noticeTitle") String noticeTitle){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // String dateString = formatter.format(publishDate);
        List<Notice> notices = this.noticeService.getNoticesForSearch(cmtId,"",noticeTitle);
        return notices;
    }

    // 根据公告id删除公告
    @RequestMapping(value = "/deleteNoticeById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String deleteHandler(@PathVariable("id") Integer id){
        Integer r = this.noticeService.removeNoticeById(id);
        if (r == 1){
            return "success";
        }else {
            return "false";
        }
    }

    // 根据公告id获取公告详情
    @RequestMapping(value = "/getNoticeById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Notice getNoticeByIdHandler(@PathVariable("id") Integer id){
        Notice notice =  this.noticeService.getNoticeByIdWith(id);
        return notice;
    }

    // 添加一个公告
    @RequestMapping(value = "/saveNotice",method = RequestMethod.POST)
    @ResponseBody
    public String saveNotice(@RequestBody Notice notice){
        logger.info("保存");
        logger.info(notice.toString());
        Integer r = this.noticeService.saveNotice(notice);
        if (r==1){
            return "success";
        }else {
            return "false";
        }
    }

    // 获取该学委发布的公告，分页显示
    @RequestMapping(value = "/getAllNotice/{cmtId}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Notice> getNoticeHandler(@PathVariable("cmtId") String cmtId,
                                         @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Notice> notices= this.noticeService.getNoticeByCmtId(cmtId,pageNo,pageSize);
        return notices;
    }

    // 获取该学委发布的公告数量
    @RequestMapping(value = "/getTotal/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getTotalHandler(@PathVariable("cmtId") String cmtId){
        return this.noticeService.getTotal(cmtId);
    }


    //学生端使用
    // 获取发布的公告
    @RequestMapping(value = "/getNoticeWithoutId")
    @ResponseBody
    public List<Notice> getAllNoticeHandler(){
        List<Notice> notices= this.noticeService.selectAllNoticeWithoutId();
        return notices;
    }
}
