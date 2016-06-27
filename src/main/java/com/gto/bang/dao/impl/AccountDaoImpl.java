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
        String sql = "SELECT * FROM user WHERE username=? and password=?";
        try {
            vo = (UserVo) jdbcTemplate.queryForObject(sql, new Object[]{username,password}, new UserVo());
        }catch (EmptyResultDataAccessException e){
            vo=null;
        }
        return vo;

    }

    public boolean isExist(String username){
        String sql = "SELECT count(1) FROM user WHERE username=?";
        int result =  jdbcTemplate.queryForInt(sql, new Object[]{username});
        if (result>0){
            return false;
        }
        return true;
    }

    @Override
    public int insert(String username, String password, String phone) {
        LOGGER.info("注册用户username:{},password:{},phone:{}",password,phone);

        if(!isExist(username)){
            return 0;
        }

        String sql = "insert into user (username,password,phone) values (?,?,?)" ;
        int num=jdbcTemplate.update(sql,new Object[]{ username,password,phone },new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
        return num;

    }
}
