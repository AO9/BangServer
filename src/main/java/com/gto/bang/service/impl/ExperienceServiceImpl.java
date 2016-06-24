package com.gto.bang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.service.ExperienceService;
import com.gto.bang.vo.ExperienceVO;

/**
 * Created by shenjialong on 16/6/23.
 */
@Service
public class ExperienceServiceImpl implements ExperienceService {
    @Autowired
    private ExperienceDao dao;

    @Override
    public Boolean createNewExperience(ExperienceVO experienceVO) {
        return dao.createNewExperience(experienceVO);
    }

	@Override
	public ExperienceVO getExperienceDetail(Integer id) {

		return null;
	}

	@Override
	public List<ExperienceVO> getExperienceList(Integer beginId) {

		return null;
	}
}
