package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.vo.ClassVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClassesDao {

    List<Classes> selectAllClasses();
    Integer selectTotal();
    List<ClassVO> selectCountBySpecialtyGradeClass();

    Integer addClasses(Classes classes);

    void updateClasses(Classes classes);

    void deleteClassesById(Integer id);

    void deleteClassBatchById(int[] idList);

    Integer selectClassNoRepetition();

    List<Classes> selectClassesForSearch(@Param("speId") Integer speId,@Param("cGrade") String cGrade,@Param("cClass") Integer cClass);

    List<Integer> selectNumClassesBySpeIdAndGrade(@Param("speId") Integer speId,@Param("grade") String grade);
}
