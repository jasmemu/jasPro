package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Tc;
import com.zyg.jas.common.pojo.Teacher;
import com.zyg.jas.common.tool.util.CheckOut;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.managerport.dao.TcDao;
import com.zyg.jas.managerport.dao.TeaDao;
import com.zyg.jas.managerport.service.TeaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeaServiceImpl implements TeaService {

    private Logger logger = LoggerFactory.getLogger(TeaServiceImpl.class);

    @Autowired
    private TeaDao teaDao;

    @Autowired
    private TcDao tcDao;
    @Override
    public int addTeacher(Teacher teacher) {
        Teacher tea = this.teaDao.selectTeaByTno(teacher.gettNo());
        int r;
        //向学生表添加记录
        if (tea == null){  //不存在这个教师编号，执行添加
            r = this.teaDao.insertToTeacher(teacher);
        }else { //存在这个教师编号，执行跟新
            r=this.teaDao.updateTea(teacher);
        }
        //向Sc表添加记录
        this.tcDao.deleteTcByTno(teacher.gettNo()); //插入和跟新教师教授课程（对tc进行插入和跟新时），先根据tNo删除tc表中的记录，再重新插入
        int cLen = teacher.getCourses().size();
        for (int i=0;i<cLen;i++){
            Tc tc = new Tc();
            tc.settNo(teacher.gettNo());
            tc.setCourseId(teacher.getCourses().get(i).getName());
            this.tcDao.insertToTc(tc);
        }
        return r;
    }

    @Override
    public Teacher getTeaByTno(String tNo) {
        return this.teaDao.selectTeaByTno(tNo);
    }

    @Override
    public List<Teacher> getAllTea(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Teacher> list = this.teaDao.selectAllTea();
        // PageInfo page = new PageInfo(list);
        return list;
    }

    @Override
    public Integer getTeaTotal() {
        Integer teaCount =  this.teaDao.selectCountOfTea();
        if (teaCount!=null){
            return teaCount;
        }else {
            return null;
        }
    }

    @Override
    public List<Teacher> getTeaForSearch(Teacher teacher) {
        return this.teaDao.selectTeaForSearch(teacher);
    }


    @Override
    public List<Teacher> dealExcelForTeas(MultipartFile file) {
        List<Teacher> teaList = new ArrayList<Teacher>(); //将教师信息存放到teaList中
        List<Tc> tcList = new ArrayList<>(); //将Tc信息存放到tcList中
        List excelList = ExcelUtil.getExcelData(file);
        logger.info("教师的行数："+excelList.size());
        for (int i = 0; i < excelList.size(); i++) {
            if (i == 0) {
                continue;
            }
            List list = (List) excelList.get(i);
            Teacher teacher = new Teacher();
            for (int j = 0; j < list.size(); j++) {
                if (j == 0) {
                    if (!CheckOut.checkIsIdentify(list.get(j).toString())){
                        teacher.settNo(list.get(j).toString());
                        this.tcDao.deleteTcByTno(teacher.gettNo()); //删除tc表中教师教授的课程信息
                    }else {
                        List<Teacher> error = new ArrayList<>();
                        Teacher teacher1 = new Teacher();
                        teacher1.setName("error");
                        teacher1.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列");
                        error.add(teacher1);
                        return error;
                    }

                } else if (j == 1) {
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        teacher.setName(list.get(j).toString());
                    }else{
                        List<Teacher> error = new ArrayList<>();
                        Teacher teacher1 = new Teacher();
                        teacher1.setName("error");
                        teacher1.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列");
                        error.add(teacher1);
                        return error;
                    }
                } else if (j == 2) {
                    if (CheckOut.checkIsIdentify(list.get(j).toString())){
                        teacher.setIdentify(list.get(j).toString());
                    }else {
                        List<Teacher> error = new ArrayList<>();
                        Teacher teacher1 = new Teacher();
                        teacher1.setName("error");
                        teacher1.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列");
                        error.add(teacher1);
                        return error;
                    }

                } else if (j == 3) {
                    if (CheckOut.checkIsPhone(list.get(j).toString())){
                        teacher.setPhone(list.get(j).toString());
                    }else {
                        List<Teacher> error = new ArrayList<>();
                        Teacher teacher1 = new Teacher();
                        teacher1.setName("error");
                        teacher1.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列");
                        error.add(teacher1);
                        return error;
                    }
                } else if (j == 4) {
                    if (CheckOut.checkIsEmail(list.get(j).toString())){
                        teacher.setEmail(list.get(j).toString());
                    }else {
                        List<Teacher> error = new ArrayList<>();
                        Teacher teacher1 = new Teacher();
                        teacher1.setName("error");
                        teacher1.setPassword("第"+ (i+1)+"行，第"+(j+1)+"列");
                        error.add(teacher1);
                        return error;
                    }

                } else {
                    Tc tc = new Tc();
                    tc.settNo(teacher.gettNo());
                    tc.setCourseId(list.get(j).toString());
                    tcList.add(tc);
                }
            }
            teacher.setPassword("123456"); //学生设置默认登录密码
            teaList.add(teacher);
        }

        for(int j=0;j< tcList.size();j++){
            this.tcDao.insertToTc(tcList.get(j));
        }
        return teaList;

    }

    @Override
    public int saveTeaFromExcel(List<Teacher> teaList) {
        int res=0;
        for (int i =0 ;i<teaList.size();i++){
            Teacher t = this.teaDao.selectTeaByTno(teaList.get(i).gettNo());
            if (t == null){
                this.teaDao.insertToTeacher(teaList.get(i));
            }else {
                this.teaDao.updateTea(teaList.get(i));
            }
            res++;
        }
        return res;
    }
    @Override
    public void deleteTeaByTno(String tNo) {
        this.tcDao.deleteTcByTno(tNo); //删除tc表中教师教授的课程信息
        this.teaDao.deleteByTno(tNo); //删除teacher表中教师信息
    }

    @Override
    public Integer removeByBatch(List<Teacher> teacherList) {
      for (Teacher t : teacherList){
          this.tcDao.deleteTcByTno(t.gettNo()); //删除tc表中教师教授的课程信息
      }
       return teaDao.deleteByBatch(teacherList);
    }
}
