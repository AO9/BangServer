package com.gto.bang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.ExperienceService;
import com.gto.bang.vo.ExperienceVO;

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
		Object beginId = request.getParameter("beginId");
		if (null == beginId) {
			res.setStatus(Constant.ERROR_STATUS);
		} else {
			Integer bid = Integer.valueOf(beginId.toString());
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
			res.setStatus(Constant.ERROR_STATUS);
		} else {
			Integer i = Integer.valueOf(id.toString());
			ExperienceVO experienceVO = experienceService
					.getExperienceDetail(i);
			res.setData(experienceVO);
        }
		super.flushResponse(response, res);
    }


    @RequestMapping(value = "/eCreate.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void eCreate(ExperienceVO experienceVO,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
		boolean result = false;
        String [] params=new String[]{"etitle","euserid","econtent","ekeyword"};
        if(super.nonNullValidate(request,params)){
			result = experienceService.createNewExperience(experienceVO);
			res.setData(Constant.SUCCESS);
		}
		if (!result) {
			res.setStatus(Constant.ERROR_STATUS);
			res.setData(Constant.FAILE);
        }
        super.flushResponse(response,res);
    }


}
