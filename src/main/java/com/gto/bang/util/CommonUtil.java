package com.gto.bang.util;

import org.apache.commons.beanutils.ConvertUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shenjialong on 17/3/27.
 */
public class CommonUtil {

    public static final String KEYWORD = "**";

    public static String getLevel(int grade) {

        if (grade <= 25) {
            return "论文新人";
        }
        if (grade <= 75) {
            return "论文达人";
        }
        if (grade <= 150) {
            return "论文长老";
        }
        if (grade <= 300) {
            return "论文副首领";
        }

        if (grade <= 500) {
            return "论文首领";
        }

        return "论文新人";

    }


    public static List<Integer> convert(String param) {
        String[] strArr = param.split(",");
        List<String> list = Arrays.asList(strArr);
        Integer[] intArr = (Integer[]) ConvertUtils.convert(list, Integer.class);
        List<Integer> result = Arrays.asList(intArr);
        return result;
    }

    /**
     *
     * @param content
     * @return
     */
    public static boolean checkContent(String content){
        boolean result= Pattern.compile("daixie").matcher(content).find();
        result = result || Pattern.compile("代写").matcher(content).find();
        result = result || Pattern.compile("dai写").matcher(content).find();
        result = result || Pattern.compile("代xie").matcher(content).find();
        result = result || Pattern.compile("代发").matcher(content).find();
        result = result || Pattern.compile("daifa").matcher(content).find();
        result = result || Pattern.compile("代fa").matcher(content).find();
        result = result || Pattern.compile("dai发").matcher(content).find();
        result = result || Pattern.compile("价格").matcher(content).find();
        result = result || Pattern.compile("发表").matcher(content).find();
        result = result || Pattern.compile("枪手").matcher(content).find();
        result = result || Pattern.compile("qiangshou").matcher(content).find();
        result = result || Pattern.compile("论文帮").matcher(content).find();
        result = result || Pattern.compile("论*文帮").matcher(content).find();
        result = result || Pattern.compile("论文*帮").matcher(content).find();
        return result;
    }


//    public static String replaceContent(String content){
//        content=content.replace("代笔",KEYWORD);
//        content=content.replace("代写",KEYWORD);
//        content=content.replace("论文代写",KEYWORD);
////        content=content.replace("论文帮",KEYWORD);
//        content=content.replace("论文代笔",KEYWORD);
//        content=content.replace("daixie",KEYWORD);
//        content=content.replace("代xie",KEYWORD);
//        content=content.replace("dai写",KEYWORD);
//        content=content.replace("代发",KEYWORD);
//        content=content.replace("代*写",KEYWORD);
//        return content;
//    }

//    public static void replaceContentForVO(List<ArticleVO> list){
//
//        for (int i=0;i<list.size();i++){
//            ArticleVO vo=list.get(i);
//            if (StringUtils.isNotBlank(vo.getContent())){
//                vo.setContent(CommonUtil.replaceContent(vo.getContent()));
//            }
//            if (StringUtils.isNotBlank(vo.getTitle())){
//                vo.setTitle(CommonUtil.replaceContent(vo.getTitle()));
//            }
//            if (StringUtils.isNotBlank(vo.getUsername())){
//                vo.setUsername(CommonUtil.replaceContent(vo.getUsername()));
//            }
//        }
//
//    }
//
//    public static void replaceContentForComment(List<CommentVO> list){
//
//        for (int i=0;i<list.size();i++){
//            CommentVO vo=list.get(i);
//            if (StringUtils.isNotBlank(vo.getContent())){
//                vo.setContent(CommonUtil.replaceContent(vo.getContent()));
//            }
//            if (StringUtils.isNotBlank(vo.getArtTitle())){
//                vo.setArtTitle(CommonUtil.replaceContent(vo.getArtTitle()));
//            }
//            if (StringUtils.isNotBlank(vo.getUsername())){
//                vo.setUsername(CommonUtil.replaceContent(vo.getUsername()));
//            }
//        }
//
//    }


}
