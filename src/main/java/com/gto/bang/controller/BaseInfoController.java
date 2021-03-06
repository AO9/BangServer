package com.gto.bang.controller;

import com.gto.bang.common.net.Response;
import com.gto.bang.model.Operation;
import com.gto.bang.service.LogService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
public class BaseInfoController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInfoController.class);

    @Autowired
    LogService logService;

    @Value("${baseinfo.wechat}")
    String wechat;

    @RequestMapping(value = "/base/info")
    @ResponseBody
    public Map<String, Object> list(Integer courseId) throws IOException {
        Map<String, String> data = new HashedMap();
        data.put("wechat", wechat);
        return supports(data);
    }

    /**
     * @param userId
     * @throws IOException
     */
    @RequestMapping(value = "salon/create", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void signUp(String userId, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|salon|create,userId={}", userId);
        Map<String, String> data = new HashedMap();
        Response<Map<String, String>> res = new Response<Map<String, String>>();
        response.setContentType("text/html;charset=utf-8");
        data.put("result", SUCCESS);
        res.setData(data);
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "salon/view", method = RequestMethod.GET)
    @ResponseBody
    public void getSalonInnfo(String userId, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|salon|view,userId={}", userId);
        Response<Map<String, String>> res = new Response<Map<String, String>>();
        response.setContentType("text/html;charset=utf-8");
        Map<String, String> data = new HashedMap();
        res.setData(data);
        data.put("introduce", "针对研究生论文规范性检查环节注意事项进行学术交流研讨");
        data.put("theme", "本科|研究生论文规范性审查交流会");
        data.put("lation", "北京中关村车库咖啡");
        data.put("date", "2019年12月12日 上午 10:00 至 12:00");
        super.flushResponse(response, res);
    }


}
