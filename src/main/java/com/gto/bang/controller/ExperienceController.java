package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.ExperienceService;
import com.gto.bang.vo.ExperienceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        String res=null;
        response.setContentType("text/html;charset=utf-8");

        request.getParameter("id");

        PrintWriter writer = response.getWriter();
        writer.println(res);
        writer.flush();
        writer.close();
    }


    @RequestMapping(value = "/eGetDetail.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void eGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String res=null;
        response.setContentType("text/html;charset=utf-8");

        request.getParameter("id");

        PrintWriter writer = response.getWriter();
        writer.println(res);
        writer.flush();
        writer.close();
    }


    @RequestMapping(value = "/eCreate.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void eCreate(ExperienceVO experienceVO,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();

        String [] params=new String[]{"etitle","euserid","econtent","ekeyword"};
        if(super.nonNullValidate(request,params)){

            boolean result= experienceService.createNewExperience(experienceVO);
            if (result){
                res.setData(Constant.SUCCESS);
            }else {
                res.setStatus(Constant.ERROR_STATUS);
                res.setData(Constant.FAILE);
            }
        }else{
            res.setStatus(Constant.ERROR_STATUS);
            res.setData(Constant.FAILE);
        }
        super.flushResponse(response,res);
    }


}
