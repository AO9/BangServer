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
 * test
 */
@Controller
@RequestMapping("/v2/course1")
public class TestCourseController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TestCourseController.class);

    public static final String LOG_PREFIX = "log|v2|course1|";
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @RequestMapping(value = "/list1")
    @ResponseBody
    public Map<String, Object> list(Integer userId) throws IOException {
        logService.createLog(userId, LOG_PREFIX + "course|list", null);
        List<Map<String, String>> courses = new ArrayList<Map<String, String>>();
        String[] titles = new String[]{"医疗专业", "软件工程", "金融专业"};
        String[] d1 = new String[]{"流感疫苗热门话题", "人工智能发展趋势", "资本市场长期改革方向"};
        String[] d2 = new String[]{"报名 18/30人", "报名 10/20人", "报名 11/50人"};
        LOGGER.info("sjlsjlsjl 1");
        LOGGER.info("sjlsjlsjl 2");
        Map<String, String> course = new HashMap<String, String>();
        for (int i = 0; i < titles.length; i++) {
            course = new HashMap<String, String>();
            course.put("courseTitle", titles[i]);
            course.put("courseDescribe1", d1[i]);
            course.put("courseDescribe2", d2[i]);
            courses.add(course);
            LOGGER.info("sjlsjlsjl for");
        }
        LOGGER.info("sjlsjlsjl 3");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", courses);
        LOGGER.info("sjlsjlsjl 4");
        return supports(data);
    }


}
