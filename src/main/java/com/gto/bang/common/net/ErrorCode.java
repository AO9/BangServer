package com.gto.bang.common.net;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Map;

/**
 * Created by shenjialong on 16/4/25.
 */
public class ErrorCode extends Serial {

    private static final long serialVersionUID = -7157364677352370551L;
    private int code;
    private String message;
    private Map<String, String> detail;

    public String toString()
    {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }
    public Map<String, String> getDetail()
    {
        return detail;
    }

    public void setDetail(Map<String, String> detail)
    {
        this.detail = detail;
    }

    public int getIdx()
    {
        return idx;
    }

    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public ErrorCode(int code, String message, Map<String, String> detail)
    {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public ErrorCode(int code) {
        this(code, "", null);
    }

    public ErrorCode(int code, String message)
    {
        this(code, message, null);
    }

    public ErrorCode()
    {
        this(0, "", null);
    }
}
