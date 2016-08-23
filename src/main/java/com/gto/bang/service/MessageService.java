package com.gto.bang.service;

import com.gto.bang.vo.MessageVO;

import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 */
public interface MessageService {
    Boolean createNewMessage(MessageVO messageVO);
//	List<MessageVO> getMessageByArtId(int userId,int msgType,int artId,int startId);
	List<MessageVO> getSystemMessage(int userId,int startId);
    List<MessageVO> getMessageList(int userId,int status);
    Boolean udpateStatus(String ids);
//	List<CommentVO> getMessageList(int userId);
}


