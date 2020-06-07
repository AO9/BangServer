package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.service.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Controller
public class SpiderController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpiderController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/spider/start")
    @ResponseBody
    public Map<String, Object> spider(String url) throws IOException {
        LOGGER.info("pv|v1|spider|start ....url={}", url);
        int result = handlerList(url);
        LOGGER.info("pv|v1|spider|end ....url={}", url);
        return super.supports(result);

    }


    //    public String handlerDetail(String url) {
//
//        RestTemplate restTemplate = new RestTemplate();
////        String url = "http://www.biyehome.net/show-20-2871-1.html";
//        String result = restTemplate.getForObject(url, String.class);
//        LOGGER.info("pv|v1|spider ....result={}", JSON.toJSONString(result));
//
//        String seperation = "<span style=\"font-family:宋体\">";
//        String[] values = result.split(seperation);
//
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < values.length; i++) {
//            if (i == 0) {
//                continue;
//            }
//            String tem = values[i];
//            LOGGER.info("pv|v1|spider ....tem = {}", JSON.toJSONString(tem));
//            int end = tem.indexOf("</span>");
//            String str = tem.substring(0, end);
//            LOGGER.info("pv|v1|spider ....str = {}", JSON.toJSONString(str));
//            sb.append(str);
//        }
//
//        LOGGER.info("pv|v1|spider ....str = {}", JSON.toJSONString(sb.toString()));
//        return sb.toString();
//
//    }
    public String handlerDetail(String url) {

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        String seperation = null;

        //第一种情况
        if (result.indexOf("</p><p><br/></p><p>") + 1 > 0) {

            String beginFlag = "<div class=\"news-content\">";
            String endFlag = "<div style=\"clear:both\">";
            int begin = result.indexOf(beginFlag);
            int end = result.indexOf(endFlag);
            result = result.substring(begin, end);
            result = result.replace(beginFlag, "");
            result = result.replace("</p>", "");
            result = result.replace("<p>", "");
            result = result.replace("<br/>", "");
            result = result.replace(" ", "");
            LOGGER.info("pv|v1|spider ....content = {}", result);
            return result;
        } else if (result.indexOf("<span style=\"font-family:宋体\">") + 1 > 0) {
            seperation = "<span style=\"font-family:宋体\">";
            String[] values = result.split(seperation);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < values.length; i++) {
                if (i == 0) {
                    continue;
                }
                String tem = values[i];
                int end = tem.indexOf("</span>");
                String str = tem.substring(0, end);
                sb.append(str);
            }
            LOGGER.info("pv|v1|spider ....content = {}", JSON.toJSONString(sb.toString()));
            return sb.toString();
        }
        LOGGER.error("pv|v1|spider ....url = {} is excepted!!!", url);
        return null;

    }


    public int handlerList(String url) {
        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://www.biyehome.net/list-18-0.html";
        String result = restTemplate.getForObject(url, String.class);

        String separation = "<li class=\"news-item clearfix\">";
        String[] strArr = result.split(separation);

        for (int i = 0; i < strArr.length; i++) {
            if (i == 0) {
                continue;
            }
            String tem = strArr[i];
            String[] values = tem.split("\"");

            String href = values[1];
            String title = values[3];
            LOGGER.info("pv|v1|handlerList ....result={},href={}", JSON.toJSONString(values[1]));
            LOGGER.info("pv|v1|handlerList ....result={},title={}", JSON.toJSONString(values[3]));
            String content = handlerDetail(href);
            if (StringUtils.isBlank(content)) {
                continue;
            }

            insert(title, content);

        }

        return strArr.length - 1;
    }

    public void insert(String title, String content) {
        ArticleWithBLOBs article = new ArticleWithBLOBs();
        article.setType((byte) 5);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthorId(15194);
        article.setAuthorid(15194);
        article.setUsername("论文帮小助手");
        articleService.createNewArticle(article);
    }


}
