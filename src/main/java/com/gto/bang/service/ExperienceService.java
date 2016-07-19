package com.gto.bang.service;

import com.gto.bang.vo.ExperienceVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface ExperienceService {
    Boolean createNewExperience(ExperienceVO experienceVO);
	ExperienceVO getExperienceDetail(Integer id);
	List<ExperienceVO> getExperienceList(Integer startId);
	List<ExperienceVO> getExperienceListByUserid(Integer userid, Integer startId);
}

