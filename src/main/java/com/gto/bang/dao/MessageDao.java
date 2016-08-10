package com.gto.bang.dao;

import com.gto.bang.vo.MessageVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface MessageDao {

    Boolean createNewMessage(MessageVO messageVO);
//    List<MessageVO> getMessageByArtId(int userId, int msgType, int artId, int startId);
    List<MessageVO> getSystemMessage(int userId,int startId);

    Boolean updateStatus(String ids);
//    /**
//     * 获取未读的消息列表
//     * @param userId
//     * @param startId
//     * @return
//     */
//    List<MessageVO> getMessageList(int userId,int startId);

}
