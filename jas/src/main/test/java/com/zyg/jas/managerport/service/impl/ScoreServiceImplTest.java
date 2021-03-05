package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.managerport.dao.ScoreDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ScoreServiceImplTest {

//    @Value("${submitPath}")
//    private String submitPath;
//    @Value("${submitUrl}")
//    private String submitUrl;
    @Autowired
    private ScoreDao scoreDao;

    @Test
    public void getCount(){


    }

    // 添加已批阅的Score
    @Test
    public void addScore() throws ParseException {
        for (int i=0;i<3;i++){
            Score score = new Score();
            score.setComId("202108044204");
            score.setsNo("202108044208");
            score.sethId(33);
            score.setComment("作业评语"+i+i);
            score.setCommitedJobPath("D://DevelopDir//developbase//tomcat8081//apache-tomcat-9.0.43//webapps//ROOT//submitjob");
            score.setCommitedJobUrl("http://localhost:8081/submitjob/");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String now=simpleDateFormat.format(date);
            Date date2 = simpleDateFormat.parse(now);
            score.setCorrectDate(date2);
            score.setScore(67.0+i);
            scoreDao.insertScore(score);

        }
    }

    // 添加没有批阅的Score
    @Test
    public void addScoreNo() throws ParseException {
        for (int i=0;i<7;i++){
            Score score = new Score();
            score.setComId("201708044211");
            score.setsNo("202108044206");
            score.sethId(34);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String now=simpleDateFormat.format(date);
            Date date2 = simpleDateFormat.parse(now);
            score.setCorrectDate(date2);
            scoreDao.insertScore(score);

        }
    }

}