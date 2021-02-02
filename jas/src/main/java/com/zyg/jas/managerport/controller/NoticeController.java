package com.zyg.jas.managerport.controller;

import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import java.util.List;

@Controller
@RequestMapping("/mport/notice")
@CrossOrigin
public class NoticeController {

    private  static  final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

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
