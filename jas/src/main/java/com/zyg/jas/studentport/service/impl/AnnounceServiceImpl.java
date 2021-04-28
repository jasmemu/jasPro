package com.zyg.jas.studentport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.Notice;
import com.zyg.jas.studentport.dao.AnnounceDao;
import com.zyg.jas.studentport.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnnounceServiceImpl implements AnnounceService {
   @Autowired
    private AnnounceDao announceDao;

    @Override
    public List<Notice> getNoticeByCmtId(String cmtId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<Notice> list = this.announceDao.selectAllAnnounce(cmtId);
        return list;
    }

    @Override
    public Integer getTotal(String cmtId) {
        Integer total = this.announceDao.selectTotalA(cmtId);
        return total;
    }

    @Override
    public List<Notice> getNoticesForSearch(String cmtId, String publishDate, String noticeTitle) {
        Map paramsMap = new HashMap();
        paramsMap.put("cmtId",cmtId);
        paramsMap.put("publishDate",publishDate);
        paramsMap.put("noticeTitle",noticeTitle);
        return this.announceDao.selectForSearching(paramsMap);
    }

    @Override
    public Notice getNoticeByIdWith(Integer id) {
        return this.announceDao.selectAnnounceById(id);
    }
}
