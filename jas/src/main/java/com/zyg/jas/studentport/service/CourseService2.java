package com.zyg.jas.studentport.service;



import com.zyg.jas.common.pojo.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService2 {
     int addCourse(Course course);

     List<Course> getAllCourse(Integer pageNo, Integer pageSize);
     Integer getCourseTotal(); //获取记录总数

     Course getCourseById(String id);

     Course getCourseForSearch(Course course);  //前端搜索框使用

     void deleteCourseById(String id);

     List<Course> dealExcelForCourse(MultipartFile file);

     int saveCourseFromExcel(List<Course> courseList); //将excel数据转为course对象后，存放在集合中开始保存


    Integer removeByBatch(List<Course> courseList);

    List<Course> getForNameByCmtId(String cmtId);

     List<Course> getForName();
//学生端使用
    Course getCourseByName(String name);
}
