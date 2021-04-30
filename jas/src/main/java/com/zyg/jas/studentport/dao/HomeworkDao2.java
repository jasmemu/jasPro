package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomeworkDao2 {
    Integer insertJob(Homework homework);

    List<Homework> selectJobs(String comId);

    Integer selectCount(String cmtId);

    Integer deleteById(Integer hId);

    List<Homework> selectForSearch(Map paramMap);

    Homework selectJobByHid(Integer hId);

    Integer deleteByBatch(@Param("homeworks") List<Homework> homeworks);
//    学生端使用
    public List<Homework> selectHomeworkByCourseid(String courseid);
}
