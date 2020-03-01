package com.gto.bang.service.impl;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.dao.MessageMapper;
import com.gto.bang.model.Message;
import com.gto.bang.service.MessageService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shenjialong on 16/7/3.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public void createNewMessage(Message message) {
        messageMapper.insertSelective(message);
    }

    @Override
    public List<Message> getSystemMessage(int userId, int startId) {
        Message condition = new Message();
        condition.setUserid(userId);
        return messageMapper.selectByCondition(condition);
    }

    @Override
    public List<Message> getMessageList(int userId, int status) {

        Message condition = new Message();
        condition.setUserid(userId);
        condition.setStatus(Byte.valueOf((byte) status));
        return messageMapper.selectByCondition(condition);
    }

    @Override
    public void udpateStatus(String ids) {

        String[] idArr = ids.split(",");
        List<String> list = Arrays.asList(idArr);
        Integer[] result = (Integer[]) ConvertUtils.convert(list, Integer[].class);
        for (int i = 0; i < result.length; i++) {
            Message tem = new Message();
            tem.setStatus((byte) Constant.READEN_STATUS);
            tem.setId(result[i]);
            messageMapper.updateByPrimaryKeySelective(tem);
        }
    }

}
