package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Score;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ScoreDao {

    Integer selectCount(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    List<Score> selectScoresByCmtId(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    Integer insertScore(Score score);

    List<Score> selectForSearch(@Param("cmtId") String cmtId,@Param("hName") String hName,@Param("commit") String commit,@Param("courseId") String courseId);

    Integer selectCountForSearch(Map<String,String> paramsMap);

    Integer updateScoreById(@Param("id") Integer id,@Param("score") double score,@Param("comment") String comment);
}
