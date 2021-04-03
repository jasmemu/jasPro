package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyg.jas.common.pojo.CC;
import com.zyg.jas.common.pojo.Committee;
import com.zyg.jas.common.pojo.Specialty;
import com.zyg.jas.common.tool.util.CheckOut;
import com.zyg.jas.common.tool.util.ExcelUtil;
import com.zyg.jas.managerport.dao.CCDao;
import com.zyg.jas.managerport.dao.CmtDao;
import com.zyg.jas.managerport.dao.SpecialtyDao;
import com.zyg.jas.managerport.service.CmtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CmtServiceImpl implements CmtService {

    private static final Logger logger = LoggerFactory.getLogger(CmtServiceImpl.class);
    @Autowired
    private CmtDao cmtDao;

    @Autowired
    private CCDao ccDao;

    @Autowired
    private SpecialtyDao specialtyDao;

    // 问题未解决
    @Override
    public List<Committee> getAllCmt(Integer pageNo, Integer pageSize) {
        List<Committee> committeeList = this.cmtDao.selectAllCmt();
        for (Committee c: committeeList){
            logger.info(c.toString());
        }
        List<Committee> committees = new ArrayList<>();
        for (int i=0;i<pageSize;i++){
            if (pageNo*pageSize-5+i < committeeList.size()){
                committees.add(committeeList.get(pageNo*pageSize-5+i));
            }

        }
        return committees;
    }

    @Override
    public Integer getCmtTotal() {
        Integer cmtCount = this.cmtDao.selectCountOfCmt();
        if (cmtCount!=null){
            return cmtCount;
        }else {
            return null;
        }
    }

    @Override
    public List<Committee> getCmtForSearch(String speName,String cGrade,Integer cClass) {
        logger.info("service: "+speName+" "+cGrade+" "+ cClass);
        Specialty specialty = this.specialtyDao.selectSpeByName(speName);
        Committee c =new Committee();
        if (specialty!=null){
           c.setSpecialty(specialty);
        }
        c.setcGrade(cGrade);
        c.setcClass(cClass);
        logger.info("cmt info：" );
        logger.info(c.toString());
        List<Committee> cmtList = this.cmtDao.selectCmtForSearch(c);
        return  cmtList;
    }

    @Override
    public Committee getCmtByComIdWith(String cmtId) {
        return this.cmtDao.selectCmtByIdWith(cmtId);
    }

    @Override
    public Committee getCmtById(String id) {
        return this.cmtDao.selectCmtById(id);
    }


    public List<Committee> errorInfo(String str){
        List<Committee> error = new ArrayList<>();
        Committee committee = new Committee();
        committee.setName("error");
        committee.setPassword(str);
        error.add(committee);
        System.out.println("错误的size: "+ error.size());
        return error;
    }
    @Override
    public List<Committee> dealExcelForCommittee(MultipartFile file) {
        List<Committee> cmtList = new ArrayList<Committee>();
        List<CC> ccList = new ArrayList<>();
        List excelList = ExcelUtil.getExcelData(file);
        List<Specialty> specialties = this.specialtyDao.selectAllSpeCialty();
        System.out.println("excel中共有: "+excelList.size()+"  条数据");
        for (int i = 0; i < excelList.size(); i++) {
            if (i==0){
                continue;
            }
            List list = (List) excelList.get(i);
            Committee committee = new Committee();
            for (int j = 0; j < list.size(); j++) {
                if (j==0){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        for (int m=0;m<specialties.size();m++){
                            if (list.get(j).toString().equals(specialties.get(m).getSpeName())){
                                committee.setSpecialty(specialties.get(m));
                            }
                        }
                    }else {
                       return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }

                }else if (j == 1){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        committee.setcGrade(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }

                }else if(j==2){
                    if (CheckOut.checkIsNumber(list.get(j).toString())){
                        committee.setcClass(Integer.parseInt(list.get(j).toString()));
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j==3){
                    if (CheckOut.checkIsStuNo(list.get(j).toString())){
                        committee.setComId(list.get(j).toString());
                        ccDao.deleteCcByComid(committee.getComId());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j==4){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        committee.setName(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }

                }else if (j==5){
                    if (CheckOut.checkIsPhone(list.get(j).toString())){
                        committee.setPhone(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else if (j==6){
                    if (CheckOut.checkIsEmail(list.get(j).toString())){
                        committee.setEmail(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列");
                    }
                }else {
                    CC cc =new CC();
                    cc.setComId(committee.getComId());
                    cc.setCourseId(list.get(j).toString());
                    ccList.add(cc);
                }
            }
            committee.setPassword("123456"); //学生设置默认登录密码
            cmtList.add(committee);
        }
        System.out.println("正确的size: "+ cmtList.size());

        for(int j=0;j<ccList.size();j++){
            this.ccDao.insertToCc(ccList.get(j));
        }
        return cmtList;
    }
    @Override
    public int saveCmt(Committee committee) {
        List<Specialty> specialties = this.specialtyDao.selectAllSpeCialty();
        for (int i=0;i<specialties.size();i++){
            if (specialties.get(i).getSpeName().equals(committee.getSpecialty().getSpeName())){
                committee.setSpecialty(specialties.get(i));
            }
        }
        Committee cmt = this.cmtDao.selectCmtByIdWith(committee.getComId());//studentDao.selectStuBySno(student.getsNo());
        int r;
        //向学委表添加记录
        if (cmt == null){  //不存在这个学委，执行添加
            r = this.cmtDao.insertCmt(committee);//.studentDao.insertStudent(student);
        }else { //存在这个学委，执行跟新
            r=this.cmtDao.updateCmtById(committee);//.studentDao.updateStudent(student);
        }
        //向CC表添加记录
        this.ccDao.deleteCcByComid(committee.getComId());  //插入和跟新学委所管理课程（对cc进行插入和跟新时），先根据comId删除cc表中的记录，再重新插入
        int cLen = committee.getCourses().size();
        for (int i=0;i<cLen;i++){
            CC cc = new CC();
            cc.setComId(committee.getComId());
            cc.setCourseId(committee.getCourses().get(i).getName());
            this.ccDao.insertToCc(cc);
        }
        return r;
    }

    @Override
    public int saveCmtFromExcel(List<Committee> cmtList) {
        for (int i =0 ;i<cmtList.size();i++){
           logger.info("学委信息");
           logger.info(cmtList.get(i).toString());
        }
        int res=0;
        for (int i =0 ;i<cmtList.size();i++){
            Committee c = this.cmtDao.selectCmtByIdWith(cmtList.get(i).getComId());
            if (c == null){
                this.cmtDao.insertCmt(cmtList.get(i));
            }else {
                this.cmtDao.updateCmtById(cmtList.get(i));
            }
            res++;
        }
        return res;
    }

    @Override
    public Committee getCmtByAccount(String loginAccount, String loginPassword) {

        Committee committee = this.cmtDao.selectCmtByAccount(loginAccount);
        if (committee.getPassword().equals(loginPassword)){
            return committee;
        }else{
            return null;
        }
    }

    @Override
    public Integer saveCmtPersonal(Committee committee) {
        return this.cmtDao.updateCmtPersonal(committee);
    }

    @Override
    public void removeCmtByComId(String cmtId) {
        this.ccDao.deleteCcByComid(cmtId);  // 删除cc表中学委记录
        this.cmtDao.deleteCmtById(cmtId); // 删除学委记录
    }
    @Override
    public Integer removeByBatch(List<Committee> committeeList) {
        for (Committee c : committeeList){
            this.ccDao.deleteCcByComid(c.getComId());
        }
        return cmtDao.deleteByBatch(committeeList);
    }


}
