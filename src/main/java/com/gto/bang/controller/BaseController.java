package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.json.JsonUtil;
import com.gto.bang.common.net.Response;
import com.gto.bang.common.string.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shenjialong on 16/6/19.
 */
public abstract class BaseController {


    // 输出结果
    public void flushResponse4Error(HttpServletResponse response, Response res,String errorMessage) throws IOException {


        res.setStatus(Constant.ERROR_STATUS);
        res.setData(errorMessage);

        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.obj2Str(res));
        writer.flush();
        writer.close();
    }

    // 输出结果
    public void flushResponse(HttpServletResponse response, Object res) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.obj2Str(res));
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

        System.out.println("before:"+request.getParameter(feildName).toString());

        // client为 移动端时不要转码,移动端直接用utf-8请求
        if(null!=request.getParameter("client")&&request.getParameter("client").toString().equals(Constant.PC)){
            String value=new String(request.getParameter(feildName).getBytes("iso-8859-1"),"UTF-8");
            System.out.println("after:"+value);
            return  value;
        }
        return  request.getParameter(feildName).toString();
    }

}
