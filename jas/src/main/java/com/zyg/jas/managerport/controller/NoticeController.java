package com.zyg.jas.managerport.controller;

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

@Controller
@RequestMapping("/mport/notice")
@CrossOrigin
public class NoticeController {

    private  static  final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/getNoticesForSearch",method = RequestMethod.POST)
    @ResponseBody
    public List<Notice> getNoticesForSearchhandler(@RequestParam("account")String cmtId,
                                                   @RequestParam("publishDate") Date publishDate,
                                                   @RequestParam("noticeTitle") String noticeTitle){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(publishDate);
        List<Notice> notices = this.noticeService.getNoticesForSearch(cmtId,dateString ,noticeTitle);
        return notices;
    }

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

    @RequestMapping(value = "/getNoticeById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Notice getNoticeByIdHandler(@PathVariable("id") Integer id){
       Notice notice =  this.noticeService.getNoticeByIdWith(id);
       return notice;
    }

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

    @RequestMapping(value = "/getAllNotice/{cmtId}/{pageNo}/{pageSize}")
    @ResponseBody
    public List<Notice> getNoticeHandler(@PathVariable("cmtId") String cmtId,
                                         @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        List<Notice> notices= this.noticeService.getNoticeByCmtId(cmtId,pageNo,pageSize);
        return notices;
    }

    @RequestMapping(value = "/getTotal/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getTotalHandler(@PathVariable("cmtId") String cmtId){
        return this.noticeService.getTotal(cmtId);
    }
}
