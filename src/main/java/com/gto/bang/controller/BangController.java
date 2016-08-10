package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.service.BangService;
import com.gto.bang.vo.BangVO;
import com.gto.bang.common.net.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shenjialong on 16/8/7.
 */
@Controller
public class BangController extends BaseController  {


    @Autowired
    BangService service;

    @RequestMapping(value = "/bang.ajax")
    @ResponseBody
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        BangVO vo=new BangVO();

        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        boolean result = false;

        String [] params=new String[]{"userid","username","phone","email","school","academy","city"};

        if(super.nonNullValidate(request,params)){

            int userid=Integer.valueOf(request.getParameter("userid").toString());
            vo.setUserid(userid);
            vo.setUsername(super.trancferChinnese(request,"username"));
            vo.setPhone(super.trancferChinnese(request,"phone"));
            vo.setEmail(super.trancferChinnese(request,"email"));
            vo.setSchool(super.trancferChinnese(request,"school"));
            vo.setAcademy(super.trancferChinnese(request,"academy"));
            vo.setCity(super.trancferChinnese(request,"city"));

            result = service.createNewOrder(vo);
        }
        if (!result) {
            super.flushResponse4Error(response,res,Constant.SAVE_ERROR);
            return;
        }else{
            res.setData(Constant.SUCCESS);
            super.flushResponse(response,res);
        }

    }

}
