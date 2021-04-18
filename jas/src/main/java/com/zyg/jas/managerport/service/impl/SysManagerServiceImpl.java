package com.zyg.jas.managerport.service.impl;

import com.zyg.jas.common.pojo.SysManager;
import com.zyg.jas.common.tool.constant.JasConstant;
import com.zyg.jas.common.tool.util.EmailUtil;
import com.zyg.jas.common.tool.util.Fileutil;
import com.zyg.jas.common.tool.util.JedisPoolUtil;
import com.zyg.jas.common.tool.util.ResultEntity;
import com.zyg.jas.managerport.dao.SysManagerDao;
import com.zyg.jas.managerport.service.SysManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Random;

@Service
public class SysManagerServiceImpl implements SysManagerService {

    // 发布的作业存放位置
    @Value("${jobPath}")
    private String jobPath;
    // 提交的作业存放位置
    @Value("${submitPath}")
    private String submitPath;
    // 报表存放的位置
    @Value("${autoCreateExcel}")
    private String autoCreateExcel;

    @Autowired
    private SysManagerDao sysManagerDao;
    @Override
    public SysManager getSysManagerForLogin(String account, String password) {
        return sysManagerDao.selectSysForLogin(account,password);
    }

    @Override
    public SysManager getSysManagerByAccount(String account) {
        return this.sysManagerDao.selectSysByAccount(account);
    }

    @Override
    public Integer saveSys(SysManager sysManager) {
        return this.sysManagerDao.updateSys(sysManager);
    }

    @Override
    public String getAuthCode(String email, String accout) {
        // 生成六位数字的验证码
        String authCode = productAutoCode();
        // 保存到redis,返回redis中是否有这个键值对
        boolean keyExist = saveAuthCode(email+accout,authCode);
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
                exitAuthCode = jedis.get(email+accout);
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
    public void resetPwdByAuthCode(String account, String newPWD) {
        sysManagerDao.updatePwdByAuthCode(account,newPWD);
    }

    @Override
    public String newYear(String account,String pwd) {
        SysManager sysManager = sysManagerDao.selectSysByAccount(account);
        if (sysManager.getPassword().equals(pwd)){
            sysManagerDao.deleteAllCmt();
            sysManagerDao.deleteALlStudents();
            sysManagerDao.deleteAllTeachers();
            Fileutil.delAllFile(jobPath);
            Fileutil.delAllFile(submitPath);
            Fileutil.delAllFile(autoCreateExcel);
            return ResultEntity.successWithoutData().getResult();
        }else {
            return JasConstant.NEW_YEAR_PWD_ERROR;
        }
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
                jedis.setex(emailAndSutNo, JasConstant.AUTH_CODE_OUT_TIME, authCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }
        return keyExist;
    }


}
