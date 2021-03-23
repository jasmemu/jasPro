package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.managerport.dao.CourseDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CourseServiceImplTest {
    @Autowired
    private CourseDao courseDao;

    @Test
    public void testGetCourseByCmtid(){
        List<Course> courseList = courseDao.selectForNameByCmtId("201708044211");
        for (Course c: courseList){
            System.out.println("======");
            System.out.println(c);
        }
    }


}