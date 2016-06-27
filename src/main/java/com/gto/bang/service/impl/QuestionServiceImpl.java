package com.gto.bang.service.impl;

import com.gto.bang.dao.QuestionDao;
import com.gto.bang.service.QuestionService;
import com.gto.bang.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong on 16/6/23.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao dao;

    @Override
    public Boolean createNewExperience(QuestionVO experienceVO) {
        return dao.createNewQuestion(experienceVO);
    }

	@Override
	public QuestionVO getExperienceDetail(Integer id) {
		QuestionVO vo=dao.getQuestionDetail(id);
		return vo;
	}

	@Override
	public List<QuestionVO> getExperienceList(Integer beginId) {

		return dao.getQuestionList(beginId);
	}
}
