package com.zyg.jas.studentport.service;

import com.zyg.jas.common.pojo.Notice;

import java.util.List;

public interface NoticeService2 {

    Integer saveNotice(Notice notice);

    List<Notice> getNoticeByCmtId(String cmtId, Integer pageNo, Integer pageSize);

    Integer getTotal(String cmtId);

    Notice getNoticeByIdWith(Integer id);

    Integer removeNoticeById(Integer id);

    List<Notice> getNoticesForSearch(String cmtId, String publishDate, String noticeTitle);

    Integer removeByBatch(List<Notice> noticeList);

    //不用id获取公告信息，学生端使用
    List<Notice> selectAllNoticeWithoutId();
}
