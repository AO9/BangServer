package com.gto.bang.service;

import com.gto.bang.vo.CommentVO;

import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
public interface CommentService {
    boolean createNewComment(CommentVO vo);
    List<CommentVO> getCommentList(Integer type, Integer artId, Integer startId);

    List<CommentVO> getCommentsByAuthorId(Integer authorId, Integer startId,String status);

    Boolean udpateStatus(String ids);
}
