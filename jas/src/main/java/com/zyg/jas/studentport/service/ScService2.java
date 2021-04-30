package com.zyg.jas.studentport.service;



import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Sc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScService2 {
     public int AddSc(Sc sc);
     public List<String> getCourseBySno(String sNo);
//     学生端使用
     public List<String> getCourseIdBySno(String sNo);

}
