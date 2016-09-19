package com.gto.bang.dao.impl;

import com.gto.bang.dao.AdminDao;
import com.gto.bang.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/7/3.
 */
@Repository
public class AdminDaoImpl implements AdminDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(ExperienceDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserVo>  getUsers(String num) {

        List<UserVo> list;
        String sql = "select * from user order by createtime desc limit "+num;
        Object [] params=new Object[]{};
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sql, params);
        list=transfer(maps);
        return list;
    }

    /**
     * 结果转换
     * @param maps
     * @return
     */
    public  List<UserVo> transfer (List<Map<String,Object>> maps){

        List<UserVo> list=new ArrayList<UserVo>();
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            UserVo vo  = new UserVo();
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setUsername(map.get("username").toString());
            vo.setPassword(map.get("password").toString());
            vo.setCity(null!=map.get("city")?map.get("city").toString():null);
            vo.setPhone(null!=map.get("phone")?map.get("phone").toString():null);
            vo.setEmail(null!=map.get("email")?map.get("email").toString():null);
            vo.setCreatetime(map.get("createtime").toString());
            list.add(vo);
        }
        return list;
    }

}
