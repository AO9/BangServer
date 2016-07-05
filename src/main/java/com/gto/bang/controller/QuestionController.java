package com.gto.bang.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by shenjialong on 16/6/21.
 */
@Controller
public class QuestionController extends BaseController {
//
//    @Autowired
//    QuestionService questionService;
//    /**
//     * 前端首次请求时startId为0即可
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/qGetList.ajax", method = RequestMethod.GET)
//    @ResponseBody
//    public void qGetList(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Response<List<QuestionVO>> res = new Response<List<QuestionVO>>();
//        response.setContentType("text/html;charset=utf-8");
//        Object startId = request.getParameter("startId");
//        if (null == startId) {
//            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
//            return;
//        } else {
//            Integer sid = Integer.valueOf(startId.toString());
//            List<QuestionVO> vo = questionService
//                    .getQuestionList(sid);
//            res.setData(vo);
//        }
//        super.flushResponse(response, res);
//    }
//
//
//    @RequestMapping(value = "/qGetDetail.ajax")
//    @ResponseBody
//    public void qGetDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Response<QuestionVO> res = new Response<QuestionVO>();
//        response.setContentType("text/html;charset=utf-8");
//        Object id=request.getParameter("id");
//        if (null == id) {
//            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
//            return;
//        } else {
//            Integer i = Integer.valueOf(id.toString());
//            QuestionVO vo = questionService.getQuestionDetail(i);
//            res.setData(vo);
//        }
//        super.flushResponse(response, res);
//    }
//
//
//    @RequestMapping(value = "/qCreate.ajax")
//    @ResponseBody
//    public void qCreate(@ModelAttribute QuestionVO questionVO,HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        request.setCharacterEncoding("utf8");
//        response.setContentType("text/html;charset=utf-8");
//        Response<String> res=new Response<String>();
//        boolean result = false;
//
//        String [] params=new String[]{"title","userid","content"};
//        if(super.nonNullValidate(request,params))    {
//            questionVO.setqUserid(Integer.valueOf(request.getParameter("userid")));
//            questionVO.setqTitle(new String(request.getParameter("title").getBytes("iso-8859-1"),"UTF-8"));
//            questionVO.setqContent(new String(request.getParameter("content").getBytes("iso-8859-1"),"UTF-8"));
//            System.out.println("11"+new String(request.getParameter("title").toString().getBytes("iso-8859-1"),"UTF-8"));
//
//            result = questionService.createNewQuestion(questionVO);
//            res.setData(Constant.SUCCESS);
//
//        }
//        if (!result) {
//            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
//            return;
//        }
//        super.flushResponse(response,res);
//    }
//
//
//    /**
//     * 获取我的问答列表,startId不能为空,首屏时前端传该值为0
//     * @param questionVO
//     * @param request
//     * @param response
//     * @throws IOException
//     */
//    @RequestMapping(value = "/qGetMyList.ajax")
//    @ResponseBody
//    public void qGetMyList(@ModelAttribute QuestionVO questionVO,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Response<List<QuestionVO>> res = new Response<List<QuestionVO>>();
//        response.setContentType("text/html;charset=utf-8");
//        if (null == request.getParameter("startId")||null==request.getParameter("userId")) {
//            super.flushResponse4Error(response,res,Constant.PARAM_ERROR);
//            return;
//        } else {
//            Integer startId = Integer.valueOf(request.getParameter("startId").toString());
//            Integer userId = Integer.valueOf(request.getParameter("userId").toString());
//            List<QuestionVO> vo = questionService
//                    .getQuestionListByUserid(userId,startId);
//            res.setData(vo);
//        }
//        super.flushResponse(response, res);
//    }
//
//
//    public  boolean validate(QuestionVO questionVO){
//
//        return !(StringUtils.isEmpty(questionVO.getqContent()) ||
//                StringUtils.isEmpty(questionVO.getqTitle()) || questionVO.getqUserid()==0);
//    }
}
