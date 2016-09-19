package com.gto.bang.dao.impl;

import com.gto.bang.dao.AccountDao;
import com.gto.bang.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(AccountDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserVo validate(String username, String password) {

        UserVo vo;
        String sql = "SELECT id,username,password,phone,email,city,education,school,createtime FROM user WHERE username=? and password=?";
        try {
            vo = jdbcTemplate.queryForObject(sql, new Object[]{username,password}, new UserVo());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;

    }

    @Override
    public UserVo userInfo(String authorId) {

        UserVo vo;
        String sql = "SELECT id,username,password,phone,email,city,education,school,createtime FROM user WHERE id=?";
        try {
            vo = jdbcTemplate.queryForObject(sql, new Object[]{authorId}, new UserVo());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;

    }

    public boolean isExist(String username){
        String sql = "SELECT count(1) FROM user WHERE username=?";
        int result =  jdbcTemplate.queryForInt(sql, username);
        return result <= 0;
    }

    @Override
    public int insert(String username, String password, String phone,String school,String education) {
        LOGGER.info("注册用户username:{},password:{}",username,password);

        if(!isExist(username)){
            return -1;
        }

        String sql = "insert into user (username,password,phone,school,education) values (?,?,?,?,?)" ;
        int num=jdbcTemplate.update(sql,new Object[]{ username,password,phone,school,education },new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
        return num;

    }
}
