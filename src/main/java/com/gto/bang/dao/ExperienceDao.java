package com.gto.bang.dao;

import com.gto.bang.vo.ExperienceVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface ExperienceDao {

    Boolean createNewExperience(ExperienceVO experienceVO);
	ExperienceVO getExperienceDetail(Integer id);
	List<ExperienceVO> getExperienceList(Integer beginId);
	List<ExperienceVO> getExperienceListByUserid(Integer userid, Integer startId);
	List<Integer>  getExpArtIdsByUserid(int userid);
}
