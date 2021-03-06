package com.zyg.jas.managerport.service.impl;

import com.github.pagehelper.PageHelper;
import com.zyg.jas.common.pojo.LeaveMessage;
import com.zyg.jas.managerport.dao.LeaveMessageDao;
import com.zyg.jas.managerport.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageDao leaveMessageDao;
    @Override
    public Integer SaveMessage(LeaveMessage leaveMessage) {
        return leaveMessageDao.insertMessage(leaveMessage);
    }

    @Override
    public List<LeaveMessage> getMessageByCmtIdWith(String comId,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return this.leaveMessageDao.selectMessageByCmtIdWith(comId);
    }

    @Override
    public Integer getCountByCmtId(String comId) {
        return this.leaveMessageDao.selectCountByCmtId(comId);
    }

    @Override
    public LeaveMessage getById(Integer id) {
        return this.leaveMessageDao.selectById(id);
    }

    @Override
    public List<LeaveMessage> getForSearchWith(Map paramsMap) {
        String flag =(String) paramsMap.get("flag");
        String comId =(String) paramsMap.get("comId");
        if (flag.equals("yes")){
            return this.leaveMessageDao.selectForSearchIsReplyWith(comId);
        }else {
            return this.leaveMessageDao.selectForSearchNotReplyWith(comId);
        }
    }

    @Override
    public Integer updateReplyById(LeaveMessage leaveMessage) {
        return this.leaveMessageDao.updateReplyById(leaveMessage);
    }

    @Override
    public Integer removeById(Integer id) {
        return this.leaveMessageDao.deleteById(id);
    }

    @Override
    public Integer removeByBatch(List<LeaveMessage> leaveMessages) {
        return leaveMessageDao.deleteByBatch(leaveMessages);
    }
}
