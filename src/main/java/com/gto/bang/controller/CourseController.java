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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 20201007 课程功能化
 * 数据库表未打通
 */
@Controller
@RequestMapping("/v2/course")
public class CourseController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);

    public static final String LOG_PREFIX = "log|v2|course|";
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Map<String, Object> list(Integer userId) throws IOException {
        logService.createLog(userId, LOG_PREFIX + "course|list", null);
        List<Map<String, String>> courses = new ArrayList<Map<String, String>>();
        String[] titles = new String[]{"医疗专业", "软件工程", "金融专业","经济学"};
        String[] d1 = new String[]{"流感疫苗热门话题", "人工智能发展趋势", "资本市场长期改革方向","社会主义市场经济"};
        String[] d2 = new String[]{"报名 18/30人", "报名 10/20人", "报名 11/50人", "报名 3/30人"};
        for (int i = 0; i < titles.length; i++) {
            Map<String, String> course = new HashMap<String, String>();
            course.put("courseTitle", titles[i]);
            course.put("courseDescribe1", d1[i]);
            course.put("courseDescribe2", d2[i]);
            courses.add(course);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", courses);
        return supports(data);
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
        return supports("create + content=" + content);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Map<String, Object> delete(Integer courseId) throws IOException {
        logService.createLog(null, LOG_PREFIX + "delete", null);
        return supports("delete courseId=" + courseId);
    }


}
