package com.gto.bang.dao;

import com.gto.bang.vo.QuestionVO;

import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface QuestionDao {
    Boolean createNewQuestion(QuestionVO experienceVO);
    QuestionVO getQuestionDetail(Integer id);
    List<QuestionVO> getQuestionList(Integer beginId);
    List<QuestionVO> getQuestionListByUserid(Integer userid, Integer beginId);
    //根据用户ID获取文章ID列表
    List<Integer> getQuesArtIdsByUserid(int userid);
}
