package com.gto.bang.dao.impl;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.vo.ExperienceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class ExperienceDaoImpl implements ExperienceDao {

	public  static Logger LOGGER = LoggerFactory.getLogger(ExperienceDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewExperience(ExperienceVO experienceVO){

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		LOGGER.info("[experience][create] 创建新经验:{},password:{},phone:{}",
				experienceVO.geteTitle(), experienceVO.geteUserid());

		String sql = "insert into experience (etitle,euserid,econtent,ecreatetime,eupdatetime,ekeyword) values (?,?,?,?,?,?)";
		int num = jdbcTemplate.update(
				sql,
				new Object[]{experienceVO.geteTitle(),
						experienceVO.geteUserid(), experienceVO.geteContent(),
						df.format(new Date()), df.format(new Date()),
						experienceVO.geteKeyword()}, new int[]{Types.VARCHAR,
						Types.INTEGER, Types.VARCHAR, Types.DATE, Types.DATE,
						Types.VARCHAR});

		return num >= 1 ? true : false;
	}

	@Override
	public ExperienceVO getExperienceDetail(Integer id) {
		ExperienceVO vo;
		LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", id);
		String sql = "SELECT * FROM experience WHERE id in (?)";
		try {
			vo = (ExperienceVO) jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper() {

				@Override
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					ExperienceVO evo = new ExperienceVO();
					evo.setId(rs.getInt("id"));
					evo.seteKeyword(rs.getString("ekeyword"));
					evo.seteTitle(rs.getString("etitle"));
					evo.seteContent(rs.getString("econtent"));
					evo.seteCreateTime(rs.getTimestamp("ecreatetime").toString());
					return evo;
				}

			});
		}catch (EmptyResultDataAccessException e){
			vo=null;
		}
		return vo;
	}


	@Override
	public List<ExperienceVO> getExperienceList(Integer beginId) {
		List<ExperienceVO> list=new ArrayList<ExperienceVO>();
		LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", beginId);
		String sql = "SELECT * FROM experience WHERE id >=?";
		List<Map<String,Object>> maps= jdbcTemplate.queryForList(sql,new Object[]{beginId});
		for(int i=0;i<maps.size();i++){
			Map<String,Object> map=maps.get(i);
			ExperienceVO evo  = new ExperienceVO();
			evo.setId(Integer.valueOf(map.get("id").toString()));
			evo.seteKeyword(map.get("ekeyword").toString());
			evo.seteTitle(map.get("etitle").toString());
			evo.seteContent(map.get("econtent").toString());
			evo.seteCreateTime(map.get("ecreatetime").toString());
			list.add(evo);
		}

		return list;
    }

}
