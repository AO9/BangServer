package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.AccountService;
import com.gto.bang.vo.UserVo;
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

        Response<UserVo> res=new Response<UserVo>();

        String [] params=new String[]{"username","password"};
        if(super.nonNullValidate(request,params)){
            System.out.println("========== login impl");
            UserVo userBo = accountService.validate(super.trancferChinnese(request,"username"),request.getParameter("password").toString());
            if (userBo!=null){
                res.setData(userBo);
                super.flushResponse(response,res);
                return;
            }
            super.flushResponse4Error(response,res,Constant.LOGIN_INFO_ERROR);
        }
        super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
    }

    @RequestMapping(value = "/register.ajax")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<UserVo> res=new Response<UserVo >();
        response.setContentType("text/html;charset=utf-8");
        String [] params=new String[]{"username","password","phone"};


        if(!super.nonNullValidate(request,params)){
            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
        }

        System.out.println("username:"+request.getParameter("username")+" password:"+request.getParameter("password"));
        // 注册的时候还要增加判断,username不能重复
        int result=accountService.register(super.trancferChinnese(request,"username"),request.getParameter("password").toString(),request.getParameter("phone").toString());
        System.out.println("before:"+request.getParameter("username")+" after:"+super.trancferChinnese(request,"username"));
        if(result==1){
            UserVo userBo = accountService.validate(super.trancferChinnese(request,"username"),request.getParameter("password").toString());
            res.setData(userBo);
            System.out.println("返回啦 userBo:"+userBo.toString());
            super.flushResponse(response,res);
        }else if(result==-1){
            super.flushResponse4Error(response,res,Constant.REGISTER_DUL);
        }else{
            super.flushResponse4Error(response,res,Constant.REGISTER_FAILE);
        }

    }
}
