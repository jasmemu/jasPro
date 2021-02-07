package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.managerport.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ResourceServiceImplTest {
    private static Logger logger = LoggerFactory.getLogger(ResourceServiceImplTest.class);

    @Autowired
    private ResourceService resourceService;

    @Test
    public void test(){

        String realPath = "D:\\program\\ideaWorkspace\\jas\\src\\main\\webapp\\cresources\\4882414c-32d4-42c7-98cc-3ce12a842385.docx";
        int  i = realPath.lastIndexOf("\\");
        String alias = realPath.substring(i+1);
        System.out.println(alias);
    }

}