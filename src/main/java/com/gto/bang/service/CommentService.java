package com.gto.bang.service;

import com.github.pagehelper.PageInfo;
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
     *
     * @param type
     * @param artId
     * @param startId
     * @return
     */
    List<Comment> getCommentList(Integer type, Integer artId, Integer startId);

    List<Comment> getCommentsByAuthorId(Integer authorId, Integer startId, Integer status, String type);

    void udpateStatus(String ids, int status);

    int numOfUnreadComments(Integer authorId);

    void updatePraise(Integer id);

    /**
     * 通用查询评论的方法
     *
     * @param condition
     * @return
     * @date 20200614 同时支持后台审核评论
     */
    List<Comment> getCommentList(Comment condition, PageInfo<Comment> page);

}
