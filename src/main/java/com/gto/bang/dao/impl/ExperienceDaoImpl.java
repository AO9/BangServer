package com.gto.bang.dao.impl;

import static com.gto.bang.dao.impl.AccountDaoImpl.LOGGER;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.vo.ExperienceVO;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class ExperienceDaoImpl implements ExperienceDao {
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
						Types.INTEGER, Types.VARCHAR, Types.TIME, Types.TIME,
						Types.VARCHAR});

		return num >= 1 ? true : false;
	}

	@Override
	public ExperienceVO getExperienceDetail(Integer id) {
		ExperienceVO vo;
		LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", id);

		String sql = "SELECT * FROM experience WHERE id in (?)";
		vo = jdbcTemplate.queryForObject(sql, new Object[]{id},
				ExperienceVO.class);
		return vo;
	}

	@Override
	public List<ExperienceVO> getExperienceList(Integer beginId) {
		List<ExperienceVO> list;
		LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", beginId);

		String sql = "SELECT * FROM experience WHERE id >=?";
		list = jdbcTemplate.queryForList(sql, new Object[]{beginId},
				ExperienceVO.class);
		return list;
    }

}
