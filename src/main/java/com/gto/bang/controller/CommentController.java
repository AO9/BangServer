package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.CommentService;
import com.gto.bang.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by shenjialong on 16/7/1.
 */
@Controller
public class CommentController extends BaseController {

    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/getCommentList.ajax")
    @ResponseBody
    public void getCommentList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<CommentVO>> res = new Response<List<CommentVO>>();
        response.setContentType("text/html;charset=utf-8");
        Object startIdObj=request.getParameter("startId");
        Object artIdObj=request.getParameter("artId");
        //type字段用于区分是 经验与问答
        Object typeObj=request.getParameter("type");

        if (null == startIdObj||null==artIdObj||null==typeObj) {
            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
            return;
        } else {
            Integer startId = Integer.valueOf(startIdObj.toString());
            Integer artId = Integer.valueOf(artIdObj.toString());
            Integer type = Integer.valueOf(typeObj.toString());
            List<CommentVO> list=commentService.getCommentList(type,artId,startId);
            res.setData(list);
        }
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/cCreate.ajax")
    @ResponseBody
    public void createNewComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<CommentVO>> res = new Response<List<CommentVO>>();
        response.setContentType("text/html;charset=utf-8");
        System.out.println("request.getCharacterEncoding():"+request.getCharacterEncoding());
        System.out.println("request.getContentType():"+request.getContentType());
        Object useridObj =request.getParameter("userId");
        //type字段用于区分是 经验与问答
        Object typeObj=request.getParameter("type");
        Object artIdObj=request.getParameter("artId");
        boolean result=false;

        Boolean isThrough=super.nonNullValidate(request,new String[]{"userId","username","content","type","artId","artTitle"});
        if(!isThrough){
            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
            return;
        }else{
            try {
                Integer userid = Integer.valueOf(useridObj.toString());
                Integer arcId = Integer.valueOf(artIdObj.toString());
                Integer type = Integer.valueOf(typeObj.toString());
                CommentVO vo = new CommentVO();
                vo.setUserId(userid);
                vo.setType(type);
                vo.setUsername(super.trancferChinnese(request, "username"));
                vo.setContent(super.trancferChinnese(request, "content"));
                vo.setActId(arcId);
                vo.setArtTitle(super.trancferChinnese(request, "artTitle"));
                result = commentService.createNewComment(vo);
            }catch (NumberFormatException e){
                super.flushResponse4Error(response,res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
            if(!result){
                super.flushResponse4Error(response,res,Constant.SAVE_ERROR);
                return;
            }
            super.flushResponse(response, res);
        }

    }


    /**
     * 获取我的消息列表
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMyComments.ajax")
    @ResponseBody
    public void getMyMessages(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<CommentVO>> res = new Response<List<CommentVO>>();
        response.setContentType("text/html;charset=utf-8");
        Object useridObj =request.getParameter("authorid");
        Object startIdObj=request.getParameter("startid");

        Boolean isThrough=super.nonNullValidate(request,new String[]{"authorid","startid"});
        if(!isThrough){
            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
            return;
        }else{
            try {

                Integer userid = Integer.valueOf(useridObj.toString());
                Integer startId = Integer.valueOf(startIdObj.toString());
                List<CommentVO> list = commentService.getCommentsByAuthorId(userid,startId);
                res.setData(list);

            }catch (NumberFormatException e){
                super.flushResponse4Error(response,res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

        super.flushResponse(response, res);
    }

}



