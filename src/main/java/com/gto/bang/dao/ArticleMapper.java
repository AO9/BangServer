package com.gto.bang.dao;


import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    // 用于好文文章列表 文章推荐 20200619
    List<Article> selectByUserId(@Param("userId")Integer userId, @Param("articleType")Integer articleType,
                                 @Param("recordIds")String recordIds);

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