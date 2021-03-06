package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {

    public void deleteByCourseId(String id); //根据sno删除一条记录

    public int updateCourse(Course course); //更新一条记录

    public int insertCourse(Course course); //添加一个课程信息

    public List<Course> selectAllCourse(); //查询所有课程记录
    public Integer selectCountOfCourse();

    List<Course> selectForName();

    public Course selectCourseById(String id);//根据courseId查询一条记录

    public Course selectCourseForSearch(Course course); //前端搜索框使用

    Integer deleteByBatch(@Param("courseList") List<Course> courseList);
}
