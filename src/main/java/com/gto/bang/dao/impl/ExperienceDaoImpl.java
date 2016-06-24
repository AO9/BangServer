package com.gto.bang.dao.impl;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.vo.ExperienceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.gto.bang.dao.impl.AccountDaoImpl.LOGGER;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class ExperienceDaoImpl implements ExperienceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewExperience(ExperienceVO experienceVO){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        LOGGER.info("[experience][create] 创建新经验:{},password:{},phone:{}",experienceVO.geteTitle(),experienceVO.geteUserid());

        String sql = "insert into experience (etitle,euserid,econtent,ecreatetime,eupdatetime,ekeyword) values (?,?,?,?,?,?)" ;
        int num=jdbcTemplate.update(sql,new Object[]{ experienceVO.geteTitle(),experienceVO
                .geteUserid(),experienceVO.geteContent(),df.format(new Date()),df.format(new Date()),experienceVO.geteKeyword()},
                new int[]{Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIME,Types.TIME,Types.VARCHAR});

        return num>=1?true:false;
    }

}
