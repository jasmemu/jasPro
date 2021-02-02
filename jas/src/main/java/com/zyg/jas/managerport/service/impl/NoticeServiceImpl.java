package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.managerport.dao.NoticeDao;
import com.zyg.jas.managerport.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;
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
}
