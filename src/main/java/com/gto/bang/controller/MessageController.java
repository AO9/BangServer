package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.MessageService;
import com.gto.bang.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 * udpate 16/08/08 系统通知类
 * TODO 还要增加接口,用于设置用户的已读状态
 */
@Controller
public class MessageController extends BaseController {

    /**
     *
     */
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/mCreate.ajax")
    @ResponseBody
    public void create( HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        MessageVO vo=new MessageVO();

        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        boolean result = false;

        String [] params=new String[]{"userid","msginfo"};

        if(super.nonNullValidate(request,params)){

            int userid=Integer.valueOf(request.getParameter("userid").toString());
            vo.setUserId(userid);
            vo.setMsgInfo(super.trancferChinnese(request,"msginfo"));

            result = messageService.createNewMessage(vo);
        }
        if (!result) {
            super.flushResponse4Error(response,res,Constant.SERVER_FAIL);
            return;
        }else{
            res.setData(Constant.SUCCESS);
            super.flushResponse(response,res);
        }

    }


    /**
     * 通知
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMessageList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getSystemMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<MessageVO>> res = new Response<List<MessageVO>>();
        response.setContentType("text/html;charset=utf-8");
        if (null==request.getParameter("userId")) {
            super.flushResponse4Error(response,res, Constant.PARAM_ERROR);
            return;
        } else {
            try{
                Integer userId = Integer.valueOf(request.getParameter("userId").toString());
                List<MessageVO> vo = messageService.getSystemMessage(userId,0);
                res.setData(vo);
                super.flushResponse(response, res);
            }catch (NumberFormatException e){
                super.flushResponse4Error(response,res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

    }



    @RequestMapping(value = "/mUpdateStatus.ajax")
    @ResponseBody
    public void updateStatus( HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        boolean result = false;

        String [] params=new String[]{"messageIds"};

        if(super.nonNullValidate(request,params)){

            String messageIds=request.getParameter("messageIds").toString();
            result = messageService.udpateStatus(messageIds);
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
