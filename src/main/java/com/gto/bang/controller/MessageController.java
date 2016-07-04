package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.MessageService;
import com.gto.bang.vo.CommentVO;
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
 */
@Controller
public class MessageController extends BaseController {

    //评论的新增加的方法没有测试,为消息功能增加的方法们
    @Autowired
    MessageService messageService;
    @RequestMapping(value = "/getMessageList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getSystemMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<CommentVO>> res = new Response<List<CommentVO>>();
        response.setContentType("text/html;charset=utf-8");
        if (null==request.getParameter("userId")) {
            super.flushResponse4Error(response,res, Constant.PARAM_ERROR);
            return;
        } else {
            try{
                Integer userId = Integer.valueOf(request.getParameter("userId").toString());
                List<CommentVO> vo = messageService.getMessageList(userId);
                res.setData(vo);
                super.flushResponse(response, res);
            }catch (NumberFormatException e){
                super.flushResponse4Error(response,res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

    }
}
