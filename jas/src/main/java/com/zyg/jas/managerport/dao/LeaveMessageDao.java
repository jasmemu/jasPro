package com.zyg.jas.managerport.dao;

import com.zyg.jas.common.pojo.LeaveMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LeaveMessageDao {

    Integer insertMessage(LeaveMessage leaveMessage);

    List<LeaveMessage> selectMessageByCmtIdWith(String comId);
    Integer selectCountByCmtId(String comId);

    LeaveMessage selectById(Integer id);

    List<LeaveMessage> selectForSearchNotReplyWith(String comId);
    List<LeaveMessage> selectForSearchIsReplyWith(String comId);

    Integer updateReplyById(LeaveMessage leaveMessage); // 根据留言id跟新回复

    Integer deleteById(Integer id);

    Integer deleteByBatch(@Param("leaveMessages") List<LeaveMessage> leaveMessages);
//学生端使用
    List<LeaveMessage> selectMessageBySno(String sNo);
    void sendMessageByStu(@Param("comId") String comId, @Param("sNo") String sNo,@Param("leaveMessage") String leaveMessage,@Param("lmDate") Date lmDate);
}
