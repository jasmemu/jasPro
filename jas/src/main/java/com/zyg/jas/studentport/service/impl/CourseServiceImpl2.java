package com.zyg.jas.studentport.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.Course;
import com.zyg.jas.common.tool.util.CheckOut;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.studentport.dao.CourseDao2;
import com.zyg.jas.studentport.service.CourseService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CourseServiceImpl2 implements CourseService2 {
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl2.class);

    @Autowired
    private CourseDao2 courseDao;

    @Override
    public int addCourse(Course course) {

        Course c = this.courseDao.selectCourseById(course.getCourseId());
        int r;
        if (c == null){  //不存在这个学号，执行添加
            r=this.courseDao.insertCourse(course);
        }else { //存在这个学号，执行跟新
            r=this.courseDao.updateCourse(course);
        }
        return r;
    }

    @Override
    public List<Course> getAllCourse(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Course> list = this.courseDao.selectAllCourse();
        PageInfo page = new PageInfo(list);
        return list;
    }

    @Override
    public Integer getCourseTotal() {
        Integer courseCount = this.courseDao.selectCountOfCourse();
        if (courseCount!=null){
            return courseCount;
        }else {
            return null;
        }
    }

    @Override
    public Course getCourseById(String id) {
        return this.courseDao.selectCourseById(id);
    }

    @Override
    public void deleteCourseById(String id) {
        this.courseDao.deleteByCourseId(id);
    }

    @Override
    public Course getCourseForSearch(Course course) {
        return this.courseDao.selectCourseForSearch(course);
    }

    @Override
    public List<Course> getForName() {
        return this.courseDao.selectForName();
    }


    @Override
    public List<Course> getForNameByCmtId(String cmtId) {
        return courseDao.selectForNameByCmtId(cmtId);
    }

    public List<Course> errorInfo(String str){
        List<Course> error = new ArrayList<>();
        Course course1 = new Course();
        course1.setName("error");
        course1.setCourseId(str);
        error.add(course1);
        return error;
    }

    @Override
    public List<Course> dealExcelForCourse(MultipartFile file) {
        List<Course> courseList = new ArrayList<Course>();
        List excelList = ExcelUtil.getExcelData(file);
        logger.info("课程行数有：" + excelList.size());
        for (int i = 0; i < excelList.size(); i++) {
            if (i==0){
                continue;
            }
            List list = (List) excelList.get(i);
            //21.4.23
            if (list.size() == 0 ||list ==null){
                break;
            }

            Course course = new Course();
            for (int j = 0; j < list.size(); j++) {
                if (j==0){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        course.setCourseId(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j == 1){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        course.setName(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if(j==2){
                    if (CheckOut.checkIsNumber(list.get(j).toString())){
                        course.setPeriod(Integer.parseInt(list.get(j).toString()));
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j==3){
                    if (CheckOut.checkIsNumber(list.get(j).toString())){
                        course.setCredit(Integer.parseInt(list.get(j).toString()));
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j==4){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateTime = null;
                    System.out.println(list.get(j).toString());
                    try {
                        String dateEnroll =list.get(j).toString();
                        if(dateEnroll.length() == 7 || dateEnroll.length() == 8 ){ //excel月数小于10时20-9-10
                            StringBuilder stringBuilder = new StringBuilder(dateEnroll);
                            stringBuilder.insert(0,"20");
                            if (dateEnroll.length() == 7){
                                stringBuilder.insert(5,"0");
                            }
                            dateEnroll =stringBuilder.toString();
                        }
                        // System.out.println(dateEnroll);
                        dateTime = simpleDateFormat.parse(dateEnroll);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (CheckOut.checkIsDate(dateTime)){
                            course.setBeginDate(dateTime);
                        }else {
                            return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }else if (j==5){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateTime = null;
                    System.out.println(list.get(j).toString());
                    try {
                        String dateEnroll =list.get(j).toString();
                        if(dateEnroll.length() == 7 ||dateEnroll.length() == 8 ){ //excel月数小于10时20-9-10  20-12-19
                            StringBuilder stringBuilder = new StringBuilder(dateEnroll);
                            stringBuilder.insert(0,"20");
                            if (dateEnroll.length() ==7){
                                stringBuilder.insert(5,"0");
                            }
                            dateEnroll =stringBuilder.toString();
                        }
                       // System.out.println(dateEnroll);
                        dateTime = simpleDateFormat.parse(dateEnroll);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (CheckOut.checkIsDate(dateTime)){
                            course.setEndDate(dateTime);
                        }else {
                            return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
            courseList.add(course);
        }
//        for (int y=0;y<courseList.size();y++){
//            System.out.println(courseList.get(y).toString());
//        }
        return courseList;
    }

    @Transactional
    @Override
    public int saveCourseFromExcel(List<Course> courseList) {
        int res=0;
        for (int i =0 ;i<courseList.size();i++){
            Course c = this.courseDao.selectCourseById(courseList.get(i).getCourseId());
            if (c == null){
                this.courseDao.insertCourse(courseList.get(i));
            }else {
                this.courseDao.updateCourse(courseList.get(i));
            }
            res++;
        }
        return res;
    }

    @Override
    public Integer removeByBatch(List<Course> courseList) {
        return courseDao.deleteByBatch(courseList);
    }

//学生端使用
    @Override
    public Course getCourseByName(String name) {
        return this.courseDao.selectCourseByName(name);
    }

}
