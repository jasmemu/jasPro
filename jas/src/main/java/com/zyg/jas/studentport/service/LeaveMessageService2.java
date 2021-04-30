package com.zyg.jas.studentport.service;

import com.zyg.jas.common.pojo.LeaveMessage;

import java.util.List;
import java.util.Map;

public interface LeaveMessageService2 {

   Integer SaveMessage(LeaveMessage leaveMessage);

   List<LeaveMessage> getMessageByCmtIdWith(String comId, Integer pageNo, Integer pageSize);

   Integer getCountByCmtId(String comId);

   LeaveMessage getById(Integer id);

   List<LeaveMessage> getForSearchWith(Map paramsMap);

   Integer updateReplyById(LeaveMessage leaveMessage);

   Integer removeById(Integer id);

    Integer removeByBatch(List<LeaveMessage> leaveMessages);
//学生端使用
   List<LeaveMessage>selectMessageBySno(String sNo);
}
