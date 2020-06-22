package com.gto.bang.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.model.Article;
import com.gto.bang.model.ArticleWithBLOBs;
import com.gto.bang.service.ArticleService;
import com.gto.bang.util.PageInfoUtil;
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
 * 文章(经验|问答控制器)
 * Created by shenjialong on 16/6/21.
 * 2020年 2月1日重构
 */
@Controller
public class ArticleController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;


    /**
     * 20200620 文章点赞功能
     *
     * @param userId
     * @param articleId
     * @return
     * @throws IOException
     * @// TODO: 20/6/20 代码逻辑未实现
     */
    @RequestMapping(value = "/v2/article/search")
    @ResponseBody
    public Map<String, Object> search(Integer userId, Integer articleId) throws IOException {
        LOGGER.info("pv|article|praise ....userId={},articleId={}", userId, articleId);
        return supports(Constant.SUCCESS);
    }



    /**
     * 20200620 文章点赞功能
     *
     * @param userId
     * @param articleId
     * @return
     * @throws IOException
     * @// TODO: 20/6/20 代码逻辑未实现
     */
    @RequestMapping(value = "/v2/article/praise")
    @ResponseBody
    public Map<String, Object> praise(Integer userId, Integer articleId) throws IOException {
        LOGGER.info("pv|article|praise ....userId={},articleId={}", userId, articleId);
        return supports(Constant.SUCCESS);
    }

    /**
     * 20200620 文章收藏功能
     *
     * @param userId
     * @param articleId
     * @return
     * @throws IOException
     * @// TODO: 20/6/20 代码逻辑未实现
     */
    @RequestMapping(value = "/v2/article/collection")
    @ResponseBody
    public Map<String, Object> collection(Integer userId, Integer articleId) throws IOException {
        LOGGER.info("pv|article|collection ....userId={},articleId={}", userId, articleId);
        return supports(Constant.SUCCESS);
    }


    /**
     * 文章内容审核接口
     * 新建的文章内容都默认是审核通过的
     * 管理员不定期后台抽查，有不符合要求的，将审核状态置为不通过
     *
     * @param param
     * @param token
     * @return
     * @throws IOException
     * @date 20200529
     */
    @RequestMapping(value = "/v2/article/audit")
    @ResponseBody
    public Map<String, Object> update(ArticleWithBLOBs param, String token) throws IOException {
        LOGGER.info("pv|article|audit ....param={},token={}", JSON.toJSONString(param), token);
        boolean result = checkToken(token);
        if (result) {
            articleService.updateArticle(param);
            return supports(Constant.SUCCESS);
        }
        return fail(Constant.FORBIDDEN_PERMISSION);
    }

    /**
     * @param param
     * @return
     * @throws IOException
     * @date 20200529 暂时没有功能使用
     */
    @RequestMapping(value = "/v2/article/update")
    @ResponseBody
    public Map<String, Object> update(ArticleWithBLOBs param) throws IOException {
        LOGGER.info("pv|article|update ....param={}", JSON.toJSONString(param));
        articleService.updateArticle(param);
        return supports(Constant.SUCCESS);
    }


    @RequestMapping(value = "/v1/article/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getArticleListV1(Integer type, PageInfo<Article> page, Integer articleType, Integer userId) throws IOException {

        LOGGER.info("pv|getArticleList .... type={}, pageNum={}, articleType={}, userId={}",
                type, page.getPageNum(), articleType, userId);
        PageInfoUtil.setDefaultValue(page);
        if (type == null) {
            return fail(Constant.PARAM_ERROR);
        } else {
            PageInfo<Article> list = articleService.getArticleList(type, page, articleType, userId);
            //端上未兼容,暂时以这种形式返回
            return supports(list);
        }

    }

    /**
     * 获取文章列表数据 type来区分经验列表或问答列表
     * isHot
     *
     * @param type
     * @param page
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/getArticleList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getArticleList(Integer type, PageInfo<Article> page, Integer isHot, Integer userId) throws IOException {

        LOGGER.info("pv|getArticleList .... type={},pageNum={}", type, page.getPageNum());
        PageInfoUtil.setDefaultValue(page);
        if (type == null) {
            return fail(Constant.PARAM_ERROR);
        } else {
            PageInfo<Article> list = articleService.getArticleList(type, page, isHot, userId);
            //端上未兼容,暂时以这种形式返回
            return supports(list.getList());
        }
    }

    @RequestMapping(value = "/getArticleDetail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|getArticleDetail ....");
        Response<Article> res = new Response<Article>();
        response.setContentType("text/html;charset=utf-8");
        Object id = request.getParameter("id");
        if (null == id) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            Integer i = Integer.valueOf(id.toString());
            Article article = articleService.getArticle(i);
            //阅读数+1
            articleService.updateViewTimes(article);
            res.setData(article);
        }
        super.flushResponse(response, res);
    }

    @RequestMapping(value = "/create.ajax")
    @ResponseBody
    public void create(ArticleWithBLOBs param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.info("pv|createArticle ....param={}", JSON.toJSONString(param));
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf-8");
        Response<String> res = new Response<String>();
        boolean result = false;
        Byte type = param.getType();

        // 黑名单校验
        if (checkBlackList(String.valueOf(param.getAuthorid()))) {
            LOGGER.info("article|create|forbidden ....userId={}", param.getAuthorid());
            super.flushResponse4Error(response, res, Constant.FORBIDDEN);
            return;
        }

        //兼容端上的老版本
        param.setAuthorId(param.getAuthorid());

        if (null != type) {
            if (Constant.ART_COMPLAINT == type) {
                if (param.getAuthorid() != null && StringUtils.isNotBlank(param.getContent())) {
                    param.setTitle("");
                    articleService.createNewArticle(param);
                    result = true;
                }
            } else if (Constant.ART_EXPERIENCE == type) {
                if (param.getAuthorid() != null && StringUtils.isNotBlank(param.getContent()) && StringUtils.isNotBlank(param.getTitle())) {
                    articleService.createNewArticle(param);
                    result = true;
                }
            } else if (Constant.ART_QUESTION == type) {
                if (param.getPrice() != null && param.getAuthorid() != null && StringUtils.isNotBlank(param.getContent())) {
                    param.setTitle("空");
                    // 为兼容 v2/article/create接口, 如果发现问答中的price大于0,自动转换成 红包问答类型
                    int priceForInt = param.getPrice();
                    if (priceForInt > 0) {
                        param.setType((byte) Constant.ART_SUPPORT);
                    }
                    articleService.createNewArticle(param);
                    result = true;
                }
            }
        }

        if (!result) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            res.setData(Constant.SUCCESS);
            super.flushResponse(response, res);
        }

    }

    /**
     * 2019年06月02日, APP客户端2.9.6版本以后,用这个接口创建 经验\问答\吐槽
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "v2/article/create")
    @ResponseBody
    public void createArticle(ArticleWithBLOBs articleVO, String authorid, String content, Integer type, HttpServletRequest request, HttpServletResponse response) throws IOException {

        LOGGER.info("pv|v2|article|create ....authorid={},content={},type={}, params={}", authorid, content, type, JSON.toJSONString(articleVO));
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf-8");
        Response<String> res = new Response<String>();
        boolean result = false;
        // 黑名单校验
        if (checkBlackList(authorid)) {
            LOGGER.info("v2|article|create|forbidden ....userId={}", authorid);
            super.flushResponse4Error(response, res, Constant.FORBIDDEN);
            return;
        }

        if (articleVO.getAuthorid() == null || StringUtils.isBlank(articleVO.getContent())) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        }
        switch (articleVO.getType()) {
            case Constant.ART_COMPLAINT:
                articleVO.setTitle("");
                articleService.createNewArticle(articleVO);
                result = true;
                break;
            case Constant.ART_EXPERIENCE:
                if (StringUtils.isNotBlank(articleVO.getTitle())) {
                    articleService.createNewArticle(articleVO);
                    result = true;
                }
                break;

            case Constant.ART_QUESTION:
                articleVO.setTitle("空");
                articleService.createNewArticle(articleVO);
                result = true;
                break;
            case Constant.ART_SUPPORT:
                if (articleVO.getPrice() != null) {
                    articleVO.setTitle("空");
                    articleService.createNewArticle(articleVO);
                    result = true;
                }
                break;
            default:
                break;
        }
        if (!result) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            res.setData(Constant.SUCCESS);
            super.flushResponse(response, res);
        }

    }


    /**
     * 我的(经验\问答)列表
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getMyArticleList.ajax")
    @ResponseBody
    public void getMyArticleList(Integer startid, Integer authorid, Integer type, HttpServletResponse response) throws IOException {
        Response<List<Article>> res = new Response<List<Article>>();
        if (null == startid || null == authorid || null == type) {
            super.flushResponse4Error(response, res, Constant.PARAM_ERROR);
            return;
        } else {
            List<Article> list = articleService.getArticleListByUserid(authorid, startid, type);
            res.setData(list);
        }
        super.flushResponse(response, res);
    }

}
