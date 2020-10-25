package com.gto.bang.controller;

import com.gto.bang.model.Operation;
import com.gto.bang.service.LogService;
import com.gto.bang.service.OperationService;
import com.gto.bang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 20201007 课程后台
 * 数据库表未打通
 */

@Controller
@RequestMapping("/bg/v2/course")
public class BGCourseController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BGCourseController.class);
    public static final String LOG_PREFIX = "log|bg|v2|course|";
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Autowired
    OperationService operationService;

    @RequestMapping(value = "/student/list")
    @ResponseBody
    public Map<String, Object> list(Integer courseId) throws IOException {
        logService.createLog(courseId, LOG_PREFIX + "stuedent|list", null);
        List<Operation> list= operationService.getStudents();
        return supports(list);
    }

}
