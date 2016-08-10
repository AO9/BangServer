package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.FeedbackService;
import com.gto.bang.vo.FeedbackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shenjialong on 16/8/10
 * 反馈功能实现类
 */
@Controller
public class FeedbackController extends BaseController {

    @Autowired
    FeedbackService service;

    /**
     * 反馈功能的后台接口
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/fCreate.ajax")
    @ResponseBody
    public void create( HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        FeedbackVO vo=new FeedbackVO();

        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        boolean result = false;

        String [] params=new String[]{"userid","content"};

        if(super.nonNullValidate(request,params)){

            int userid=Integer.valueOf(request.getParameter("userid").toString());
            vo.setUserId(userid);
            vo.setContent(super.trancferChinnese(request,"content"));

            result = service.createNewFeedback(vo);
        }
        if (!result) {
            super.flushResponse4Error(response,res,Constant.SERVER_FAIL);
            return;
        }else{
            res.setData(Constant.SUCCESS);
            super.flushResponse(response,res);
        }

    }

}
