package com.zyg.jas.managerport.controller;


import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.constant.JasConstant;
import com.zyg.jas.common.tool.util.ResultEntity;
import com.zyg.jas.common.tool.vo.ClassVO;
import com.zyg.jas.managerport.service.StudentService;
import com.zyg.jas.managerport.service.impl.ClassesServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mport/classes")
@CrossOrigin
public class ClassesController {
    private Logger logger = LoggerFactory.getLogger(ClassesController.class);
    @Autowired
    private ClassesServiceImpl classessService;
    @Autowired
    private StudentService studentService;

    // 根据专业id、年级获取班级（numClasses）
    @RequestMapping(value = "/get/numclass/{speId}/{grade}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Integer>> getNumClassesBySpeIdAndGradeHandler(@PathVariable("speId") Integer speId,@PathVariable("grade") String grade){
        List<Integer> numClasssList = classessService.getNumClassesBySpeIdAndGrade(speId,grade);
        return ResultEntity.successWithData(numClasssList);
    }

    // 根据classes的专业id、年级、班级获取学生
   @RequestMapping(value = "/get/students/by/classes",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<List<Student>> getStudentByClassesHandler(@RequestBody Classes classes){
        List<Student> studentList = studentService.getStudentByClasses(classes);
        return ResultEntity.successWithData(studentList);
    }
    //根据专业id或年级或班级（classes）的id搜索班级信息
    @RequestMapping(value = "/get/for/search",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity<List<Classes>> getClassesForSerach(@RequestParam(value = "speId",required = false) Integer speId,
    @RequestParam(value = "cGrade",required = false) String cGrade,@RequestParam(value = "cClass",required = false) Integer cClass){
        logger.info("搜索内容: "+ speId+"-"+cGrade+"-"+cClass);
        List<Classes> list = classessService.getClassesForSearch(speId,cGrade,cClass);
        List<ClassVO> classVOList = classessService.getCountBySpecialtyGradeClass();
        for (int i=0;i<list.size();i++){
            boolean flag= false;
            for (int j=0;j<classVOList.size();j++){
                if (list.get(i).getSpeId().equals(classVOList.get(j).getSpeId())&&
                        list.get(i).getGrade().equals(classVOList.get(j).getsGrade())&&
                        list.get(i).getNumClass().equals(classVOList.get(j).getsClass())){
                    list.get(i).setStuNum(classVOList.get(j).getNum());
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list.get(i).setStuNum(0);
            }
        }
          return  ResultEntity.successWithData(list);
    }

    //获取所有的班级编号（numClass）
    @RequestMapping(value = "/get/grades",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<String[]> getGrades(){
        return ResultEntity.successWithData(JasConstant.GRADE_LIST);
    }

    //获取所有的班级编号（numClass）
    @RequestMapping(value = "/get/no/repetition",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity<List<Integer>> getClassNumNoRepetition(){
       Integer maxNumClass = classessService.getClassesNoRepetition();
       List<Integer> numClassList = new ArrayList<>();
       for (int i=0;i<maxNumClass;i++){
           numClassList.add(i+1);
       }
       return ResultEntity.successWithData(numClassList);
    }

    //批量删除班级信息，接收是一个数组存放的是班级（classes）的id
    @RequestMapping(value = "/delete/batch",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity deleteBatch(@RequestBody int[] idList){
        classessService.removeClassesBatchById(idList);
        return  ResultEntity.successWithoutData();
    }


    // 接收的是班级（classes）中的id，根据id删除班级
    @RequestMapping(value = "/remove/class/by/id/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity removeClassHandler(@PathVariable("id") Integer id){
         classessService.removeClassesById(id);
         return ResultEntity.successWithoutData();
    }

    // 添加一个班级，或者修改班级的班主任编号，
    @RequestMapping(value = "/save/class",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveClass(@RequestBody Classes classes){

        System.out.println("接收到的class"+classes);
        if (classessService.saveClasses(classes).equals("success")){
            return ResultEntity.successWithoutData();
        }else {
            return ResultEntity.failed("");
        }
    }

    //获取course表所有记录，分页
    @RequestMapping(value = "/getAllClasses/{pageNo}/{pageSize}",method = RequestMethod.GET)
    @ResponseBody
    public List<Classes> getAllCourse(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize){
        PageInfo<Classes> pageInfo = classessService.getAllClasses(pageNo,pageSize);
        List<Classes> list = new ArrayList<>();
        for (Classes info : pageInfo.getList()) {
            list.add(info);
        }
        List<ClassVO> classVOList = classessService.getCountBySpecialtyGradeClass();
        for (int i=0;i<list.size();i++){
            boolean flag= false;
            for (int j=0;j<classVOList.size();j++){
                if (list.get(i).getSpeId().equals(classVOList.get(j).getSpeId())&&
                        list.get(i).getGrade().equals(classVOList.get(j).getsGrade())&&
                        list.get(i).getNumClass().equals(classVOList.get(j).getsClass())){
                    list.get(i).setStuNum(classVOList.get(j).getNum());
                    flag = true;
                    break;
                }
            }
            if(!flag){
                list.get(i).setStuNum(0);
            }
        }
        return list;
    }

    //获取course表记录数量
    @RequestMapping(value = "/getClassesTotal",method = RequestMethod.GET)
    @ResponseBody
    public String getCourseTotal(){
        Integer classesCount = classessService.getClassTotal();
        if (classesCount!=null){
            return String.valueOf(classesCount);
        }else {
            return "";
        }
    }

    //获取course表记录数量
    @RequestMapping(value = "/getCountBySpecialtyGradeClass",method = RequestMethod.GET)
    @ResponseBody
    public List<ClassVO> getCountBySpecialtyGradeClassHandler(){
        List<ClassVO> stuCountByClass = classessService.getCountBySpecialtyGradeClass();
        if (stuCountByClass!=null){
            return stuCountByClass;
        }else {
            return null;
        }
    }


}
