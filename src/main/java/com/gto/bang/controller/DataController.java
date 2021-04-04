package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.User;
import com.gto.bang.service.LogService;
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

    /**
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/v1/data", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|user|list|v1 ");
        logService.createLog(null, "user|list", null);

        Response<Map<String,String>> res = new Response<Map<String,String>>();
        response.setContentType("text/html;charset=utf-8");

        Map<String,String> result=new HashedMap();
        // 日活用户
        List<User> users = userService.getUsers(200);
        result.put("activeUserNum",String.valueOf(users.size()));
        // 新增用户
        result.put("newUserNum",String.valueOf(users.size()));
        // 新增文章
        result.put("newArticleNum",String.valueOf(users.size()));
        // 新客
        result.put("newCustomerNum",String.valueOf(users.size()));
        // 全站PV
        result.put("pv",String.valueOf(users.size()));
        // 课程报名数
        result.put("signupNum",String.valueOf(users.size()));
        // 阅读PV
        result.put("readPV",String.valueOf(users.size()));
        // 原创阅读PV
        result.put("originalReadPV",String.valueOf(users.size()));

        res.setData(result);

        super.flushResponse(response, res);
    }


}
