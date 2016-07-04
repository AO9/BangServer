package com.gto.bang.dao;

import com.gto.bang.vo.ArticleVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface ArticleDao {

    Boolean createNewArticle(ArticleVO articleVO);
	ArticleVO getArticleDetail(Integer id);
	List<ArticleVO> getArticleList(Integer beginId,Integer type);
	List<ArticleVO> getArticleListByUserid(Integer userid, Integer startId,Integer type);
	List<Integer>  getExpArtIdsByUserid(int userid);
}
