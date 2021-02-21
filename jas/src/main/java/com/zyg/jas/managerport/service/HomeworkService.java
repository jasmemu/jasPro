package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Homework;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface HomeworkService {
    Integer saveJob(MultipartFile multipartFile,Homework homework) throws IOException, ParseException;

    List<Homework> getAllJobs(String cmtId,Integer pageNo, Integer pageSize);

    Integer getCount(String cmtId);

    Integer removeJobById(Integer hId);
}
