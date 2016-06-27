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
    public  static Logger LOGGER = LoggerFactory.getLogger(ExperienceDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewQuestion(QuestionVO vo){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        LOGGER.info("[experience][create] 创建新经验:{},password:{},phone:{}",
                vo.getqTitle(), vo.getqUserid());

        String sql = "insert into experience (qtitle,quserid,qcontent,qcreatetime,qupdatetime) values (?,?,?,?,?)";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{vo.getqTitle(),
                        vo.getqUserid(), vo.getqContent(),
                        df.format(new Date()), df.format(new Date())
                        }, new int[]{Types.VARCHAR,
                        Types.INTEGER, Types.VARCHAR, Types.DATE, Types.DATE});

        return num >= 1 ? true : false;
    }

    @Override
    public QuestionVO getQuestionDetail(Integer id) {
        QuestionVO vo;
        LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", id);
        String sql = "SELECT * FROM experience WHERE id=?";
        try {
            vo = (QuestionVO) jdbcTemplate.queryForObject(sql, new Object[]{id},new QuestionVO());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;
    }


    @Override
    public List<QuestionVO> getQuestionList(Integer beginId) {
        List<QuestionVO> list=new ArrayList<QuestionVO>();
        LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", beginId);
        String sql = "SELECT * FROM experience WHERE id >=?";
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sql,new Object[]{beginId});
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            QuestionVO vo  = new QuestionVO();
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setqAnwserNum(map.get("qanwsernum").toString());
            vo.setqTitle(map.get("qtitle").toString());
            vo.setqContent(map.get("qcontent").toString());
            vo.setqCreateTime(map.get("qcreatetime").toString());
            list.add(vo);
        }

        return list;
    }
}
