package com.gto.bang.dao;

import java.util.List;

import com.gto.bang.vo.ExperienceVO;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface ExperienceDao {

    Boolean createNewExperience(ExperienceVO experienceVO);
	ExperienceVO getExperienceDetail(Integer id);
	List<ExperienceVO> getExperienceList(Integer beginId);
}
