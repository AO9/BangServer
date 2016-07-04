package com.gto.bang.dao.impl;

import com.gto.bang.dao.QuestionDao;
import com.gto.bang.vo.QuestionVO;
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
 * Created by shenjialong on 16/6/28.
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(QuestionDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewQuestion(QuestionVO vo){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        LOGGER.info("[Question][create] 创建新经验:{},password:{},phone:{}",
                vo.getqTitle(), vo.getqUserid());

        String sql = "insert into question (qtitle,quserid,qcontent,qcreatetime,qupdatetime) values (?,?,?,?,?)";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{vo.getqTitle(),
                        vo.getqUserid(), vo.getqContent(),
                        df.format(new Date()), df.format(new Date())
                        }, new int[]{Types.VARCHAR,
                        Types.INTEGER, Types.VARCHAR, Types.DATE, Types.DATE});

        return num >= 1;
    }

    @Override
    public QuestionVO getQuestionDetail(Integer id) {
        QuestionVO vo;
        LOGGER.info("[Question][getQuestionDetail] by id:{},id:{}", id);
        String sql = "SELECT * FROM question WHERE id=?";
        try {
            vo = jdbcTemplate.queryForObject(sql, new Object[]{id},new QuestionVO());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;
    }


    @Override
    public List<QuestionVO> getQuestionList(Integer startId) {

        List<QuestionVO> list=new ArrayList<QuestionVO>();
        LOGGER.info("[Question][getQuestionList] by startId:{}", startId);
        StringBuilder sb =new StringBuilder("select * from question");
        Object [] params=new Object[]{startId};
        sb.append(" where quserid>?");
        sb.append(" order by qcreatetime desc limit 20");

        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            QuestionVO vo  = new QuestionVO();
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setqAnwserNum(map.get("qanwsernum").toString());
            vo.setqTitle(map.get("qtitle").toString());
            vo.setqContent(map.get("qcontent").toString());
            vo.setqCreateTime(map.get("qcreatetime").toString());
            vo.setqUserid(Integer.valueOf(map.get("quserid").toString()));
            vo.setqUpdateTime(map.get("qupdatetime").toString());
            list.add(vo);
        }

        return list;
    }

    @Override
    public List<QuestionVO> getQuestionListByUserid(Integer userid, Integer startId) {


        List<QuestionVO> list=new ArrayList<QuestionVO>();
        LOGGER.info("[Question][getQuestionList] by startId:{}", startId);
        StringBuilder sb =new StringBuilder("select * from question");
        Object [] params=new Object[]{userid,startId};
        sb.append(" where quserid=? and id>?");
        sb.append(" order by qcreatetime desc limit 20");

        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            QuestionVO vo  = new QuestionVO();
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setqAnwserNum(map.get("qanwsernum").toString());
            vo.setqTitle(map.get("qtitle").toString());
            vo.setqContent(map.get("qcontent").toString());
            vo.setqCreateTime(map.get("qcreatetime").toString());
            vo.setqUserid(Integer.valueOf(map.get("quserid").toString()));
            vo.setqUpdateTime(map.get("qupdatetime").toString());
            list.add(vo);
        }
//        this.test(userid,startId);
        return list;
    }




    /**
     * 根据userid获取用户的文章ID列表,默认之需要最新的20篇
     * @param userid
     * @return
     */
    @Override
    public List<Integer> getQuesArtIdsByUserid(int userid) {
        List<Integer> list=new ArrayList<Integer>();
        LOGGER.info("[experience][getExpArtIdsByUserid] by userid:{}", userid);
        StringBuilder sb =new StringBuilder("select id from question");
        Object [] params=new Object[]{userid};
        sb.append(" where quserid=?");
        // 暂时限制微最新的20篇文章吧
        sb.append(" order by qcreatetime desc limit 20");
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            Integer id=Integer.valueOf(map.get("id").toString());
            list.add(id);
        }
        return list;
    }


//    public List<Map<String,Object>> test(Integer userid, Integer beginId) {
//        LOGGER.info("[Question][getQuestionList] by beginId:{}", beginId);
//        String sql = "SELECT * FROM question WHERE quserid = ? and id > ? order by qcreatetime desc limit 20;";
//        List<Map<String,Object>> list= jdbcTemplate.queryForList(sql, userid,beginId);
//        return list;
//    }
}
