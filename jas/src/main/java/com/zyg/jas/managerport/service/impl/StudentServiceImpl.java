

package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Student;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.managerport.dao.StudentDao;
import com.zyg.jas.managerport.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    private PageInfo page; //分页
//    public void setStudentDao(StudentDao studentDao) {
//       this.studentDao=studentDao;
//    }

    @Override
    public int addStudent(Student student) {
        Student stu = this.studentDao.selectStuBySno(student.getsNo());
        int r;
        if (stu == null){  //不存在这个学号，执行添加
            r = this.studentDao.insertStudent(student);
        }else { //存在这个学号，执行跟新
            r=this.studentDao.updateStudent(student);
        }
        return r;
    }

    @Override
    public List<Student> getAllStu(Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Student> list = this.studentDao.selectAllStu();
        System.out.println("学生list");
        System.out.println(list);
        page = new PageInfo(list);
       return list;
    }

    @Override
    public long getStuTotal() {
        return this.studentDao.selectCountOfStu();
    }

    @Override
    public Student getStuBySno(String sNo) {
        return this.studentDao.selectStuBySno(sNo);
    }

    @Override
    public void deleteStuBySno(String sNo) {
        this.studentDao.deleteBySno(sNo);
    }

    @Override
    public Student getStuForSearch(Student student) {
        Student stu = this.studentDao.selectStuForSearch(student);
        return  stu;
    }

    @Override
    public List<Student> dealExcelForStudents(MultipartFile file) {
        List<Student> studentList = new ArrayList<Student>();
        List excelList = ExcelUtil.getExcelData(file);
        for (int i = 0; i < excelList.size(); i++) {
            if (i==0){
                continue;
            }
            List list = (List) excelList.get(i);
            Student stu = new Student();
            for (int j = 0; j < list.size(); j++) {
                if (j==0){
                    stu.setsNo(list.get(j).toString());
                }else if (j == 1){
                    stu.setName(list.get(j).toString());
                }else if(j==2){
                    stu.setPhone(list.get(j).toString());
                }else if (j==3){
                    stu.setEmail(list.get(j).toString());
                }else if (j==4){
                    if (list.get(j).equals("计算机科学与技术系")){
                        stu.setSpeId(new Integer(1));
                    }else if (list.get(j).equals("网络工程系")){
                        stu.setSpeId(new Integer(2));
                    }else if (list.get(j).equals("软件工程系")){
                        stu.setSpeId(new Integer(3));
                    }
                }else if (j==5){
                    stu.setsClass(Integer.parseInt(list.get(j).toString()));
                }else if (j==6){
                    stu.setsGrade(list.get(j).toString());
                }else if (j==7){
                    stu.setIdentify(list.get(j).toString());
                }else if (j==8){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateTime = null;
                    System.out.println(list.get(j).toString());
                    try {
                        String dateEnroll =list.get(j).toString();
                        if(dateEnroll.length() == 7){ //excel月数小于10时20-9-10
                            StringBuilder stringBuilder = new StringBuilder(dateEnroll);
                            stringBuilder.insert(0,"20");
                            stringBuilder.insert(5,"0");

                            dateEnroll =stringBuilder.toString();

                        }
                        System.out.println(dateEnroll);
                        dateTime = simpleDateFormat.parse(dateEnroll);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    stu.setEnrollment(dateTime);

                }
            }
            stu.setPassword("123456"); //学生设置默认登录密码
            studentList.add(stu);
        }
        for (int y=0;y<studentList.size();y++){
            System.out.println(studentList.get(y).toString());
        }
        return studentList;
    }

    @Transactional
    @Override
    public int saveStuFromExcel(List<Student> studentList){
        int res=0;
        for (int i =0 ;i<studentList.size();i++){
            this.studentDao.insertStudent(studentList.get(i));
            res++;
        }
        return res;
    }


}


