package com.zyg.jas.managerport.controller;


import com.zyg.jas.common.pojo.LeaveMessage;
import com.zyg.jas.managerport.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/mport/message")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @RequestMapping(value = "/getMessages/{cmtId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<LeaveMessage> getMessageHandler(@PathVariable("cmtId") String cmtId,
                                                @PathVariable("pageNo")Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        return this.leaveMessageService.getMessageByCmtIdWith(cmtId,pageNo,pageSize);
    }

    @RequestMapping(value = "/getCount/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountHandler(@PathVariable("cmtId") String cmtId){
        return this.leaveMessageService.getCountByCmtId(cmtId);
    }

    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public LeaveMessage getCountHandler(@PathVariable("id") Integer id){
        return this.leaveMessageService.getById(id);
    }

    @RequestMapping(value = "/getForSearch/{comId}/{lmDate}",method = RequestMethod.GET)
    @ResponseBody
    public List<LeaveMessage> getForSearchHandler(@PathVariable("comId")String comId, @PathVariable("lmDate")Date lmDate){
        Map map = new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(lmDate);
        map.put("comId",comId);
        map.put("lmDate",dateString);
       return this.leaveMessageService.getForSearchWith(map);
    }

    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    @ResponseBody
    public Integer reply(@RequestBody LeaveMessage leaveMessage){
        return  this.leaveMessageService.updateReplyById(leaveMessage);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Integer reply(@PathVariable("id") Integer id){
        return  this.leaveMessageService.removeById(id);
    }
}
