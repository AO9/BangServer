package com.gto.bang.common.constant;

/**
 * Created by shenjialong on 16/6/21.
 * 数据字典  常量在这定义
 */
public abstract class Constant {

    public static final String SUCCESS ="success";
    public static final String REGISTER_SUCCESS ="注册成功";
    public static final String REGISTER_FAILE ="注册失败";
    public static final String PARAM_ERROR ="参数异常";
    public static final String PARAM_FORMAT_ERROR ="参数格式转换异常";
    public static final String SAVE_ERROR ="保存失败";
    public static final String LOGIN_INFO_ERROR ="账户名或者密码错误";

    public static final int ERROR_STATUS =0;
    public static final int SUCCESS_STATUS =1;

    // 评论状态
    public static final int UNREAD=0;
    public static final int READ=1;

    // 文章类别
    public static final int ART_QUESTION=2;
    public static final int ART_EXPERIENCE=1;

    // 终端类型
    public static final String APP="1";
    public static final String PC="2";

}
