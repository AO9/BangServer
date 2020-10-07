package com.gto.bang.common.constant;

/**
 * Created by shenjialong on 16/6/21.
 * 数据字典  常量在这定义
 */
public abstract class Constant {

    public static final String SUCCESS ="success";
    public static final String SERVER_FAIL ="后台服务异常,请稍后重试";
    public static final String REGISTER_SUCCESS ="注册成功";
    public static final String REGISTER_FAILE ="服务异常,请稍后重试";
    public static final String REGISTER_DUL ="注册失败,该昵称已存在!";
    public static final String PARAM_ERROR ="参数异常";
    public static final String TYPE_ERROR ="type参数非法";
    public static final String FORBIDDEN ="帐号异常,已被系统限制发布文章内容,请联系管理员";
    public static final String FORBIDDEN_PERMISSION ="无操作权限，请联系系统管理员";
    public static final String FORBIDDEN_USER ="帐号异常,已被系统限制登录,如有疑问请联系管理员vx sjl132400";
    public static final String CONTENT_ERROR ="内容包含敏感词汇,请重新填写";
    public static final String USERNAME_ERROR ="昵称包含敏感词汇,请重新填写";
    public static final String PARAM_FORMAT_ERROR ="参数格式转换异常";
    public static final String SAVE_ERROR ="保存失败";
    public static final String LOGIN_INFO_ERROR ="账户名或者密码错误";
    public static final String USER_INFO_ERROR ="获取用户信息失败";
    public static final String VALUE_EXCEPTION ="非法信息";

    public static final String SEPRATOR=",";
    public static final int ERROR_STATUS =0;
    public static final int SUCCESS_STATUS =1;
    public static final int READEN_STATUS =1;
    public static final int DELETE_STATUS =999;


    // 评论状态
    public static final int UNREAD=0;
    public static final int READ=1;

    // 文章类别
    // 原创
    public static final int ART_ORIGINAL=10;

    public static final int ART_COMPLAINT=3;
    public static final int ART_QUESTION=2;
    public static final int ART_EXPERIENCE=1;
    public static final int ART_PRODUCT=999;
    // 红包问答
    public static final int ART_SUPPORT=4;
    public static final int ART_ARTICLE=5;

    // 终端类型
    public static final String ANDROID="1";
    public static final String IOS="3";
    public static final String PC="2";

    public static final String EMPYT="";
    public static final int UPDATE_NAME=1001;
    public static final int UPDATE_GENDER=1002;
    public static final int UPDATE_REGION=1003;
    public static final int UPDATE_SIGNATRUE=1004;
    public static final int UPDATE_ACADEMY=1005;

    public static final int BOY=1;
    public static final int GIRL=0;
    public static final String QUATE="'";
    public static final String BOY_FLAG="1";
    public static final String GIRL_FLAG="0";
    public static final String BOY_GENDER="男";
    public static final String GIRL_GENDER="女";


    public static final String TYPE_EXPERIENCE="1";
    public static final String TYPE_QUESTION="2";
    public static final String TYPE_COMPLAINTS="3";



}
