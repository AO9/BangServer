package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.model.User;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.LogService;
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

@Controller
public class BGArticleController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BGArticleController.class);
    @Autowired
    ArticleService articleService;
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @RequestMapping(value = "/bg/v1/article/create")
    @ResponseBody
    public Map<String, Object> createArticle(ArticleWithBLOBs articleVO, String password, String userName,
                                             Integer type) throws IOException {
        LOGGER.info("pv|bg|v1|article|create +sjlsjl+ ....userName={},content={},title={}, password={}", userName, password);
        User currentUser = userService.queryByUserNameAndPassword(userName, password);
        if (currentUser == null) {
            return super.fail(Constant.LOGIN_INFO_ERROR);
        }
        logService.createLog(currentUser.getId(), "v2/article/create", null);
        if (StringUtils.isBlank(articleVO.getTitle()) || StringUtils.isBlank(articleVO.getContent()) || articleVO.getType() == null) {
            return super.fail(Constant.PARAM_ERROR);
        }
        if (!checkTypeForBG(String.valueOf(articleVO.getType()))) {
            return super.fail(Constant.TYPE_ERROR);
        }
        articleVO.setAuthorId(currentUser.getId());
        articleVO.setAuthorid(currentUser.getId());
        // 黑名单校验
        if (checkBlackList(String.valueOf(currentUser.getId()))) {
            LOGGER.info("bg|v1|article|create| 黑名单拦截 ....userId={}", currentUser.getId());
            return super.fail(Constant.FORBIDDEN);
        }
        switch (articleVO.getType()) {
            case Constant.ART_ORIGINAL:
                articleService.createNewArticle(articleVO);
                LOGGER.info("bg|v1|article|create 插入成功 ={}", JSON.toJSON(articleVO));
                break;
            default:
                break;
        }
        return supports(null);
    }


}
