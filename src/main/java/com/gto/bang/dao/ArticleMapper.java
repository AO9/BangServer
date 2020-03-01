package com.gto.bang.dao;


import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;

import java.util.List;

public interface ArticleMapper {

    void updateViewTimes(Integer artId);

    List<Integer> getArticleIdList(ArticleWithBLOBs condition);

    List<Article> selectByCondition(ArticleWithBLOBs condition);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);
}