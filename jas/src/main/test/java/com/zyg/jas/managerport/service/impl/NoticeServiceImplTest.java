package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class NoticeServiceImplTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void insertNoticeTest(){
        for (int i=0;i<12;i++){
            Notice notice = new Notice();
            notice.setComId("201708044211");
            notice.setNoticeTitle("标题"+i);
            notice.setContent("注意了！明天放假"+i);
            Date date = new Date(System.currentTimeMillis());
            notice.setPublishDate(date);
            this.noticeService.saveNotice(notice);
        }
    }

}