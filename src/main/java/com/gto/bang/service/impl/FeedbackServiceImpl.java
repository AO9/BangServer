package com.gto.bang.service.impl;

import com.gto.bang.dao.FeedbackDao;
import com.gto.bang.service.FeedbackService;
import com.gto.bang.vo.FeedbackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 16/8/10.
 * 反馈功能的实现类
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackDao dao;

    @Override
    public Boolean createNewFeedback(FeedbackVO vo) {
        return dao.createNewFeedback(vo);
    }

}
