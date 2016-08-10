package com.gto.bang.service.impl;

import com.gto.bang.dao.CommentDao;
import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.dao.MessageDao;
import com.gto.bang.dao.QuestionDao;
import com.gto.bang.service.MessageService;
import com.gto.bang.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong on 16/7/3.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    ExperienceDao experienceDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public Boolean createNewMessage(MessageVO messageVO) {
        return messageDao.createNewMessage(messageVO);
    }

//    @Override
//    public List<MessageVO> getMessageByArtId(int userId, int msgType, int artId, int startId) {
//        return null;
//    }

//    /**
//     * 获取未读的评论列表
//     * @param userId
//     * @return
//     */
//    @Override
//    public List<CommentVO> getMessageList(int userId) {
//
//        List<Integer> quesList=questionDao.getQuesArtIdsByUserid(userId);
//        List<Integer> expList=experienceDao.getExpArtIdsByUserid(userId);
//
//        List<CommentVO> list1=commentDao.getUnReadCommentListByArtIds(quesList, Constant.ART_QUESTION);
//        List<CommentVO> list2=commentDao.getUnReadCommentListByArtIds(expList, Constant.ART_QUESTION);
//
//        return list1;
//    }

    @Override
    public List<MessageVO> getSystemMessage(int userId, int startId) {
        return messageDao.getSystemMessage(userId,startId);
    }

    @Override
    public Boolean udpateStatus(String ids) {
        return messageDao.updateStatus(ids);
    }
}
