package com.zyg.jas.managerport.service;



import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
     int addCourse(Course course);

     List<Course> getAllCourse(Integer pageNo,Integer pageSize);
     public long getCourseTotal(); //获取记录总数

     public Course getCourseById(String id);

     public void deleteCourseById(String id);

     public Course getCourseForSearch(Course course);  //前端搜索框使用

     public List<Course> dealExcelForCourse(MultipartFile file);

     public int saveCourseFromExcel(List<Course> courseList); //将excel数据转为course对象后，存放在集合中开始保存


}
