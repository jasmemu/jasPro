package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Score;
import com.zyg.jas.common.pojo.Student;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ScoreDao {

    List<Score> selectScForLevelByHid(Integer hId);//根据作业id获取各成绩等级的学生学号

    List<Student> selectAllStudentForSubmitJob(Integer jobId); //根据作业id查询该班级所有学生

    List<Student> selectStudentForSubmitJob(Integer jobId); //根据作业id查询已经提交该作业的学生

    List<Student> selectStudentForNoSubmitJob(Integer jobId); //根据作业id查询没有提交作业的学生

    Integer selectCount(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    List<Score> selectScoresByCmtId(@Param("cmtId") String cmtId,@Param("courseId") String courseId);

    Integer insertScore(Score score);

    List<Score> selectForSearch(@Param("cmtId") String cmtId,@Param("hName") String hName,@Param("commit") String commit,@Param("courseId") String courseId);

    Integer selectCountForSearch(Map<String,String> paramsMap);

    Integer updateScoreById(@Param("id") Integer id,@Param("score") double score,@Param("comment") String comment);
}
