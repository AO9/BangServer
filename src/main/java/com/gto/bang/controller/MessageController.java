package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.Message;
import com.gto.bang.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/7/03.
 * udpate 16/08/08 系统通知类
 * TODO 还要增加接口,用于设置用户的已读状态
 */
@Controller
public class MessageController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/mCreate.ajax")
    @ResponseBody
    public Map<String, Object> create(Message param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|message|create, param={}", JSON.toJSONString(param));
        if (param.getUserid() != null && StringUtils.isNotBlank(param.getMsginfo())) {
            messageService.createNewMessage(param);
            return supports(SUCCESS);
        } else {
            return fail(Constant.PARAM_ERROR);
        }

    }


    /**
     * 通知
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMessageList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getSystemMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|message|list ");
        Response<List<Message>> res = new Response<List<Message>>();
        response.setContentType("text/html;charset=utf-8");
        if (null == request.getParameter("userId")) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            try {
                Integer userId = Integer.valueOf(request.getParameter("userId").toString());
                List<Message> vo = messageService.getSystemMessage(userId, 0);
                res.setData(vo);
                super.flushResponse(response, res);
            } catch (NumberFormatException e) {
                super.flushResponse4Error(response, res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

    }


    @RequestMapping(value = "/numOfUnReadSystemMessage.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void numOfUnReadSystemMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<String> res = new Response<String>();
        response.setContentType("text/html;charset=utf-8");
        if (null == request.getParameter("userId")) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            try {
                Integer userId = Integer.valueOf(request.getParameter("userId").toString());
                List<Message> list = messageService.getMessageList(userId, Constant.UNREAD);

                if (null == list || list.size() == 0) {
                    res.setData("0");
                } else {
                    res.setData(String.valueOf(list.size()));
                }
                super.flushResponse(response, res);
            } catch (NumberFormatException e) {
                super.flushResponse4Error(response, res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

    }


    @RequestMapping(value = "/mUpdateStatus.ajax")
    @ResponseBody
    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf-8");
        Response<String> res = new Response<String>();
        boolean result = false;

        String[] params = new String[]{"messageIds"};

        if (super.nonNullValidate(request, params)) {
            String messageIds = request.getParameter("messageIds").toString();
            messageService.udpateStatus(messageIds);
            result = true;
        }
        if (!result) {
            super.flushResponse4Error(response, res, Constant.SERVER_FAIL);
            return;
        } else {
            res.setData(Constant.SUCCESS);
            super.flushResponse(response, res);
        }

    }


    /**
     * 批量创建系统通知
     *
     * @param content
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/message/create")
    @ResponseBody
    public void createNotice(String content, int beginId, int endId, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Response<String> res = new Response<String>();
        boolean result = false;
        String[] params = new String[]{"messageIds"};
        if (StringUtils.isNotBlank(content) && beginId != 0) {
            for (int i = beginId; i <= endId; i++) {
                Message messageVO = new Message();
                messageVO.setUserid(i);
                messageVO.setMsginfo(content);
                messageService.createNewMessage(messageVO);
            }
        }
        res.setData(Constant.SUCCESS);
        super.flushResponse(response, res);

    }
}
