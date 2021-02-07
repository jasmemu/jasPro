package com.zyg.jas.managerport.service;

import com.zyg.jas.common.pojo.LeaveMessage;

import java.util.List;
import java.util.Map;

public interface LeaveMessageService {

   Integer SaveMessage(LeaveMessage leaveMessage);

   List<LeaveMessage> getMessageByCmtIdWith(String comId,Integer pageNo,Integer pageSize);

   Integer getCountByCmtId(String comId);

   LeaveMessage getById(Integer id);

   List<LeaveMessage> getForSearchWith(Map paramsMap);

   Integer updateReplyById(LeaveMessage leaveMessage);

   Integer removeById(Integer id);
}
