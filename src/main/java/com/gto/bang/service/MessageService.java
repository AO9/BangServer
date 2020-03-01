package com.gto.bang.service;

import com.gto.bang.model.Message;

import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 */
public interface MessageService {
    void createNewMessage(Message message);

    List<Message> getSystemMessage(int userId, int startId);

    List<Message> getMessageList(int userId, int status);

    void udpateStatus(String ids);
}


