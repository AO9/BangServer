package com.gto.bang.controller;

import com.gto.bang.service.LogService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shenjialong on 20/6/23.
 */
@Controller
public class NoticeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Value("${statement}")
    String statement;

    @Value("${notice.title}")
    String noticeTitle;

    @Value("${notice.content}")
    String noticeContent;


    @Autowired
    LogService logService;

    @RequestMapping(value = "v1/notice/view", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> notice(String userId) throws IOException {

        LOGGER.info("pv|notice|view,userId={}", userId);
        logService.createLog(Integer.valueOf(userId),"notice|view",null);

        Map<String, String> map = new HashedMap();
        map.put("title", StringEscapeUtils.unescapeJava(noticeTitle));
        map.put("content", StringEscapeUtils.unescapeJava(noticeContent));
        return super.supports(map);

    }

    @RequestMapping(value = "statement/view", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> statement(String userId) throws IOException {
        LOGGER.info("pv|statement|view,userId={}", userId);
        return super.supports(statement);
    }

}
