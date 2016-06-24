package com.gto.bang.dao.impl;

import com.gto.bang.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public String getUserInfo(String id) {
        String sql = "SELECT username FROM user WHERE id in (?)" ;
        String username=jdbcTemplate.queryForObject(sql,new Object[]{ id },String.class);
        return username;
    }
}
