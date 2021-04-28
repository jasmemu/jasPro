package com.zyg.jas.studentport.dao;

import com.zyg.jas.common.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface AnnounceDao {
    List<Notice> selectAllAnnounce(String cmtId);//根据学委id查询所有公告
    List<Notice> selectForSearching(Map map);//搜索框使用
    Notice selectAnnounceById(Integer id);
    Integer selectTotalA(String cmtId);

}
