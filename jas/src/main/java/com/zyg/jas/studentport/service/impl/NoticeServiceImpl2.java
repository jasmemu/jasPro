package com.zyg.jas.studentport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.dao.NoticeDao;
import com.zyg.jas.managerport.service.NoticeService;
import com.zyg.jas.studentport.dao.NoticeDao2;
import com.zyg.jas.studentport.service.NoticeService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl2 implements NoticeService2 {
    @Autowired
    private NoticeDao2 noticeDao;
    @Override
    public Integer saveNotice(Notice notice) {
        return this.noticeDao.insertNotice(notice);
    }

    @Override
    public List<Notice> getNoticeByCmtId(String cmtId,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        List<Notice> list = this.noticeDao.selectAllNotice(cmtId);
        return list;
    }

    @Override
    public Integer getTotal(String cmtId) {
        Integer total = this.noticeDao.selectTotal(cmtId);
        return total;
    }

    @Override
    public Notice getNoticeByIdWith(Integer id) {
        return  this.noticeDao.selectByIdWith(id);
    }

    @Override
    public Integer removeNoticeById(Integer id) {
        return this.noticeDao.deleteById(id);
    }

    @Override
    public List<Notice> getNoticesForSearch(String cmtId,String publishDate,String noticeTitle) {
        Map paramsMap = new HashMap();
        paramsMap.put("cmtId",cmtId);
        paramsMap.put("publishDate",publishDate);
        paramsMap.put("noticeTitle",noticeTitle);
        return this.noticeDao.selectForSearch(paramsMap);
    }

    @Override
    public Integer removeByBatch(List<Notice> noticeList) {
        return noticeDao.deleteByBatch(noticeList);
    }

    //学生端使用该功能：不用id获取公告信息
    @Override
    public List<Notice> selectAllNoticeWithoutId() {
        List<Notice> list = this.noticeDao.selectAllNoticeWithoutId();
        return list;
    }
}
