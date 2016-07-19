package com.gto.bang.dao.impl;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.vo.ExperienceVO;
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

		return num >= 1;
	}

	@Override
	public ExperienceVO getExperienceDetail(Integer id) {
		ExperienceVO vo;
		LOGGER.info("[experience][getExperienceDetail] by id:{},id:{}", id);
		String sql = "SELECT * FROM experience WHERE id in (?)";
		try {
			vo = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ExperienceVO());
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
		List<Map<String,Object>> maps= jdbcTemplate.queryForList(sql, beginId);
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


	@Override
	public List<ExperienceVO> getExperienceListByUserid(Integer userid, Integer startId) {

		List<ExperienceVO> list=new ArrayList<ExperienceVO>();
		LOGGER.info("[experience][getQuestionList] by startId:{}", startId);
		StringBuilder sb =new StringBuilder("select * from experience");
		Object [] params=new Object[]{userid,startId};
		sb.append(" where euserid=? and id>?");
		sb.append(" order by ecreatetime desc limit 20");
		List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
		for(int i=0;i<maps.size();i++){
			Map<String,Object> map=maps.get(i);
			ExperienceVO vo  = new ExperienceVO();
			vo.setId(Integer.valueOf(map.get("id").toString()));
			vo.seteTitle(map.get("etitle").toString());
			vo.seteContent(map.get("econtent").toString());
			vo.seteCreateTime(map.get("ecreatetime").toString());
			vo.seteUserid(Integer.valueOf(map.get("euserid").toString()));
			vo.seteUpdateTime(map.get("eupdatetime").toString());
			list.add(vo);
		}
		return list;
	}

	/**
	 * 根据userid获取用户的文章ID列表,默认之需要最新的20篇
	 * @param userid
	 * @return
     */
	@Override
	public List<Integer> getExpArtIdsByUserid(int userid) {
		List<Integer> list=new ArrayList<Integer>();
		LOGGER.info("[experience][getExpArtIdsByUserid] by userid:{}", userid);
		StringBuilder sb =new StringBuilder("select id from experience");
		Object [] params=new Object[]{userid};
		sb.append(" where euserid=?");
		// 暂时限制微最新的20篇文章吧
		sb.append(" order by ecreatetime desc limit 20");
		List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
		for(int i=0;i<maps.size();i++){
			Map<String,Object> map=maps.get(i);
			Integer id=Integer.valueOf(map.get("id").toString());
			list.add(id);
		}
		return list;
	}
}
