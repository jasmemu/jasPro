package com.zyg.jas.studentport.service;



import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService2 {
     List<Student> getStudentByClasses(Classes classes);

     int addStudent(Student student);

     List<Student> getAllStu(Integer pageNo, Integer pageSize);
     public Integer getStuTotal(); //获取记录总数

     public Student getStuBySno(String sNo);

     public void deleteStuBySno(String sNo);

     public List<Student> getStuForSearch(Student student);  //前端搜索框使用

     public List<Student> dealExcelForStudents(MultipartFile file); //从上传的excel中获取studnet，保存记录

     public int saveStuFromExcel(List<Student> studentList); //将excel数据转为student对象后，存放在集合中开始保存

     Integer removeByBatch(List<Student> studentList);
}
