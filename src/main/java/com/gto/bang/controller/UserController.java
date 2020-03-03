package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.User;
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
public class UserController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @Value("${statement}")
    String statement;

    /**
     * 获取今天登录过的账号列表
     * 2020年2月1日,暂未启用,端上需要使用userName createTime 驼峰命名格式
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/v1/user/list", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|user|list|v1 ");
        Response<List<User>> res = new Response<List<User>>();
        response.setContentType("text/html;charset=utf-8");
        Object num = request.getParameter("num");
        if (null == num) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            List<User> vos = userService.getUsers(Integer.valueOf(num.toString()));

            for (int i = 0; i < vos.size(); i++) {
                User user = vos.get(i);
                user.setPhone("****");
                user.setWechat("****");
            }
            res.setData(vos);
        }
        super.flushResponse(response, res);
    }

    /**
     * 获取今天登录过的账号列表
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getUsers.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleListV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|user|getUsers| ");
        Response<List<User>> res = new Response<List<User>>();
        response.setContentType("text/html;charset=utf-8");
        Object num = request.getParameter("num");
        if (null == num) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            List<User> vos = userService.getUsers(Integer.valueOf(num.toString()));
            //向下兼容
            for (int i = 0; i < vos.size(); i++) {
                User user = vos.get(i);
                user.setUsername(user.getUserName());
                user.setCreatetime(user.getCreateTime());
            }
            res.setData(vos);
        }
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/login.ajax")
    @ResponseBody
    public void login(User userInfo, HttpServletResponse response) throws IOException {

        LOGGER.info("pv|user|login, userInfo={} ", JSON.toJSONString(userInfo));
        response.setContentType("text/html;charset=utf-8");
        Response<User> res = new Response<User>();
        if (StringUtils.isBlank(userInfo.getUsername()) || StringUtils.isBlank(userInfo.getPassword())) {
            LOGGER.info("login | fail result = {}", Constant.PARAM_ERROR);
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        }

        if (isInvalidUser(userInfo.getAndroidId())) {
            LOGGER.info("login | fail androidId = {}, 黑名单拦截", userInfo.getAndroidId());
            super.flushResponse4Error(response, res, Constant.FORBIDDEN_USER);
            return;
        }

        User user = userService.queryByUserNameAndPassword(userInfo.getUsername(), userInfo.getPassword());
        LOGGER.info("login | user = {}", JSON.toJSONString(user));
        if (user == null) {
            LOGGER.info("login | fail result = {}", Constant.LOGIN_INFO_ERROR);
            super.flushResponse4Error(response, res, Constant.LOGIN_INFO_ERROR);
            return;
        }

        //向下兼容
        user.setUsername(user.getUserName());
        user.setInfo(statement);
        res.setData(user);
        LOGGER.info("login param,userIno={}", JSON.toJSONString(user));

        user.setAndroidId(userInfo.getAndroidId());
        userService.update(user);
        userService.updateLoginTime(user.getId());
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/user/update.ajax")
    @ResponseBody
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Response<String> res = new Response<String>();
        String[] params = new String[]{"content", "userId", "updateType"};
        if (super.nonNullValidate(request, params)) {
            int userId = Integer.valueOf(request.getParameter("userId").toString());
            String content = request.getParameter("content").toString();
            int updateType = Integer.valueOf(request.getParameter("updateType").toString());
            if (!checkParam(updateType, content)) {
                super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
                return;
            }

            content = this.trancferChinnese(request, "content");
            LOGGER.info("pv|user|update,userId={},updateType={}, content={}", userId, updateType, content);
            userService.updateUserInfo(updateType, content, userId);
            res.setData(Constant.SUCCESS);
            super.flushResponse(response, res);
        }
        super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
    }


    /**
     * 校验参数  0922
     *
     * @param updateType
     * @param content
     * @return
     */
    public boolean checkParam(int updateType, String content) {
        if (updateType != Constant.UPDATE_NAME && updateType != Constant.UPDATE_REGION
                && updateType != Constant.UPDATE_SIGNATRUE && updateType != Constant.UPDATE_GENDER && updateType != Constant.UPDATE_ACADEMY) {
            return false;
        }
        if (updateType == Constant.UPDATE_GENDER) {
            if (!content.equals(Constant.BOY_FLAG) && !content.equals(Constant.GIRL_FLAG)) {
                return false;
            }
        }
        return true;
    }


    @RequestMapping(value = "/user.ajax")
    @ResponseBody
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        Response<User> res = new Response<User>();
        String[] params = new String[]{"authorid"};

        if (super.nonNullValidate(request, params)) {
            String userId = request.getParameter("authorid");
            LOGGER.info("pv|user|view, userId={}", userId);
            User condition = new User();
            condition.setId(Integer.valueOf(userId));
            User user = userService.queryUser(condition);
            if (user != null) {
                user.setPassword(null);
                user.setPhone("****");
                res.setData(user);
                super.flushResponse(response, res);
                return;
            }
            super.flushResponse4Error(response, res, Constant.USER_INFO_ERROR);
        }
        super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
    }


    /**
     * 20191208
     * 修改接口
     *
     * @param param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/register.ajax")
    @ResponseBody
    public Map<String, Object> register(User param, String username) throws IOException {

        LOGGER.info("pv|v1|register ....userInfo={}", JSON.toJSONString(param));
        // 兼容历史APP版本命名不规范问题,后续统一调整为驼峰命名规范 2020年1月31日
        if (StringUtils.isNotBlank(username)) {
            param.setUserName(username);
        }
        if (StringUtils.isBlank(param.getUserName()) || StringUtils.isBlank(param.getPassword()) || StringUtils.isBlank(param.getPhone())) {
            return super.fail(Constant.PARAM_ERROR);
        }

        if (CommonUtil.checkContent(param.getUserName())) {
            LOGGER.info("v1|register|fail ....userInfo={}" + JSON.toJSONString(param));
            return super.fail(Constant.USERNAME_ERROR);
        }

        User condition = new User();
        condition.setUserName(username);
        User user = userService.queryUser(condition);
        if (user != null) {
            LOGGER.warn("register|userName is dul.... userVo={}", JSON.toJSONString(param));
            return super.fail(Constant.REGISTER_DUL);
        }
        userService.register(param);
        Map<String, Object> map = new HashedMap();
        map.put("id", param.getId());
        return super.supports(map);

    }


    /**
     * 20200222 新版本注册入口
     *
     * @param userInfo
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1/user/register")
    @ResponseBody
    public Map<String, Object> registerV1(User userInfo) throws IOException {

        LOGGER.info("pv|v1|register ....userInfo={}", JSON.toJSONString(userInfo));
        if (StringUtils.isBlank(userInfo.getUserName()) || StringUtils.isBlank(userInfo.getPassword())
                || StringUtils.isBlank(userInfo.getPhone())) {
            return super.fail(Constant.PARAM_ERROR);
        }
        if (CommonUtil.checkContent(userInfo.getUserName())) {
            LOGGER.info("v1|register|fail ....userInfo={}" + JSON.toJSONString(userInfo));
            return super.fail(Constant.USERNAME_ERROR);
        }

        User condition = new User();
        condition.setUserName(userInfo.getUserName());
        User user = userService.queryUser(condition);
        if (user != null) {
            LOGGER.warn("register|userName is dul.... userVo={}", JSON.toJSONString(userInfo));
            return super.fail(Constant.REGISTER_DUL);
        }
        userService.register(userInfo);
        Map<String, Object> map = new HashedMap();
        map.put("id", userInfo.getId());
        return super.supports(map);
    }

    /**
     * 20191208 新版后台接口 对应3.0.8以上版本的APP
     *
     * @param userInfo
     * @throws IOException
     */
    @RequestMapping(value = "/v1/user/login")
    @ResponseBody
    public Map<String, Object> login(User userInfo) throws IOException {

        LOGGER.info("pv|v1|login ....userInfo={}" + JSON.toJSONString(userInfo));
        String userName = userInfo.getUserName();
        String password = userInfo.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return fail(Constant.PARAM_ERROR);
        }

        if (isInvalidUser(userInfo.getAndroidId())) {
            LOGGER.info("login | fail androidId = {}, 黑名单拦截", userInfo.getAndroidId());
            return super.fail(Constant.FORBIDDEN_USER);
        }

        User user = userService.queryByUserNameAndPassword(userName, password);
        if (user != null) {
            user.setInfo(statement);
            user.setAndroidId(userInfo.getAndroidId());
            userService.update(user);
            userService.updateLoginTime(user.getId());
            //向下兼容
            user.setUsername(user.getUserName());
            return supports(user);
        }
        return super.fail(Constant.LOGIN_INFO_ERROR);
    }

}
