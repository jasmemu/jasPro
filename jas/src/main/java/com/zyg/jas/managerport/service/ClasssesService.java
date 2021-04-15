package com.zyg.jas.managerport.service;

import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.vo.ClassVO;

import java.util.List;

public interface ClasssesService {
    PageInfo<Classes> getAllClasses(Integer pageNo, Integer pageSize);
    Integer getClassTotal(); //获取记录总数

    List<ClassVO> getCountBySpecialtyGradeClass();

    String saveClasses(Classes classes);

   void removeClassesById(Integer id);
   void removeClassesBatchById(int[] idList);
   Integer getClassesNoRepetition();

    List<Classes> getClassesForSearch(Integer speId, String cGrade, Integer cClass);

     List<Integer> getNumClassesBySpeIdAndGrade(Integer speId, String grade);
}
