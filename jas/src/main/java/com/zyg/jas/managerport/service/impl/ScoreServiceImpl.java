package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.managerport.dao.ScoreDao;
import com.zyg.jas.managerport.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;


    @Override
    public Integer getCount(String cmtId,String courseId) {
        return scoreDao.selectCount(cmtId,courseId);
    }

    @Override
    public List<Score> getScoresByCmtId(String cmtId,String courseId,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return scoreDao.selectScoresByCmtId(cmtId,courseId);
    }

    @Override
    public List<Score> getScreForSearch(String cmtId, String hName, String commit,String courseId,Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return  scoreDao.selectForSearch(cmtId,hName,commit,courseId);
    }

    @Override
    public Integer getCountForSearch(Map<String, String> paramMap) {
        return scoreDao.selectCountForSearch(paramMap);
    }

    @Override
    public Integer updateScoreById(Integer id, double score, String comment) {
        return scoreDao.updateScoreById(id,score,comment);
    }


}
