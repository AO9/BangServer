package com.gto.bang.dao.impl;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.dao.ArticleDao;
import com.gto.bang.vo.ArticleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/7/3.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao{
    public  static Logger LOGGER = LoggerFactory.getLogger(ExperienceDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewArticle(ArticleVO articleVO) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        LOGGER.info("[ArticleDaoImpl][createNewArticle] 新建文章,type:{},title:{}",
                articleVO.getType(),articleVO.getTitle());
        int num;
        StringBuilder sql=new StringBuilder();
        Object[] params;
        int [] types;
        System.out.println("系统时间:"+df.format(new Date()));
        // params里面的参数数量与sql语句中的占位符数量保持一致,否则异常
        if(Constant.ART_EXPERIENCE==articleVO.getType()){
            sql.append("insert into article (title,authorid,content,createtime,updatetime,type,keyword) values (?,?,?,?,?,?,?)");
            params=new Object[]{articleVO.getTitle(),
                    articleVO.getAuthorId(), articleVO.getContent(),
                    df.format(new Date()), df.format(new Date()),
                    articleVO.getType(),articleVO.getKeyword()};
            types=new int[]{Types.VARCHAR,
                    Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,Types.INTEGER,
                    Types.VARCHAR};
        }else {
            sql.append("insert into article (title,authorid,content,createtime,updatetime,type) values (?,?,?,?,?,?)");
            params=new Object[]{articleVO.getTitle(),
                    articleVO.getAuthorId(), articleVO.getContent(),
                    df.format(new Date()), df.format(new Date()),
                    articleVO.getType()};
            types=new int[]{Types.VARCHAR,
                    Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.TIMESTAMP,Types.INTEGER};
        }

        num = jdbcTemplate.update(sql.toString(), params, types);
        return num >= 1;

    }

    @Override
    public ArticleVO getArticleDetail(Integer id) {
        ArticleVO vo;
        LOGGER.info("[ArticleDaoImpl][getArticleDetail] by id:{},id:{}", id);
        String sql = "SELECT * FROM article WHERE id in (?)";
        try {
            vo = (ArticleVO) jdbcTemplate.queryForObject(sql, new Object[]{id}, new ArticleVO());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;
    }

    /**
     * 获取文章列表
     * @param startId 起始文章主键ID
     * @param type 文章类型:1-经验 2-问答
     * @return
     */
    @Override
    public List<ArticleVO> getArticleList(Integer startId, Integer type) {
        List<ArticleVO> list=new ArrayList<ArticleVO>();
        LOGGER.info("[ArticleDaoImpl][getArticleList] by startId:{},type:{}", startId,type);
        String sql = "SELECT * FROM article WHERE id >=? and type=?";
        Object [] params=new Object[]{startId,type};
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sql, params);
        list=transfer(maps);
        return list;
    }

    @Override
    public List<ArticleVO> getArticleListByUserid(Integer authorId, Integer startId,Integer type) {

        List<ArticleVO> list=new ArrayList<ArticleVO>();
        LOGGER.info("[Article][getArticleListByUserid] by startId:{}", startId);
        StringBuilder sb =new StringBuilder("select * from article");
        Object [] params=new Object[]{authorId,startId,type};
        sb.append(" where authorid=? and id>=? and type=?");
        sb.append(" order by createtime desc limit 20");
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        list=transfer(maps);
        return list;
    }


    /**
     * 结果转换
     * @param maps
     * @return
     */
    public  List<ArticleVO> transfer (List<Map<String,Object>> maps){

        List<ArticleVO> list=new ArrayList<ArticleVO>();
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            ArticleVO vo  = new ArticleVO();
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setType(Integer.valueOf(map.get("type").toString()));
            vo.setAuthorId(Integer.valueOf(map.get("authorid").toString()));
            // 经验有keyword属性,问答无该属性
            vo.setKeyword(map.get("keyword")==null?null:map.get("keyword").toString());
            vo.setTitle(map.get("title").toString());
            vo.setContent(map.get("content").toString());
            vo.setCreateTime(map.get("createtime").toString());
            vo.setUpdateTime(map.get("updatetime").toString());
            list.add(vo);
        }
        return list;
    }

    @Override
    public List<Integer> getExpArtIdsByUserid(int userid) {
        return null;
    }
}
