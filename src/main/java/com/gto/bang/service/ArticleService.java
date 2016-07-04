package com.gto.bang.service;

import com.gto.bang.vo.ArticleVO;

import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 */
public interface ArticleService {
    Boolean createNewArticle(ArticleVO articleVO);
	ArticleVO getArticleDetail(Integer id);
	List<ArticleVO> getArticleList(Integer startId,Integer type);
	List<ArticleVO> getArticleListByUserid(Integer authorId, Integer startId,Integer type);
}


