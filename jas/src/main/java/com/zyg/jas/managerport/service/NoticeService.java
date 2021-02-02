package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.Notice;

import java.util.List;

public interface NoticeService {

    Integer saveNotice(Notice notice);

    List<Notice> getNoticeByCmtId(String cmtId,Integer pageNo,Integer pageSize);

    Integer getTotal(String cmtId);
}
