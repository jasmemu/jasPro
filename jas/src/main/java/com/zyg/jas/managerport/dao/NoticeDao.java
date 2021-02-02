package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Notice;

import java.util.List;

public interface NoticeDao {

     int insertNotice(Notice notice);

     List<Notice> selectAllNotice(String cmtId);

     Integer selectTotal(String cmtId);
}
