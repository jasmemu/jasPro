package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Homework;

import java.util.List;
import java.util.Map;

public interface HomeworkDao {
    Integer insertJob(Homework homework);

    List<Homework> selectJobs(String comId);

    Integer selectCount(String cmtId);

    Integer deleteById(Integer hId);

    List<Homework> selectForSearch(Map paramMap);
}
