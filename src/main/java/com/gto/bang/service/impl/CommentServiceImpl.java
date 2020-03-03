package com.gto.bang.service.impl;


import com.gto.bang.common.constant.Constant;
import com.gto.bang.dao.ArticleMapper;
import com.gto.bang.dao.CommentMapper;
import com.gto.bang.model.Comment;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.CommentService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
@Service
public class CommentServiceImpl implements CommentService {

    public static Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleService articleService;

    @Override
    public void createNewComment(Comment vo) {
        commentMapper.insertSelective(vo);
    }

    @Override
    public List<Comment> getCommentList(Integer type, Integer artId, Integer startId) {
        Comment condition = new Comment();
//        condition.setType(type.byteValue());
        condition.setArtId(artId);
        return commentMapper.selectByCondition(condition);
    }


    /**
     * 根据文章IDS来获取评论列表,一次最多获取最新的20条
     *
     * @param authorId
     * @param startId
     * @return
     */
    @Override
    public List<Comment> getCommentsByAuthorId(Integer authorId, Integer startId, Integer status, String type) {

        List<Integer> list = articleService.getArticleIdList(authorId, type);
        String articleIds = null;
        List<Comment> res = new ArrayList<Comment>();
        if (!CollectionUtils.isEmpty(list)) {
            articleIds = StringUtils.join(list, Constant.SEPRATOR);
        } else {
            return res;
        }
        res = commentMapper.selectByArtIds(articleIds, status);

        return res;
    }

    @Override
    public void udpateStatus(String ids) {

        String[] temp = StringUtils.split(ids);
        List<String> idList = Arrays.asList(temp);
        Integer[] intArr = (Integer[]) ConvertUtils.convert(idList, Integer[].class);
        for (int i = 0; i < intArr.length; i++) {
            Integer id = intArr[i];
            Comment values = new Comment();
            values.setId(id);
            values.setStatus(Constant.READEN_STATUS);
            commentMapper.updateByPrimaryKey(values);
        }
    }

    @Override
    public int numOfUnreadComments(Integer authorId) {
        return commentMapper.numOfUnreadComments(authorId);
    }

    @Override
    public void updatePraise(Integer id) {
        LOGGER.info("comment|service|updatePraise,commentId is {}",id);
        commentMapper.updatePraise(id);
    }


}
