package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.LeaveMessage;

import java.util.List;
import java.util.Map;

public interface LeaveMessageDao {

    Integer insertMessage(LeaveMessage leaveMessage);

    List<LeaveMessage> selectMessageByCmtIdWith(String comId);
    Integer selectCountByCmtId(String comId);

    LeaveMessage selectById(Integer id);

    List<LeaveMessage> selectForSearchWith(Map paramsMap);

    Integer updateReplyById(LeaveMessage leaveMessage); // 根据留言id跟新回复

    Integer deleteById(Integer id);
}
