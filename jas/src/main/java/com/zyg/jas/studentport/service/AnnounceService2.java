package com.zyg.jas.studentport.service;

import com.zyg.jas.common.pojo.Notice;

import java.util.List;

public interface AnnounceService2 {
    List<Notice> getNoticeByCmtId(String cmtId, Integer pageNo, Integer pageSize);
    Integer getTotal(String cmtId);
    List<Notice> getNoticesForSearch(String cmtId, String publishDate, String noticeTitle);
    Notice getNoticeByIdWith(Integer id);
}
