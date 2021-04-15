package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.pojo.Teacher;
import com.zyg.jas.common.tool.vo.ClassVO;
import com.zyg.jas.managerport.dao.ClassesDao;
import com.zyg.jas.managerport.dao.TeaDao;
import com.zyg.jas.managerport.service.ClasssesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClasssesService {
    @Autowired
    private ClassesDao classesDao;
    @Autowired
    private TeaDao teaDao;


    @Override
    public PageInfo<Classes> getAllClasses(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Classes> list = classesDao.selectAllClasses();
        PageInfo<Classes> pageInfo = new PageInfo(list);
        return pageInfo;

    }

    @Override
    public Integer getClassTotal() {
        return classesDao.selectTotal();
    }

    @Override
    public List<ClassVO> getCountBySpecialtyGradeClass() {
        return classesDao.selectCountBySpecialtyGradeClass();
    }

    @Override
    public String saveClasses(Classes classes) {
        List<Teacher> teacherList = teaDao.selectAllTea();
        List<Classes> classesList = classesDao.selectAllClasses();
        for (Classes classes1: classesList){
            if (classes1.getSpeId().equals(classes.getSpeId())&&classes1.getGrade().equals(classes.getGrade())
                &&classes1.getNumClass().equals(classes.getNumClass())){
                classesDao.updateClasses(classes);
                return "success";
            }else {
                for (Teacher teacher: teacherList){
                    if(teacher.gettNo().equals(classes.getCharge())){
                        classesDao.addClasses(classes);
                        return "success";
                    }
                }
            }
        }


      return "failed";

    }

    @Override
    public void removeClassesById(Integer id) {
        classesDao.deleteClassesById(id);
    }

    @Override
    public void removeClassesBatchById(int[] idList) {
        classesDao.deleteClassBatchById(idList);
    }

    @Override
    public Integer getClassesNoRepetition() {
        return classesDao.selectClassNoRepetition();
    }

    @Override
    public List<Classes> getClassesForSearch(Integer speId, String cGrade, Integer cClass) {
        return classesDao.selectClassesForSearch(speId,cGrade,cClass);
    }

    @Override
    public List<Integer> getNumClassesBySpeIdAndGrade(Integer speId, String grade) {
        return classesDao.selectNumClassesBySpeIdAndGrade(speId,grade);
    }

}
