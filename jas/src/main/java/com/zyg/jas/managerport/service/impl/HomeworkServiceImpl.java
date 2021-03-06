package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Homework;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.common.tool.util.Fileutil;
import com.zyg.jas.managerport.dao.HomeworkDao;
import com.zyg.jas.managerport.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Value("${jobPath}")
    private String jobPath;
    @Value("${jobURL}")
    private String jobURL;
    @Autowired
    private HomeworkDao homeworkDao;

    @Override
    public Integer saveJob(MultipartFile multipartFile,Homework homework) throws IOException, ParseException {
        if (multipartFile.isEmpty()) {
            return 0;
        }
        String fileName = multipartFile.getOriginalFilename();
        int i = fileName.lastIndexOf(".");
        String suffix = fileName.substring(i);
        String fileUUID =  UUID.randomUUID().toString()+suffix;
        File dest = new File(new File(jobPath).getAbsolutePath()+ "/" + fileUUID);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        multipartFile.transferTo(dest);
        // 保存数据
        homework.sethName(fileName);
        homework.sethUrl(jobURL+fileUUID);
        homework.sethPath(dest.getAbsoluteFile().toString());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date now = new Date();
        String nowTime = f.format(now);
        Date date = f.parse(nowTime);
        Timestamp time = new Timestamp(date.getTime());
        homework.setPublishDate(time);
        return this.homeworkDao.insertJob(homework);
    }

    @Override
    public List<Homework> getAllJobs(String cmtId,Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Homework> list = this.homeworkDao.selectJobs(cmtId);
        return list;
    }

    @Override
    public Integer getCount(String cmtId) {
        return this.homeworkDao.selectCount(cmtId);
    }

    @Override
    public Integer removeJobById(Integer hId) {
        Homework homework = this.homeworkDao.selectJobByHid(hId);
        Fileutil.removeFile(homework.gethPath());
        return this.homeworkDao.deleteById(hId);
    }

    @Override
    public List<Homework> getForSearch(String comId,String hName) {
        Map<String,String> map = new HashMap<>();
        map.put("comId",comId);
        map.put("hName",hName);
        return this.homeworkDao.selectForSearch(map);
    }

    @Override
    public Integer removeByBatch(List<Homework> homeworks) {
        for (Homework job : homeworks){
            Fileutil.removeFile(job.gethPath());
        }
        return homeworkDao.deleteByBatch(homeworks);
    }
}
