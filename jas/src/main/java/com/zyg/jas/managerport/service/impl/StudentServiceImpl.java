

package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Classes;
import com.zyg.jas.common.pojo.Sc;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.util.CheckOut;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.managerport.dao.ClassesDao;
import com.zyg.jas.managerport.dao.ScDao;
import com.zyg.jas.managerport.dao.StudentDao;
import com.zyg.jas.managerport.service.StudentService;
import org.apache.ibatis.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ScDao scDao;
    @Autowired
    private ClassesDao classesDao;

//    public void setStudentDao(StudentDao studentDao) {
//       this.studentDao=studentDao;
//    }

    @Override
    public List<Student> getStudentByClasses(Classes classes) {
        return studentDao.selectStudentByClasses(classes);
    }

    @Override
    public int addStudent(Student student) {
        Student stu = this.studentDao.selectStuBySno(student.getsNo());
        int r;
        //向学生表添加记录
        if (stu == null){  //不存在这个学号，执行添加
            r = this.studentDao.insertStudent(student);
        }else { //存在这个学号，执行跟新
            r=this.studentDao.updateStudent(student);
        }
        //向Sc表添加记录
        this.scDao.deleteScBySno(student.getsNo()); //插入和跟新学生所选课程（对sc进行插入和跟新时），先根据sNo删除sc表中的记录，再重新插入
        int cLen = student.getCourses().size();
        for (int i=0;i<cLen;i++){
            Sc sc = new Sc();
            sc.setCourseId(student.getCourses().get(i).getName());
            sc.setsNo(student.getsNo());
            this.scDao.insertToSc(sc);
        }
        return r;
    }

    @Override
    public List<Student> getAllStu(Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Student> list = this.studentDao.selectAllStu();
        //PageInfo pageInfo = new PageInfo(list);
       return list;
    }

    @Override
    public Integer getStuTotal() {
        Integer stuCount = this.studentDao.selectCountOfStu();
        if (stuCount!=null){
            return stuCount;
        }else {
            return null;
        }
    }

    @Override
    public Student getStuBySno(String sNo) {
        return this.studentDao.selectStuBySno(sNo);
    }

    @Override
    public List<Student> getStuForSearch(Student student) {
        List<Student> stuList = this.studentDao.selectStuForSearch(student);
        return  stuList;
    }

    @Override
    public List<Student> dealExcelForStudents(MultipartFile file)  {
        Map<String,Object>  resultMap= new HashMap<>();
        List<Student> studentList = new ArrayList<Student>();
        List<Sc> scList = new ArrayList<>();
        List excelList = ExcelUtil.getExcelData(file);
        List<Classes> classesList = classesDao.selectAllClasses();
        for (int i = 0; i < excelList.size(); i++) {
            if (i==0){
                continue;
            }
            List list = (List) excelList.get(i);
            Student stu = new Student();
            for (int j = 0; j < list.size(); j++) {
                if (j==0){
                    if (CheckOut.checkIsStuNo(list.get(j).toString())){
                        stu.setsNo(list.get(j).toString());
                        scDao.deleteScBySno(stu.getsNo());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;

                    }

                }else if (j == 1){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        stu.setName(list.get(j).toString());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }

                }else if(j==2){
                    if (CheckOut.checkIsPhone(list.get(j).toString())){
                        stu.setPhone(list.get(j).toString());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }
                }else if (j==3){
                    if (CheckOut.checkIsEmail(list.get(j).toString())){
                        stu.setEmail(list.get(j).toString());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }

                }else if (j==4){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        if (list.get(j).equals("计算机科学与技术系")){
                            stu.setSpeId(new Integer(1));
                        }else if (list.get(j).equals("网络工程系")){
                            stu.setSpeId(new Integer(2));
                        }else if (list.get(j).equals("软件工程系")){
                            stu.setSpeId(new Integer(3));
                        }
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }
                }else if (j==5){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        stu.setsClass(Integer.parseInt(list.get(j).toString()));
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }
                }else if (j==6){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        stu.setsGrade(list.get(j).toString());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }
                }else if (j==7){
                    if (CheckOut.checkIsIdentify(list.get(j).toString())){
                        stu.setIdentify(list.get(j).toString());
                    }else {
                        List<Student> list1 = new ArrayList<>();
                        Student student = new Student();
                        student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                        student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                        list1.add(student);
                        return list1;
                    }
                }else if (j==8){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateTime = null;
                    //System.out.println(list.get(j).toString());
                    try {
                        String dateEnroll =list.get(j).toString();
                        if(dateEnroll.length() == 7 || dateEnroll.length()==8){  //excel月数小于10时20-9-10  月大于等于十时20-12-19
                            StringBuilder stringBuilder = new StringBuilder(dateEnroll);
                            stringBuilder.insert(0,"20");
                            if (dateEnroll.length()==7){
                                stringBuilder.insert(5,"0");
                            }
                            dateEnroll =stringBuilder.toString();
                        }
                        //System.out.println(dateEnroll);
                        dateTime = simpleDateFormat.parse(dateEnroll);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (CheckOut.checkIsDate(dateTime)){
                            stu.setEnrollment(dateTime);
                        }else {
                            List<Student> list1 = new ArrayList<>();
                            Student student = new Student();
                            student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                            student.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                            list1.add(student);
                            return list1;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }else {
                    Sc sc =new Sc();
                    sc.setsNo(stu.getsNo());
                    System.out.println("课程：" + list.get(j).toString());
                    sc.setCourseId(list.get(j).toString());
                    scList.add(sc);
                }
            }
            stu.setPassword("123456"); //学生设置默认登录密码
            // 班级是否存在进行判断（21/4/10）
            boolean judgeExit = true;
            for (int m=0;m<classesList.size();m++){ // 判断excel中的专业、年级、班级是否存在
                if (stu.getSpeId().equals(classesList.get(m).getSpeId())&&
                        stu.getsGrade().equals(classesList.get(m).getGrade())&&
                        stu.getsClass().equals(classesList.get(m).getNumClass())){
                    judgeExit = false;
                    studentList.add(stu);
                    break;
                }
            }
            if (judgeExit){
                List<Student> list1 = new ArrayList<>();
                Student student = new Student();
                student.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                student.setPassword("第"+ (i+1)+"行中的班级不存在！");
                list1.add(student);
                return list1;
            }
            // 班级是否存在进行判断（21/4/10）
        }
        for(int j=0;j<scList.size();j++){
            this.scDao.insertToSc(scList.get(j));
        }
        return studentList;
    }

    @Transactional
    @Override
    public int saveStuFromExcel(List<Student> studentList){
        int res=0;
        for (int i =0 ;i<studentList.size();i++){
            Student s = this.studentDao.selectStuBySno(studentList.get(i).getsNo());
            if (s == null){
                this.studentDao.insertStudent(studentList.get(i));
            }else {
                this.studentDao.updateStudent(studentList.get(i));
            }
            res++;
        }
        return res;
    }

    @Override
    public void deleteStuBySno(String sNo) {
        scDao.deleteScBySno(sNo);
        this.studentDao.deleteBySno(sNo);
    }

    @Override
    public Integer removeByBatch(List<Student> studentList) {
        for (Student s : studentList){
            scDao.deleteScBySno(s.getsNo());
        }
       return studentDao.deleteByBatch(studentList);
    }


}


