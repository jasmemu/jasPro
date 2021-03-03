package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.LeaveMessage;
import com.zyg.jas.managerport.service.LeaveMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LeaveMessageServiceImplTest {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Test
    public void testSave(){
        for (int i=0;i<7;i++){
            LeaveMessage leaveMessage = new LeaveMessage();
            leaveMessage.setComId("201708044211");
            leaveMessage.setsNo("202108044202");
            leaveMessage.setLeaveMessage("交作业吗？"+i);
            leaveMessage.setLmDate(new Date());
            leaveMessage.setReplyMessage("");
            leaveMessage.setRmDate(new Date());
            this.leaveMessageService.SaveMessage(leaveMessage);
        }
    }

    @Test
    public void testGet(){
        System.out.println(this.leaveMessageService.getCountByCmtId("201708044211"));
    }

    @Test
    public void testGetById(){
        LeaveMessage leaveMessage = this.leaveMessageService.getById(29);
        System.out.println(leaveMessage);
    }

    @Test
    public void getForSearchTest(){
        Map map = new HashMap();
        map.put("comId","201708044211");


        map.put("lmDate","2021-02-06");
        List<LeaveMessage> leaveMessages = this.leaveMessageService.getForSearchWith(map);
        for (LeaveMessage l : leaveMessages){
            System.out.println(l.toString());
        }

    }

}