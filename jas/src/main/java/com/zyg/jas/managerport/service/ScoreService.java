package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.common.pojo.Student;

import java.util.List;
import java.util.Map;

public interface ScoreService {


    Map<String,Integer> getScForLevelByHid(Integer hId);

    Integer getCount(String cmtId,String courseId);

    List<Score> getScoresByCmtId(String cmtId,String courseId,Integer pageNo,Integer pageSize);

    List<Score> getScreForSearch(String cmtId, String hName, String commit,String courseId,Integer pageNo, Integer pageSize);

    Integer getCountForSearch(Map<String,String> paramMap);

    Integer updateScoreById(Integer id,double score,String comment);

    List<Student> getAllStudentForSubmitJob(Integer jobId); //根据作业id查询该班级所有学生

    List<Student> getStudentForSubmitJob(Integer jobId); //根据作业id查询已经提交该作业的学生

    List<Student> getStudentForNoSubmitJob(Integer jobId); //根据作业id查询没有提交作业的学生
}
