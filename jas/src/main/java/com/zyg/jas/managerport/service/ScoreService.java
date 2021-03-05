package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService {

    Integer getCount(String cmtId,String courseId);

    List<Score> getScoresByCmtId(String cmtId,String courseId,Integer pageNo,Integer pageSize);

    List<Score> getScreForSearch(String cmtId, String hName, String commit,String courseId,Integer pageNo, Integer pageSize);

    Integer getCountForSearch(Map<String,String> paramMap);

    Integer updateScoreById(Integer id,double score,String comment);
}
