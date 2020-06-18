package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.Comment;
import com.gto.bang.model.Message;
import com.gto.bang.service.CommentService;
import com.gto.bang.service.MessageService;
import com.gto.bang.util.PageInfoUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/7/1.
 */
@Controller
public class CommentController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;
    @Autowired
    MessageService messageService;

    /**
     * 用于后台功能, 评论也支持 后台审核
     *
     * @param token
     * @return
     * @throws IOException
     * @date 20200614
     */
    @RequestMapping(value = "/v1/comment/update")
    @ResponseBody
    public Map<String, Object> update(String token, String commentIds) throws IOException {
        LOGGER.info("pv|comment|audit ....commentIds={},token={}", commentIds, token);
        if (checkToken(token)) {
            commentService.udpateStatus(commentIds, Constant.DELETE_STATUS);
            return supports(Constant.SUCCESS);
        }
        return fail(Constant.FORBIDDEN_PERMISSION);
    }

    /**
     * @param condition
     * @return
     * @throws IOException
     * @date 20200614改造, 同时支持后台与客户端的需求
     * @comment  发现客户端使用的是该接口,6月15日复原为不分页的实现形式
     */
    @RequestMapping(value = "/v1/comment/list")
    @ResponseBody
    public Map<String, Object> getCommentList(PageInfo<Comment> page, Comment condition) throws IOException {
        PageInfoUtil.setDefaultValue(page);
        LOGGER.info("pv|v1|comment|list condtion={}", JSON.toJSONString(condition));
        List<Comment> list = commentService.getCommentList(condition, page);
        return super.supports(list);
    }


    /**
     * @param condition
     * @return
     * @throws IOException
     * @date 20200615 后续客户端再升级,需要使用该版本的接口
     */
    @RequestMapping(value = "/v2/comment/list")
    @ResponseBody
    public Map<String, Object> commentList(PageInfo<Comment> page, Comment condition) throws IOException {

        PageInfoUtil.setDefaultValue(page);
        LOGGER.info("pv|comment|list condtion={}", JSON.toJSONString(condition));
        List<Comment> list = commentService.getCommentList(condition, page);
        PageInfo<Comment> retureList = new PageInfo<Comment>(list);
        return super.supports(retureList);
    }


    @RequestMapping(value = "/user/numOfUnReadInfo")
    @ResponseBody
    public void getMyUnReadCommentsNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<String> res = new Response<String>();
        response.setContentType("text/html;charset=utf-8");
        Object object = request.getParameter("userId");
        int num = 0;
        if (null == object) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            try {
                Integer userId = Integer.valueOf(object.toString());
                List<Message> list = messageService.getMessageList(userId, Constant.UNREAD);
                if (!CollectionUtils.isEmpty(list)) {
                    num = list.size();
                }
                num += commentService.numOfUnreadComments(userId);
                res.setData(String.valueOf(num));
            } catch (Exception e) {
                super.flushResponse4Error(response, res, Constant.PARAM_FORMAT_ERROR);
                return;
            }
        }

        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/getCommentList.ajax")
    @ResponseBody
    public Map<String, Object> getCommentList(Integer startId, Integer artId, Integer type, HttpServletResponse response) throws IOException {

        LOGGER.info("pv|getCommentList, startId={},artId={},type={}", startId, artId, type);
        if (null == startId || null == artId || null == type) {
            return super.fail(Constant.PARAM_ERROR);
        } else {
            List<Comment> list = commentService.getCommentList(type, artId, startId);
            return super.supports(list);
        }
    }


    /**
     * 3.1.2之前的版本都用这个接口
     *
     * @param param
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/cCreate.ajax")
    @ResponseBody
    public Map<String, Object> createNewComment(Comment param, HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOGGER.info("pv|createNewComment, param={}", JSON.toJSONString(param));

        // 黑名单校验
        if (checkBlackList(String.valueOf(param.getUserId()))) {
            LOGGER.info("v1|comment|create|forbidden ....userId={}", param.getUserId());
            return fail(Constant.FORBIDDEN);
        }

        // check param values
        if (StringUtils.isBlank(param.getContent()) || StringUtils.isBlank(param.getUsername())
                || param.getUserId() == null || param.getType() == null || param.getArtId() == null) {
            return super.fail(Constant.PARAM_ERROR);
        }

        param.setArttitle("");
        commentService.createNewComment(param);
        return supports(SUCCESS);

    }

    /**
     * 3.1.3之后的都用这个新接口
     *
     * @param param
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1/comment/create")
    @ResponseBody
    public Map<String, Object> crreate(Comment param, HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOGGER.info("pv|createNewComment, param={}", JSON.toJSONString(param));

        // 黑名单校验
        if (checkBlackList(String.valueOf(param.getUserId()))) {
            LOGGER.info("v1|comment|create|forbidden ....userId={}", param.getUserId());
            return fail(Constant.FORBIDDEN);
        }

        // check param values
        if (StringUtils.isBlank(param.getContent()) || StringUtils.isBlank(param.getUsername())
                || param.getUserId() == null || param.getType() == null || param.getArtId() == null) {
            return super.fail(Constant.PARAM_ERROR);
        }

        param.setArttitle("");
        commentService.createNewComment(param);
        return supports(SUCCESS);

    }

    /**
     * 获取我的消息列表
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMyComments.ajax")
    @ResponseBody
    public Map<String, Object> getMyComments(Integer authorid, Integer startid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|getMyComments, authorid={},startid={}", authorid, startid);
        if (authorid == null | startid == null) {
            return fail(Constant.PARAM_ERROR);
        }
        List<Comment> list = commentService.getCommentsByAuthorId(authorid, startid, null, "1,2,3,4");
        return super.supports(list);
    }

    @RequestMapping(value = "/getMyUnReadCommentsNum.ajax")
    @ResponseBody
    public Map<String, Object> getMyUnReadCommentsNum(Integer authorid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (authorid == null) {
            return fail(Constant.PARAM_ERROR);
        } else {
            int num = 0;
            num = commentService.numOfUnreadComments(authorid);
            return supports(num);
        }
    }


    @RequestMapping(value = "/cUpdateStatus.ajax")
    @ResponseBody
    public Map<String, Object> updateStatus(String commentIds, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|updateStatus.ajax, commentIds={}", commentIds);
        if (StringUtils.isNotBlank(commentIds)) {
            commentService.udpateStatus(commentIds, Constant.READEN_STATUS);
            return supports(SUCCESS);
        } else {
            return fail(Constant.PARAM_ERROR);
        }
    }


    /**
     * 目的支持点赞的功能 3.1.3之后的APP新增该功能
     *
     * @param commentId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/v1/comment/price")
    @ResponseBody
    public Map<String, Object> update(String commentId, String userId, String androidId) throws IOException {

        LOGGER.info("pv|updateStatus.ajax, commentIds={}", commentId);
        if (StringUtils.isNotBlank(commentId)) {


            return supports(SUCCESS);
        } else {
            return fail(Constant.PARAM_ERROR);
        }
    }


//    @RequestMapping(value = "/v1/comment/list")
//    @ResponseBody
//    public Map<String, Object> getCommentList(Integer artId) throws IOException {
//
//        LOGGER.info("pv|comment|list artId={}", artId);
//        if (null == artId) {
//            return super.fail(Constant.PARAM_ERROR);
//        } else {
//
//            List<Comment> list = commentService.getCommentList(artId);
//            return super.supports(list);
//        }
//    }


}



