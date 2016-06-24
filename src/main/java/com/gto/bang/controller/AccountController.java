package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shenjialong on 16/6/21.
 */
@Controller
public class AccountController extends BaseController {

    @Autowired
    AccountService accountService;
    @RequestMapping(value = "/login.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        Response<String> res=new Response<String>();

        String [] params=new String[]{"username","password"};
        if(super.nonNullValidate(request,params)){
            System.out.println("========== login impl");
            boolean result= accountService.validate(request.getParameter("username").toString(),request.getParameter("password").toString());
            if (result){
                res.setData(Constant.SUCCESS);
            }else {
                res.setStatus(Constant.ERROR_STATUS);
                res.setData(Constant.FAILE);
            }
        }else{
            res.setStatus(Constant.ERROR_STATUS);
            res.setData(Constant.FAILE);
        }
        super.flushResponse(response,res);
    }

    @RequestMapping(value = "/register.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<String> res=new Response<String >();
        response.setContentType("text/html;charset=utf-8");
        String [] params=new String[]{"username","password","phone"};

        if(!super.nonNullValidate(request,params)){
            res.setStatus(Constant.ERROR_STATUS);
            res.setData(Constant.FAILE);
        }

        boolean result=accountService.register(request.getParameter("username").toString(),request.getParameter("password").toString(),request.getParameter("phone").toString());
        if(result){
            res.setData(Constant.SUCCESS);
        }else{
            res.setData(Constant.FAILE);
        }

        super.flushResponse(response,res);
    }
}
