package com.gto.bang.service;

import com.gto.bang.vo.QuestionVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface QuestionService {
    Boolean createNewExperience(QuestionVO experienceVO);
	QuestionVO getExperienceDetail(Integer id);
	List<QuestionVO> getExperienceList(Integer beginId);
}


