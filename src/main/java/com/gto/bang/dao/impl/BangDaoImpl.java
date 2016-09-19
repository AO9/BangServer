package com.gto.bang.dao.impl;

import com.gto.bang.dao.BangDao;
import com.gto.bang.vo.BangVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shenjialong on 16/7/3.
 *
 * 0913 增加qq字段
 */
@Repository
public class BangDaoImpl implements BangDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(BangDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public Boolean createNewOrder(BangVO vo) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        int num;
        StringBuilder sql=new StringBuilder();
        Object[] params;
        int [] types;
        String qq="";
        if(null!=vo.getQq()){
            qq=vo.getQq();
        }

        sql.append("insert into bang (userid,username,phone,email,school,academy,city,createtime,qq) values (" +
                "?,?,?,?,?,?,?,?,?)");
        params=new Object[]{vo.getUserid(),vo.getUsername(),vo.getPhone(),vo.getEmail(),vo.getSchool(),
                vo.getAcademy(),vo.getCity(), df.format(new Date()),qq};
        types=new int[]{Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
                Types.VARCHAR,Types.VARCHAR, Types.TIMESTAMP,Types.VARCHAR};

        num = jdbcTemplate.update(sql.toString(), params, types);
        return num == 1;
    }
}
