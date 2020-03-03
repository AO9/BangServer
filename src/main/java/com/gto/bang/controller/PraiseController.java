package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.model.Praise;
import com.gto.bang.model.User;
import com.gto.bang.service.PraiseService;
import com.gto.bang.service.UserService;
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
 * Created by shenjialong on 16/7/1.
 */
@Controller
public class PraiseController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PraiseController.class);
    @Autowired
    PraiseService praiseService;

    @Autowired
    UserService userService;

    /**
     * @param praise
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1/praise/create")
    @ResponseBody
    public Map<String, Object> create(Praise praise) throws IOException {

        LOGGER.info("v1|pv|praise|create, commentIds={}", JSON.toJSONString(praise));
        if (StringUtils.isNotBlank(praise.getAndroidId()) || praise.getUserId() == null || praise.getCommentId() == null) {
            return fail(Constant.PARAM_ERROR);
        }

        User condition=new User();
        condition.setId(praise.getUserId());
        // 校验用户ID信息是否有效
        User user=userService.queryUser(condition);
        if (user==null){
            return fail(Constant.VALUE_EXCEPTION);
        }

        // 进行设备认证,登录的时候更新过androidId字段,此时进行二次确认,避免作弊
        if(StringUtils.isNotBlank(user.getAndroidId())){
            if (!user.getAndroidId().equals(praise.getAndroidId())){
                return fail(Constant.VALUE_EXCEPTION);
            }
        }

        praiseService.create(praise);
        return supports(SUCCESS);
    }

}



