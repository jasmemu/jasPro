package com.zyg.jas.managerport.service;



import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
     int addCourse(Course course);

     List<Course> getAllCourse(Integer pageNo, Integer pageSize);
     Integer getCourseTotal(); //获取记录总数

     Course getCourseById(String id);

     Course getCourseForSearch(Course course);  //前端搜索框使用

     List<Course> getForName();

     void deleteCourseById(String id);

     List<Course> dealExcelForCourse(MultipartFile file);

     int saveCourseFromExcel(List<Course> courseList); //将excel数据转为course对象后，存放在集合中开始保存


}
