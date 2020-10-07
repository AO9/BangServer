package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.common.string.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/6/19.
 */
public class BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Value("${blackList.userIds}")
    String blackList;

    @Value("${typeList}")
    String typeList;

    @Value("${invalidAndroidIds}")
    String invalidAndroidIds;

    @Value("${tokens}")
    String tokens;

    public static final String SUCCESS="success";


    public boolean checkTypeForBG(String type) {

        LOGGER.info("黑名单列表 typelist={},校验 type={}", typeList, type);
        if (StringUtils.isEmpty(typeList)) {
            return false;
        }

        String[] values = typeList.split(",");
        List<String> list = Arrays.asList(values);

        if (list.contains(type)) {
            return true;
        }else{
            LOGGER.info("非法合法 type={}", type);
        }

        return false;
    }


    /**
     * 20200529日 内容审核接口增加token校验功能
     *
     * @param token
     * @return
     */
    public boolean checkToken(String token) {

        LOGGER.info("tokens={},to be checked token={}", tokens, token);
        if (StringUtils.isEmpty(tokens)) {
            return false;
        }

        String[] values = tokens.split(",");
        List<String> list = Arrays.asList(values);

        if (list.contains(token)) {
            LOGGER.info("包含 token={}", token);
            return true;
        }

        return false;
    }

    /**
     * 是否是非法用户
     * @param androidId
     * @return
     */
    public boolean isInvalidUser(String androidId) {

        LOGGER.info("黑名单用户 invalidAndroidIds={},校验value={}", invalidAndroidIds, androidId);
        if (StringUtils.isEmpty(invalidAndroidIds)) {
            return false;
        }

        String[] values = invalidAndroidIds.split(",");
        List<String> list = Arrays.asList(values);

        if (list.contains(androidId)) {
            LOGGER.info("黑名单用户 androidId={}", androidId);
            return true;
        }

        return false;
    }


    public boolean checkBlackList(String value) {

        LOGGER.info("黑名单列表 blackList={},校验value={}", blackList, value);
        if (StringUtils.isEmpty(blackList)) {
            return false;
        }

        String[] values = blackList.split(",");
        List<String> list = Arrays.asList(values);

        if (list.contains(value)) {
            LOGGER.info("被黑名单过滤 value={}", value);
            return true;
        }

        return false;
    }

    public Map<String, Object> supports(Object data) {
        HashMap map = new HashMap();
        map.put("status", Integer.valueOf(Constant.SUCCESS_STATUS));
        map.put("data", data);
        return map;
    }

    public Map<String, Object> fail(Object data) {
        HashMap map = new HashMap();
        map.put("status", Integer.valueOf(Constant.ERROR_STATUS));
        map.put("data", data);
        return map;
    }


    // 输出结果
    public void flushResponse4Error(HttpServletResponse response, Response res,String errorMessage) throws IOException {
        res.setStatus(Constant.ERROR_STATUS);
        res.setData(errorMessage);
        PrintWriter writer = response.getWriter();
        writer.println(JSON.toJSONString(res));
        writer.flush();
        writer.close();
    }

    // 输出结果
    public void flushResponse(HttpServletResponse response, Object res) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(JSON.toJSONString(res));
        writer.flush();
        writer.close();
    }

    // 非空校验
    public boolean nonNullValidate(HttpServletRequest request, String[] params) throws IOException {

        Object param;
        for (int i=0;i<params.length;i++){
            param=request.getParameter(params[i]);
            if(StringUtil.isEmpty(param)){
                return false;
            }
        }

        return  true;
    }


    /**
     * 中文转换
     * @param request
     * @param feildName
     * @return
     * @throws IOException
     */
    public String trancferChinnese(HttpServletRequest request, String feildName) throws IOException {
        // client为 移动端时不要转码,移动端直接用utf-8请求
        if(null!=request.getParameter("client")&&request.getParameter("client").toString().equals(Constant.PC)){
            String value=new String(request.getParameter(feildName).getBytes("iso-8859-1"),"UTF-8");
            return  value;
        }
        return  request.getParameter(feildName).toString();
    }

}
