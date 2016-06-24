package com.gto.bang.service;

import java.util.List;

import com.gto.bang.vo.ExperienceVO;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface ExperienceService {
    Boolean createNewExperience(ExperienceVO experienceVO);
	ExperienceVO getExperienceDetail(Integer id);
	List<ExperienceVO> getExperienceList(Integer beginId);
}


