package com.gto.bang.service.impl;

import com.gto.bang.dao.FeedbackMapper;
import com.gto.bang.model.Feedback;
import com.gto.bang.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 16/8/10.
 * 2020年0101, mybatis重构
 * 反馈功能的实现类
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public void createNewFeedback(Feedback vo) {
        feedbackMapper.insertSelective(vo);
    }

}
