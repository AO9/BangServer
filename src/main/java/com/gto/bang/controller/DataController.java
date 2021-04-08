package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.Article;
import com.gto.bang.model.Operation;
import com.gto.bang.model.User;
import com.gto.bang.service.ArticleService;
import com.gto.bang.service.LogService;
import com.gto.bang.service.OperationService;
import com.gto.bang.service.UserService;
import com.gto.bang.util.CommonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 20200222
 */
@Controller
public class DataController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Autowired
    ArticleService articleService;
    @Autowired
    OperationService operationService;

    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/v1/data", method = RequestMethod.GET)
    @ResponseBody
    public void data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|data|v1 ");
        logService.createLog(null, "user|list", null);

        Response<Map<String, String>> res = new Response<Map<String, String>>();
        response.setContentType("text/html;charset=utf-8");

        Map<String, String> result = new HashedMap();
        // 日活用户
        List<User> activeUsers = userService.getUsers(200);
        result.put("activeUserNum", String.valueOf(activeUsers.size()));
        LOGGER.info("pv|data|v1 activeUserNum={}",activeUsers.size());
        // 新增用户
        List<User> newUsers = userService.getNewUsers(200);
        result.put("newUserNum", String.valueOf(newUsers.size()));
        LOGGER.info("pv|data|v1 newUserNum={}",newUsers.size());
        // 新增文章
        List<Article> articles = articleService.getNewActicles(null);
        result.put("newArticleNum", String.valueOf(articles.size()));
        LOGGER.info("pv|data|v1 newArticleNum={}",articles.size());
        // 新客
        List<Article> supportArticles = articleService.getNewActicles(Constant.ART_SUPPORT);
        result.put("newCustomerNum", String.valueOf(supportArticles.size()));
        LOGGER.info("pv|data|v1 newCustomerNum={}",supportArticles.size());
        // APP启动
        List<Operation> launch = operationService.getPV("APP启动");
        result.put("launch", String.valueOf(launch.size()));
        LOGGER.info("pv|data|v1 launch={}",launch.size());
        // 登录
        List<Operation> login = operationService.getPV("user|login");
        result.put("login", String.valueOf(login.size()));
        LOGGER.info("pv|data|v1 login={}",login.size());
        // 全站PV
        List<Operation> pv = operationService.getPV(null);
        result.put("pv", String.valueOf(pv.size()));
        LOGGER.info("pv|data|v1 pv={}",pv.size());
        // search
        List<Operation> search = operationService.getPV("search");
        result.put("search", String.valueOf(search.size()));
        LOGGER.info("pv|data|v1 search={}",search.size());
        // 课程报名数
        List<Operation> signupPV = operationService.getStudents();
        result.put("signupPV", String.valueOf(signupPV.size()));
        LOGGER.info("pv|data|v1 signupPV={}",signupPV.size());
        // 阅读PV
        List<Operation> readPV = operationService.getPV("getArticleDetail");
        result.put("readPV", String.valueOf(readPV.size()));
        LOGGER.info("pv|data|v1 readPV={}",readPV.size());
        // 原创阅读PV
//        List<Operation> originalReadPV = operationService.getPV("");
//        result.put("originalReadPV", String.valueOf(originalReadPV.size()));

        List<Operation> noticeViewsAction = operationService.getPV("notice|view");
        result.put("noticeViewsAction", String.valueOf(noticeViewsAction.size()));
        LOGGER.info("pv|data|v1 noticeViewsAction={}",noticeViewsAction.size());

        List<Operation> answerAction = operationService.getPV("首页-提问");
        result.put("answerAction", String.valueOf(answerAction.size()));
        LOGGER.info("pv|data|v1 answerAction={}",answerAction.size());

        List<Operation> reductionAction = operationService.getPV("首页-降重");
        result.put("reductionAction", String.valueOf(reductionAction.size()));
        LOGGER.info("pv|data|v1 reductionAction={}",reductionAction.size());

        List<Operation> supportAction = operationService.getPV("首页-论文求助");
        result.put("supportAction", String.valueOf(supportAction.size()));
        LOGGER.info("pv|data|v1 supportAction={}",supportAction.size());

        List<Operation> registerAction = operationService.getPV("register.ajax");
        result.put("registerAction", String.valueOf(registerAction.size()));
        LOGGER.info("pv|data|v1 registerAction={}",registerAction.size());

        res.setData(result);

        super.flushResponse(response, res);
    }


}
