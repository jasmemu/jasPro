package com.zyg.jas.studentport.service.impl;


import com.zyg.jas.common.pojo.*;
import com.zyg.jas.common.tool.constant.JasConstant;
import com.zyg.jas.common.tool.util.*;
import com.zyg.jas.studentport.dao.*;
import com.zyg.jas.studentport.service.CmtService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class CmtServiceImpl2 implements CmtService2 {

    private static final Logger logger = LoggerFactory.getLogger(CmtServiceImpl2.class);
    @Autowired
    private CmtDao2 cmtDao;

    @Autowired
    private CCDao2 ccDao;

    @Autowired
    private SpecialtyDao2 specialtyDao;

    @Autowired
    private ClassesDao2 classesDao;

    @Autowired
    private SysManagerDao2 sysManagerDao;

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
        List<Specialty> specialties = specialtyDao.selectAllSpeCialty();
        System.out.println("excel中共有: "+excelList.size()+"  条数据");
        List<Classes>classesList = classesDao.selectAllClasses();
        for (int i = 0; i < excelList.size(); i++) {
            if (i==0){
                continue;
            }
            List list = (List) excelList.get(i);
            //21.4.23
            if (list.size() == 0 ||list ==null){
                break;
            }
            Committee committee = new Committee();
            for (int j = 0; j < list.size(); j++) {
                if (j==0){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        for (int m=0;m<specialties.size();m++){
                            if (list.get(j).toString().equals(specialties.get(m).getSpeName())){
                                committee.setSpecialty(specialties.get(m));
                                break;
                            }else {
                                committee.setSpecialty(new Specialty("error"));
                            }
                        }
                    }else {
                       return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }

                }else if (j == 1){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        committee.setcGrade(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }

                }else if(j==2){
                    if (CheckOut.checkIsNumber(list.get(j).toString())){
                        committee.setcClass(Integer.parseInt(list.get(j).toString()));
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }
                }else if (j==3){
                    if (CheckOut.checkIsStuNo(list.get(j).toString())){
                        committee.setComId(list.get(j).toString());
                        ccDao.deleteCcByComid(committee.getComId());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }
                }else if (j==4){
                    if (!CheckOut.checkIsNull(list.get(j).toString())){
                        committee.setName(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }

                }else if (j==5){
                    if (CheckOut.checkIsPhone(list.get(j).toString())){
                        committee.setPhone(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }
                }else if (j==6){
                    if (CheckOut.checkIsEmail(list.get(j).toString())){
                        committee.setEmail(list.get(j).toString());
                    }else {
                        return errorInfo("第"+ (i+1)+"行，第"+(j+1)+"列格式错误！");
                    }
                }else {
                    CC cc =new CC();
                    cc.setComId(committee.getComId());
                    cc.setCourseId(list.get(j).toString());
                    ccList.add(cc);
                }
            }
            committee.setPassword("123456"); //学生设置默认登录密码
            // 班级是否存在进行判断（21/4/10）
            boolean judgeExit = true;
            for (int m=0;m<classesList.size();m++){ // 判断excel中的专业、年级、班级是否存在
                if (committee.getSpecialty().getSpeName().equals(classesList.get(m).getSpecialty().getSpeName())&&
                        committee.getcGrade().equals(classesList.get(m).getGrade())&&
                        committee.getcClass().equals(classesList.get(m).getNumClass())){
                    judgeExit = false;
                    cmtList.add(committee);
                    break;
                }
            }
            if (judgeExit){
                List<Committee> list1 = new ArrayList<>();
                Committee c = new Committee();
                c.setName("error"); // 创建姓名为error的学生，password为校验不通过的行和列
                c.setPassword("第"+ (i+1)+"行中的班级不存在！");
                list1.add(c);
                return list1;
            }
            // 班级是否存在进行判断（21/4/10）

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
        // 如果根据账号没有查到学委，这里会出现空指针异常
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

    // redis中的键是email和stuNo拼接的
    @Override
    public String getAuthCode(String email,String stuNo) {
        // 生成六位数字的验证码
        String authCode = productAutoCode();
        // 保存到redis,返回redis中是否有这个键值对
        boolean keyExist = saveAuthCode(email+stuNo,authCode);
        // redis中不存在该键值对，发送邮件
        if (!keyExist){
            // 获取系统管理员
            List<SysManager> sysManagerList= sysManagerDao.getSysManager();
            SysManager sysManager = sysManagerList.get(0);
            try {
                EmailUtil.sendMail(sysManager.getEmail(),sysManager.getQqAuthCode(),email, JasConstant.JAS_EMAIL_TITLE,
                        "验证码："+authCode+"；有效时间："+JasConstant.AUTH_CODE_OUT_TIME/60+"分钟");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 返回新生成的验证码
            return authCode;
        }else {
            JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
            Jedis jedis = null;
            String exitAuthCode=null;
            try {
                jedis = jedisPool.getResource();
                exitAuthCode = jedis.get(email+stuNo);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JedisPoolUtil.release(jedisPool, jedis);
            }
            //redis中已存在，返回redis中的验证码
            return exitAuthCode;

        }

    }

    @Override
    public void resetPwdByAuthCode(String stuNo, String newPWD) {
        cmtDao.updatePwdByAuthCode(stuNo,newPWD);
    }

    @Override
    public List<Student> getStatisticsSubmit(String cmtId, String courseId) {
        return cmtDao.selectStatisticsSubmit(cmtId,courseId);
    }

    @Override
    public String getStatisticsSubmitForExcel(String cmtId, String courseId,String courseName) {
        List<Student> studentList = cmtDao.selectStatisticsSubmit(cmtId,courseId);
       String excelFile = CreateExcel.getExcelFile(JasConstant.SUBMITED_EXCEL_NAME,courseName+JasConstant.SUBMITED_lIST_TITLE, studentList);
        return excelFile;
    }

    @Override
    public String getNoSubmitForExcel(String cmtId, String courseId, String courseName) {
        List<String> noSubmit = getNoSubmit(cmtId, courseId);

        System.out.println("未交的String");
        for (int i=0;i<noSubmit.size();i++){
            System.out.println(noSubmit.get(i));
        }
        List<String[]> sNoAndHidList= new ArrayList<>();
        for (int i =0 ;i<noSubmit.size();i++){
            String [] sNoAndHid=noSubmit.get(i).split("-");
            sNoAndHidList.add(sNoAndHid);
        }
//        System.out.println("学号，作业id:");
//        for (int i=0;i<sNoAndHidList.size();i++){
//            System.out.println(sNoAndHidList.get(i)[0]+"-"+sNoAndHidList.get(i)[1]);
//        }
        List<Student> studentList = new ArrayList<>();
        for (int i=0;i<sNoAndHidList.size();i++){
            Student student =cmtDao.selectBySnoAndHid(cmtId,courseId,Integer.parseInt( sNoAndHidList.get(i)[1]),sNoAndHidList.get(i)[0]);
            studentList.add(student) ;
        }
        System.out.println("未提交的学生名单");
        for (Student student : studentList){
            System.out.println(student.toString());
        }
        String excelFile = CreateExcel.getExcelFile(JasConstant.DO_NOT_EXCEL_NAME,courseName+JasConstant.DO_NOT_SUBMIT_lIST_TITLE, studentList);

        return excelFile;
    }


    @Override
    public List<Student> getShouldSubmit(String cmtId, String courseId) {
        return cmtDao.selectShouldSubmit(cmtId,courseId);
    }

    @Override
    public List<String> getNoSubmit(String cmtId,String courseId){
        List<Student> shouldList = cmtDao.selectShouldSubmit(cmtId,courseId);
        List<String> shouldStringList =new ArrayList<>();
        for (int i=0;i<shouldList.size();i++){
            for (int j=0;j<shouldList.get(i).getScores().size();j++){
                String sNoSpeliceHno =shouldList.get(i).getsNo()+"-"+shouldList.get(i).getScores().get(j).gethId();
                shouldStringList.add(sNoSpeliceHno);
            }
        }
        List<Student> hadList = cmtDao.selectStatisticsSubmit(cmtId,courseId);
        List<String> hadStringList= new ArrayList<>();
        if (hadList.size()>0){
            for (int i2= 0;i2<hadList.size();i2++){
                for (int j2=0;j2<hadList.get(i2).getScores().size();j2++){
                    String sNoSpeliceHno = hadList.get(i2).getsNo()+"-"+hadList.get(i2).getScores().get(j2).gethId();
                    hadStringList.add(sNoSpeliceHno);
                }
            }
        }

        System.out.println("应交的");
        for (int i=0;i<shouldStringList.size();i++){
            System.out.println(shouldStringList.get(i));
        }
        System.out.println("已交的");
        if (hadStringList.size()>0){
            for (int i=0;i<hadStringList.size();i++){
                System.out.println(hadStringList.get(i));
            }
        }


        List<String> notList = new ArrayList<>();
        for (int i=0;i<shouldStringList.size();i++){
            boolean exit=false;
            if (hadStringList.size()>0){
                for (int j=0;j<hadStringList.size();j++){
                    if (shouldStringList.get(i).equals(hadStringList.get(j))){
                        exit = true;
                        break;
                    }
                }
                if (!exit){
                    notList.add(shouldStringList.get(i));
                }
            }else {
                notList.add(shouldStringList.get(i));
            }

        }
          return notList;
    }




    // 生成六位数字的验证码
    public String productAutoCode(){
        StringBuffer authCode= new StringBuffer();
        Random random = new Random();
        for (int i=0;i<6;i++){
            authCode.append(random.nextInt(10));
        }
        return authCode.toString();
    }

    // 将生成的验证码存入redis
    public boolean saveAuthCode(String emailAndSutNo,String authCode){
        boolean keyExist = false;
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 该键值对是否已存在
            keyExist = jedis.exists(emailAndSutNo);
            //如果reids中不存在，存到redis中，存在说明验证码还没过期
            if (!keyExist) {
                // 将key、value存入redis、并指定过期时间
                jedis.setex(emailAndSutNo,JasConstant.AUTH_CODE_OUT_TIME, authCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }
        return keyExist;
    }
}
