package com.gto.bang.dao;


import com.gto.bang.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    /**
     * 2020年01月01日, 评论状态非999的, 999为人工删除的评论
     * @param artId
     * @return
     */
    int commentNumOfArticle(@Param("artId")Integer artId);

    /**
     * 2020年01月01日,这个既定实现有BUG, 不符合查看某个用户维度的维度消息数的业务需求
     * @param authorId
     * @return
     */
    int numOfUnreadComments(@Param("authorId")Integer authorId);

    List<Comment> selectByArtIds(@Param("artIds")String artIds, @Param("status")Integer status);

    List<Comment> selectByCondition(Comment condition);

    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}