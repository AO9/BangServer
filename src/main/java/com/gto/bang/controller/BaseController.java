package com.gto.bang.controller;

import com.gto.bang.common.json.JsonUtil;
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
}
