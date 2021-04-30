package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface NoticeDao2 {

    int insertNotice(Notice notice);

    List<Notice> selectAllNotice(String cmtId);

    Integer selectTotal(String cmtId);

    Notice selectByIdWith(Integer id);

    Integer deleteById(Integer id);

    List<Notice> selectForSearch(Map map);

    Integer deleteByBatch(@Param("noticeList") List<Notice> noticeList);

    //不用学委id即可获取所有公告，学生端使用该功能读取公告信息
    List<Notice> selectAllNoticeWithoutId();
}
