package com.gto.bang.service;

import com.gto.bang.vo.QuestionVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface QuestionService {
    Boolean createNewQuestion(QuestionVO experienceVO);
	QuestionVO getQuestionDetail(Integer id);
	List<QuestionVO> getQuestionList(Integer beginId);
	List<QuestionVO> getQuestionListByUserid(Integer userid, Integer beginId);
}


