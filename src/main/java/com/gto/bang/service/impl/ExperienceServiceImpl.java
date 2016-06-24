package com.gto.bang.service.impl;

import com.gto.bang.dao.ExperienceDao;
import com.gto.bang.service.ExperienceService;
import com.gto.bang.vo.ExperienceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
