package com.gto.bang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gto.bang.controller.UserController;
import com.gto.bang.dao.ArticleMapper;
import com.gto.bang.dao.CommentMapper;
import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.model.User;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by shenjialong on 16/6/23.
 * <p>
 * 20191221 完成改造
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static Logger LOGGER = Logger.getLogger(UserController.class.getClass());

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Override
    public void updateViewTimes(Article articleVO) {
        articleMapper.updateViewTimes(articleVO.getId());
    }

    @Override
    public void createNewArticle(ArticleWithBLOBs articleVO) {
        articleVO.setCreateTime(new Date());
        articleMapper.insertSelective(articleVO);
    }

    @Override
    public void updateArticle(ArticleWithBLOBs articleVO) {
        articleMapper.updateByPrimaryKeySelective(articleVO);
    }

    @Override
    public ArticleWithBLOBs getArticle(Integer id) {

        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(id);
        if (articleWithBLOBs != null) {
            User condition = new User();
            condition.setId(articleWithBLOBs.getAuthorId());
            User userInfo = userService.queryUser(condition);
            articleWithBLOBs.setUsername(userInfo.getUserName());
        }
        return articleWithBLOBs;
    }

    @Override
    public List<Article> getArticleListByUserid(Integer authorId, Integer startId, Integer type) {
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setType(type.byteValue());
        condition.setAuthorId(authorId);
        List<Article> result = articleMapper.selectByCondition(condition);
        return result;
    }


    @Override
    public PageInfo<Article> getArticleList(Integer type, PageInfo<Article> page) {
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setType(type.byteValue());
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Article> list = articleMapper.selectByCondition(condition);
        setUpCommentNum(list);
        return new PageInfo<Article>(list);
    }

    public void setUpCommentNum(List<Article> list) {
        LOGGER.info("setUpCommentNum|begin ....");
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            Integer number = commentMapper.commentNumOfArticle(article.getId());
//            commentDao.numOfComment(article.getId());
            article.setCommentNum(number);
        }
        LOGGER.info("setUpCommentNum|end ....");
    }


    /**
     * 获取用户的文章id数量
     *
     * @param authorId
     * @param type     逗号隔开
     * @return
     */
    @Override
    public List<Integer> getArticleIdList(Integer authorId, String type) {
        ArticleWithBLOBs condition = new ArticleWithBLOBs();
        condition.setAuthorId(authorId);

        String[] typeArr = StringUtils.split(type, ",");
        List<String> typeList = Arrays.asList(typeArr);
        condition.setTypeList(typeList);

        List<Article> list = articleMapper.selectByCondition(condition);
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            ids.add(article.getId());
        }
        return ids;
    }
}
