package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.model.Feedback;
import com.gto.bang.service.FeedbackService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shenjialong on 16/8/10
 * 反馈功能实现类
 */
@Controller
public class FeedbackController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    FeedbackService service;

    /**
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/fCreate.ajax")
    @ResponseBody
    public Map<String, Object> create(Feedback param) throws IOException {
        LOGGER.info("pv|feedback|fCreate, param={}", JSON.toJSONString(param));
        String[] params = new String[]{"userid", "content"};

        if (param.getUserid() != null && StringUtils.isNotBlank(param.getContent())) {
            service.createNewFeedback(param);
            return supports(SUCCESS);
        } else {
            return fail(Constant.PARAM_ERROR);
        }

    }

}
