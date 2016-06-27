package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.net.Response;
import com.gto.bang.service.QuestionService;
import com.gto.bang.vo.QuestionVO;
import org.apache.commons.lang.StringUtils;
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
public class QuestionController extends BaseController {

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "/qGetList.ajax", method = RequestMethod.GET)
    @ResponseBody
    public void qGetList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<List<QuestionVO>> res = new Response<List<QuestionVO>>();
        response.setContentType("text/html;charset=utf-8");
        Object beginId = request.getParameter("beginId");
        if (null == beginId) {
            res.setStatus(Constant.ERROR_STATUS);
        } else {
            Integer bid = Integer.valueOf(beginId.toString());
            List<QuestionVO> vo = questionService
                    .getExperienceList(bid);
            res.setData(vo);
        }
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/qGetDetail.ajax")
    @ResponseBody
    public void qGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Response<QuestionVO> res = new Response<QuestionVO>();
        response.setContentType("text/html;charset=utf-8");
        Object id=request.getParameter("id");
        if (null == id) {
            res.setStatus(Constant.ERROR_STATUS);
        } else {
            Integer i = Integer.valueOf(id.toString());
            QuestionVO vo = questionService.getExperienceDetail(i);
            res.setData(vo);
        }
        super.flushResponse(response, res);
    }


    @RequestMapping(value = "/qCreate.ajax")
    @ResponseBody
    public void qCreate(@ModelAttribute QuestionVO questionVO,HttpServletRequest request, HttpServletResponse response) throws IOException {


        response.setContentType("text/html;charset=utf-8");
        Response<String> res=new Response<String>();
        boolean result = false;

        String [] params=new String[]{"etitle","euserid","econtent","ekeyword"};
        if(validate(questionVO)) {
            result = questionService.createNewExperience(questionVO);
            res.setData(Constant.SUCCESS);
            if (!result) {
                res.setStatus(Constant.ERROR_STATUS);
                res.setData(Constant.FAILE);
            }
        }
        super.flushResponse(response,res);
    }


    public  boolean validate(QuestionVO questionVO){

        if(StringUtils.isEmpty(questionVO.getqContent())||StringUtils.isEmpty(questionVO.getqTitle())||null==questionVO.getqUserid()){
            return false;
        }
        return true;
    }
}
