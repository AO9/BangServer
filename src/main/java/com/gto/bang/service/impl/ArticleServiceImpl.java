package com.gto.bang.service.impl;

import com.gto.bang.dao.ArticleDao;
import com.gto.bang.service.ArticleService;
import com.gto.bang.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong on 16/6/23.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao dao;

	@Override
	public Boolean createNewArticle(ArticleVO articleVO) {

		return dao.createNewArticle(articleVO);
	}

	@Override
	public ArticleVO getArticleDetail(Integer id) {

		return dao.getArticleDetail(id);
	}

	@Override
	public List<ArticleVO> getArticleList(Integer startId, Integer type) {

		return dao.getArticleList(startId,type);
	}

	@Override
	public List<ArticleVO> getArticleListByUserid(Integer authorId, Integer startId,Integer type) {

		return dao.getArticleListByUserid(authorId,startId,type);
	}
}
