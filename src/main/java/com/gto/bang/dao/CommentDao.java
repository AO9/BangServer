package com.gto.bang.dao;

import com.gto.bang.vo.CommentVO;

import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
public interface CommentDao {
    boolean createNewComment(CommentVO vo);
    List<CommentVO> getCommentList(Integer type, Integer artId, Integer startId);
    List<CommentVO> getUnReadCommentListByArtIds(List<Integer> artIds,int type);

    List<CommentVO> getCommentListByArtIds(String artIds,int startId);
}
