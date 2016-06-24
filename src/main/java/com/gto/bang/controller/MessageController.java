package com.gto.bang.controller;

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
 * Created by shenjialong on 16/6/21.
 */
@Controller
public class MessageController extends BaseController {

    @Autowired
    DemoService demoService;
    @RequestMapping(value = "/getMessageList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void demoHttp(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String res=null;
        response.setContentType("text/html;charset=utf-8");

        request.getParameter("id");

        PrintWriter writer = response.getWriter();
        writer.println(res);
        writer.flush();
        writer.close();
    }
}
