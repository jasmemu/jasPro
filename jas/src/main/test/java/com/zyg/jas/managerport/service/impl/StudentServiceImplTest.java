package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.managerport.dao.CmtDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentServiceImplTest {

    @Autowired
    private CmtDao cmtDao;
    @Test
    public void getStudents() {
        List<Committee> committees = this.cmtDao.selectAllCmt();
        for (int i=0;i<committees.size();i++){
            System.out.println("学委信息"+(i+1));
            System.out.println(committees.get(i));
        }
    }

    @Test
    public void testArgs(){
        Committee committee = new Committee();
        System.out.println(committee.getSpecialty().getSpeId());
    }
}
