package com.gto.bang.service;

import com.gto.bang.model.Comment;

import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 * 19年12月29日 更新
 */
public interface CommentService {
    void createNewComment(Comment vo);

    /**
     * 3.1.2以前
     * @param type
     * @param artId
     * @param startId
     * @return
     */
    List<Comment> getCommentList(Integer type, Integer artId, Integer startId);

    List<Comment> getCommentsByAuthorId(Integer authorId, Integer startId, Integer status, String type);

    void udpateStatus(String ids);

    int numOfUnreadComments(Integer authorId);

    void updatePraise(Integer id);

    /**
     * 3.1.3以后
     * @param artId
     * @return
     */
    List<Comment> getCommentList(Integer artId);

}
