package com.gto.bang.service.impl;

import com.gto.bang.dao.CommentDao;
import com.gto.bang.service.CommentService;
import com.gto.bang.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public boolean createNewComment(CommentVO vo) {
        return commentDao.createNewComment(vo);
    }

    @Override
    public List<CommentVO> getCommentList(Integer type, Integer artId, Integer startId) {
        return commentDao.getCommentList(type,artId,startId);
    }
}
