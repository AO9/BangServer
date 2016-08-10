package com.gto.bang.dao.impl;

import com.gto.bang.dao.FeedbackDao;
import com.gto.bang.vo.FeedbackVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class FeedbackDaoImpl implements FeedbackDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(FeedbackDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewFeedback(FeedbackVO vo) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "insert into feedback (userid,content,createtime) values (?,?,?)";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{vo.getUserId(),vo.getContent(),df.format(new Date())}, new int[]{Types.INTEGER,
                        Types.VARCHAR, Types.DATE});
        return num >= 1;
    }
}
