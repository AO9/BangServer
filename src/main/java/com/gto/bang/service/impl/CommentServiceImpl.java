package com.gto.bang.service.impl;

import com.gto.bang.dao.CommentDao;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.CommentService;
import com.gto.bang.vo.CommentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
@Service
public class CommentServiceImpl implements CommentService {

    public  static Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Autowired
    ArticleService articleService;

    @Override
    public boolean createNewComment(CommentVO vo) {
        return commentDao.createNewComment(vo);
    }

    @Override
    public List<CommentVO> getCommentList(Integer type, Integer artId, Integer startId) {
        return commentDao.getCommentList(type,artId,startId);
    }


    /**
     * 根据文章IDS来获取评论列表,一次最多获取最新的20条
     * @param authorId
     * @param startId
     * @return
     */
    @Override
    public List<CommentVO> getCommentsByAuthorId(Integer authorId, Integer startId,String status) {
        List<Integer> list=articleService.getArticleIdList(authorId);

        StringBuffer sb=new StringBuffer();

        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                if(i==0){
                    sb.append(""+list.get(i));
                }else{
                    sb.append(","+list.get(i));
                }
            }
        }
        LOGGER.info("[getCommentsByAuthorId] authorId:{},list<integer>:{}",
                authorId,sb.toString());
        return commentDao.getCommentListByArtIds(sb.toString(),startId,status);
    }

    @Override
    public Boolean udpateStatus(String ids) {
        return commentDao.updateStatus(ids);
    }


}
