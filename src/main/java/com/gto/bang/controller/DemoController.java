package com.gto.bang.controller;

import com.gto.bang.common.json.JsonUtil;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by shenjialong on 16/6/19.
 */
@Controller
public class DemoController extends BaseController {
    @Autowired
    DemoService demoService;
    @RequestMapping(value = "/demo.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void demoHttp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        if (null != request.getParameter("id")) {
            String id = request.getParameter("id").toString();
            String username = demoService.getUserInfo(id);
            res.setData("欢迎你:"+username);
        }else {
            res.setData("未找到相关用户信息");
        }

        PrintWriter writer = response.getWriter();
        writer.println(JsonUtil.obj2Str(res));
        writer.flush();
        writer.close();
    }
}
