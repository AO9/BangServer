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
        String[] titles = new String[]{"医疗专业", "软件工程", "金融财经","经济学",
                "开题报告咨询","选题免费咨询", "工商管理", "学前教育",
                "行政管理","语言文学","食品安全","法律"};
        String[] d1 = new String[]{"疫情相关等基础医学方向", "人工智能发展趋势", "理财产品分析股票市场国家经济等方向","新时代市场经济第三方支付等","提纲建议开题框架指导等","本硕各专业选题免费咨询",
                "工商企业管理营销策划分析等","幼儿教育幼儿园管理幼教成长培养等","围绕疫情等民生行政","汉语言文学相关","食品质量与安全","监察法公务员法相关等方向"};
        String[] d2 = new String[]{"报名 18/30人", "报名 14/20人", "报名 13/50人", "报名 23/30人",
                "报名 16/20人", "报名 11/20人", "报名 17/20人", "报名 23/40人",
                "报名 09/20人", "报名 19/30人", "报名 43/50人", "报名 23/35人"};
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
