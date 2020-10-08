package com.gto.bang.controller;

import com.gto.bang.service.LogService;
import com.gto.bang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * 20201007 课程功能化
 * 数据库表未打通
 */
@Controller
//@RequestMapping("/v2/course")
public class CourseController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    public static final String LOG_PREFIX = "log|v2|course|";
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @RequestMapping(value = "/v2/course/list")
    @ResponseBody
    public Map<String, Object> list(Integer userId) throws IOException {
        logService.createLog(userId, LOG_PREFIX + "list", null);
        return supports(null);
    }

    @RequestMapping(value = "/view")
    @ResponseBody
    public Map<String, Object> view(Integer userId, Integer courseId) throws IOException {
        logService.createLog(userId, LOG_PREFIX + "view", null);
        return supports("view +" + courseId);
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public Map<String, Object> create(Integer content) throws IOException {
        logService.createLog(null, LOG_PREFIX + "create", null);
        return supports(null);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer courseId) throws IOException {
        logService.createLog(null, LOG_PREFIX + "delete", null);
        return supports(null);
    }


}
