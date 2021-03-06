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

    //批量删除留言
    @RequestMapping(value = "/delete/byBatch",method = RequestMethod.POST)
    @ResponseBody
    public  Integer deleteStuByBatchHandler(@RequestBody List<LeaveMessage> leaveMessages) {
        return leaveMessageService.removeByBatch(leaveMessages);
    }

    // 获取留言，分页显示
    @RequestMapping(value = "/getMessages/{cmtId}/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<LeaveMessage> getMessageHandler(@PathVariable("cmtId") String cmtId,
                                                @PathVariable("pageNo")Integer pageNo,@PathVariable("pageSize") Integer pageSize){
        return this.leaveMessageService.getMessageByCmtIdWith(cmtId,pageNo,pageSize);
    }

    // 根据学委id获取留言数量（学生留言时，必须获取该学生所在班级的学委，将学委id存到留言表总）
    @RequestMapping(value = "/getCount/{cmtId}",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountHandler(@PathVariable("cmtId") String cmtId){
        return this.leaveMessageService.getCountByCmtId(cmtId);
    }

    // 根据留言id获取该留言详细信息
    @RequestMapping(value = "/getById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public LeaveMessage getCountHandler(@PathVariable("id") Integer id){
        return this.leaveMessageService.getById(id);
    }

    // 根据comId、reply_message是否为空搜索留言
    @RequestMapping(value = "/getForSearch/{comId}/{reply}",method = RequestMethod.GET)
    @ResponseBody
    public List<LeaveMessage> getForSearchHandler(@PathVariable("comId")String comId, @PathVariable("reply")String reply){
        Map<String,String> map = new HashMap<String,String>();
        String flag = "no";
        if (reply.equals("已回复")){
            flag = "yes";
        }
        map.put("comId",comId);
        map.put("flag",flag);
       return this.leaveMessageService.getForSearchWith(map);
    }

    // 留言回复
    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    @ResponseBody
    public Integer reply(@RequestBody LeaveMessage leaveMessage){
        return  this.leaveMessageService.updateReplyById(leaveMessage);
    }

    // 根据留言id删除留言
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Integer reply(@PathVariable("id") Integer id){
        return  this.leaveMessageService.removeById(id);
    }
}
