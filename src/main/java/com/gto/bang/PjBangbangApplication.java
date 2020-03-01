package com.gto.bang;

import com.alibaba.fastjson.JSON;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.gto.bang.dao")
public class PjBangbangApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PjBangbangApplication.class);

    public static void main(String[] args) {

//        handlerList();
//        handlerDetail();


//        handlerList("http://www.biyehome.net/list-18-2.html");
//        handlerDetail();

//// 临时注释
        LOGGER.info("项目启动 start  ........");
        SpringApplication.run(PjBangbangApplication.class, args);
        LOGGER.info("项目启动 end  ........");

    }


    public static int handlerList(String url) {
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
            LOGGER.info("pv|v1|handlerList ....href={}", JSON.toJSONString(values[1]));
            LOGGER.info("pv|v1|handlerList ....title={}", JSON.toJSONString(values[3]));
            String content = handlerDetail(href);

        }

        return strArr.length - 1;
    }

    public static String handlerDetail(String url) {

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


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return builder.sources(PjBangbangApplication.class);
    }
}