package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.common.string.StringUtil;
import com.gto.bang.service.AccountService;
import com.gto.bang.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @RequestMapping(value = "/login.ajax")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        Response<UserVo> res=new Response<UserVo>();

        String [] params=new String[]{"username","password"};
        if(super.nonNullValidate(request,params)){
            String username=super.trancferChinnese(request,"username").toString();
            String password=super.trancferChinnese(request,"password").toString();

            System.out.println("login controller username:"+username);
            UserVo userBo = accountService.validate(username,password);
            if (userBo!=null){
                res.setData(userBo);
                super.flushResponse(response,res);
                return;
            }
            super.flushResponse4Error(response,res,Constant.LOGIN_INFO_ERROR);
        }
        super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
    }


    @RequestMapping(value = "/user.ajax")
    @ResponseBody
    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        Response<UserVo> res=new Response<UserVo>();

        String [] params=new String[]{"authorid"};
        if(super.nonNullValidate(request,params)){
            String authorId=request.getParameter("authorid");

            UserVo userBo = accountService.userInfo(authorId);
            if (userBo!=null){
                res.setData(userBo);
                super.flushResponse(response,res);
                return;
            }
            super.flushResponse4Error(response,res,Constant.USER_INFO_ERROR);
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

        //兼容 app 1.1.1版本之前的用户
        String school="";
        String education="";
        if(!StringUtil.isEmpty(request.getParameter("school"))){
            school=super.trancferChinnese(request,"school");
        }

        if(!StringUtil.isEmpty(request.getParameter("education"))){
            education=super.trancferChinnese(request,"education");
        }

        // 注册的时候还要增加判断,username不能重复
        int result=accountService.register(super.trancferChinnese(request,"username"),request.getParameter("password").toString(),
                request.getParameter("phone").toString(),school,education);
        if(result==1){
            UserVo userBo = accountService.validate(super.trancferChinnese(request,"username"),request.getParameter("password").toString());
            res.setData(userBo);
            super.flushResponse(response,res);
        }else if(result==-1){
            super.flushResponse4Error(response,res,Constant.REGISTER_DUL);
        }else{
            super.flushResponse4Error(response,res,Constant.REGISTER_FAILE);
        }

    }
}
