package com.gto.bang.service;

import com.github.pagehelper.PageInfo;
import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;

import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 */
public interface ArticleService {

    PageInfo<Article> getArticlesByKeyword(PageInfo<Article> page,String keyword);

    void updateViewTimes(Article articleVO);

    void createNewArticle(ArticleWithBLOBs articleVO);

    void updateArticle(ArticleWithBLOBs articleVO);

    ArticleWithBLOBs getArticle(Integer id);

    PageInfo<Article> getArticleList(Integer type, PageInfo<Article> page, Integer articleType, Integer userId);

    List<Article> getArticleListByUserid(Integer authorId, Integer startId, Integer type);

    List<Integer> getArticleIdList(Integer authorId, String type);

}


