package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeaService {
    int addTeacher(Teacher teacher);

    public Teacher getTeaByTno(String tNo);

    List<Teacher> getAllTea(Integer pageNo, Integer pageSize);
    public Integer getTeaTotal(); //获取记录总数

    public List<Teacher> getTeaForSearch(Teacher teacher);  //前端搜索框使用

    public void deleteTeaByTno(String tNo);

    public List<Teacher> dealExcelForTeas(MultipartFile file); //从上传的excel中获取teacher，保存到集合

    public int saveTeaFromExcel(List<Teacher> teaList); //将excel数据转为teacher对象后，读取集合中对象开始保存


}
