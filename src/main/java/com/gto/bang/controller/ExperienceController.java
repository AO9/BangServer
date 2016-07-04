package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.ExperienceService;
import com.gto.bang.vo.ExperienceVO;
import com.gto.bang.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
@Controller
public class ExperienceController extends BaseController {

    @Autowired
    ExperienceService experienceService;

    @RequestMapping(value = "/eGetList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void eGetList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<List<ExperienceVO>> res = new Response<List<ExperienceVO>>();
        response.setContentType("text/html;charset=utf-8");
		Object startId = request.getParameter("startId");
		if (null ==startId ) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer bid = Integer.valueOf(startId.toString());
			List<ExperienceVO> experienceVO = experienceService
					.getExperienceList(bid);
			res.setData(experienceVO);
		}
		super.flushResponse(response, res);
    }


    @RequestMapping(value = "/eGetDetail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void eGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<ExperienceVO> res = new Response<ExperienceVO>();
        response.setContentType("text/html;charset=utf-8");
        Object id=request.getParameter("id");
		if (null == id) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer i = Integer.valueOf(id.toString());
			ExperienceVO experienceVO = experienceService
					.getExperienceDetail(i);
			res.setData(experienceVO);
        }
		super.flushResponse(response, res);
    }


    @RequestMapping(value = "/eCreate.ajax",method = RequestMethod.GET)
    @ResponseBody
    public void eCreate( HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf8");
		ExperienceVO experienceVO=new ExperienceVO();

		response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
		boolean result = false;

        String [] params=new String[]{"etitle","euserid","econtent","ekeyword"};
        if(super.nonNullValidate(request,params)){

			experienceVO.seteTitle(new String(request.getParameter("etitle").toString().getBytes("iso-8859-1"),"UTF-8"));
			experienceVO.seteUserid(Integer.valueOf(request.getParameter("euserid").toString()));
			experienceVO.seteContent(new String(request.getParameter("econtent").getBytes("iso-8859-1"),"UTF-8"));
			experienceVO.seteKeyword(new String(request.getParameter("ekeyword").getBytes("iso-8859-1"),"UTF-8"));
			result = experienceService.createNewExperience(experienceVO);
			res.setData(Constant.SUCCESS);
		}
		if (!result) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
        }
        super.flushResponse(response,res);
    }

	@RequestMapping(value = "/eGetMyList.ajax")
	@ResponseBody
	public void qGetMyList(@ModelAttribute QuestionVO questionVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Response<List<ExperienceVO>> res = new Response<List<ExperienceVO>>();
		response.setContentType("text/html;charset=utf-8");
		if (null == request.getParameter("startId")||null==request.getParameter("userId")) {
			super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
			return;
		} else {
			Integer startId = Integer.valueOf(request.getParameter("startId").toString());
			Integer userId = Integer.valueOf(request.getParameter("userId").toString());
			List<ExperienceVO> vo = experienceService.getExperienceListByUserid(userId,startId);
			res.setData(vo);
		}
		super.flushResponse(response, res);
	}


}
