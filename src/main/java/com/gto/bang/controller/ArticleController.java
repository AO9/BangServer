package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.ArticleService;
import com.gto.bang.vo.ArticleVO;
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
 * 文章(经验|问答控制器)
 * Created by shenjialong on 16/6/21.
 */
@Controller
public class ArticleController extends BaseController {

    @Autowired
	ArticleService articleService;

	/**
	 * 获取文章列表数据 type来区分经验列表或问答列表
	 * @param request
	 * @param response
	 * @throws IOException
     */
    @RequestMapping(value = "/getArticleList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<List<ArticleVO>> res = new Response<List<ArticleVO>>();
        response.setContentType("text/html;charset=utf-8");
		Object startIdObj = request.getParameter("startid");
		Object typeObj = request.getParameter("type");
		if (null ==startIdObj || null==typeObj) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer startId = Integer.valueOf(startIdObj.toString());
			Integer type = Integer.valueOf(typeObj.toString());
			List<ArticleVO> vos = articleService.getArticleList(startId,type);
			res.setData(vos);
		}
		super.flushResponse(response, res);
    }


    @RequestMapping(value = "/getArticleDetail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void getArticleDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<ArticleVO> res = new Response<ArticleVO>();
        response.setContentType("text/html;charset=utf-8");
        Object id=request.getParameter("id");
		if (null == id) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer i = Integer.valueOf(id.toString());
			ArticleVO articleVO = articleService.getArticleDetail(i);
			res.setData(articleVO);
        }
		super.flushResponse(response, res);
    }


    @RequestMapping(value = "/create.ajax")
    @ResponseBody
    public void create( HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf8");
		ArticleVO articleVO=new ArticleVO();

		response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
		boolean result = false;

        String [] params=new String[]{"title","authorid","content","type"};

        if(super.nonNullValidate(request,params)){

			int type=Integer.valueOf(request.getParameter("type").toString());
			articleVO.setType(type);
			articleVO.setTitle(super.trancferChinnese(request,"title"));
			articleVO.setAuthorId(Integer.valueOf(request.getParameter("authorid").toString()));
			articleVO.setContent(super.trancferChinnese(request,"content"));
			System.out.println("article create.ajax type:"+type);
			// 问答有关键字字段
			if(Constant.ART_EXPERIENCE==type){
				Object keywordObj=request.getParameter("keyword");
				if(null==keywordObj){
					super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
					return;
				}
				articleVO.setKeyword(super.trancferChinnese(request,"keyword"));
			}

			result = articleService.createNewArticle(articleVO);
		}
		if (!result) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
        }else{
			res.setData(Constant.SUCCESS);
			super.flushResponse(response,res);
		}

    }

	/**
	 * 我的(经验\问答)列表
	 * @param request
	 * @param response
	 * @throws IOException
     */
	@RequestMapping(value = "/getMyArticleList.ajax")
	@ResponseBody
	public void getMyArticleList( HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<List<ArticleVO>> res = new Response<List<ArticleVO>>();
		response.setContentType("text/html;charset=utf-8");
		if (null == request.getParameter("startid")||null==request.getParameter("authorid")||null == request.getParameter("type")) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer startid = Integer.valueOf(request.getParameter("startid").toString());
			Integer authorid = Integer.valueOf(request.getParameter("authorid").toString());
			Integer type = Integer.valueOf(request.getParameter("type").toString());
			List<ArticleVO> list = articleService.getArticleListByUserid(authorid,startid,type);
			res.setData(list);
		}
		super.flushResponse(response, res);
	}


}
