package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.managerport.dao.ScoreDao;
import com.zyg.jas.managerport.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;


    @Override
    public Map<String,Integer> getScForLevelByHid(Integer hId) {
        List<Score>  scoreList=scoreDao.selectScForLevelByHid(hId);
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        for(int i=0;i<scoreList.size();i++){
            Score s = scoreList.get(i);
            if (s.getScore()<60){
                d++;
            }else if(s.getScore()>=60&&s.getScore()<80){
                c++;
            }else if (s.getScore()>=80&&s.getScore()<90){
                b++;
            }else if (s.getScore()>=90&&s.getScore()<=100){
                a++;
            }else{
                continue;
            }
        }
        Map<String,Integer> levelMap= new HashMap<>();
        levelMap.put("level1",a);
        levelMap.put("level2",b);
        levelMap.put("level3",c);
        levelMap.put("level4",d);
        return levelMap;
    }

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

    @Override
    public List<Student> getAllStudentForSubmitJob(Integer jobId) {
        return scoreDao.selectAllStudentForSubmitJob(jobId);
    }

    @Override
    public List<Student> getStudentForSubmitJob(Integer jobId) {
        return scoreDao.selectStudentForSubmitJob(jobId);
    }

    @Override
    public List<Student> getStudentForNoSubmitJob(Integer jobId) {
        return scoreDao.selectStudentForNoSubmitJob(jobId);
    }


}
