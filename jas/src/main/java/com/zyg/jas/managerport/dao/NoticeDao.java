package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeDao {

     int insertNotice(Notice notice);

     List<Notice> selectAllNotice(String cmtId);

     Integer selectTotal(String cmtId);

     Notice selectByIdWith(Integer id);

     Integer deleteById(Integer id);

     List<Notice> selectForSearch(Map map);

    Integer deleteByBatch(@Param("noticeList") List<Notice> noticeList);
}
