package com.zyg.jas.managerport.service;



import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.pojo.Sc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScService {
     public int AddSc(Sc sc);
     public List<String> getCourseBySno(String sNo);

}
