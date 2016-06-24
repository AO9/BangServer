package com.gto.bang.common.net;

import com.gto.bang.common.constant.Constant;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by shenjialong on 16/4/25.
 */
public class Response<E>{

    private int status = Constant.SUCCESS_STATUS;
    private E data;
    private ErrorCode errorCode;

    public Response()
    {}

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public E getData()
    {
        return data;
    }

    public void setData(E data)
    {
        this.data = data;
    }

    public ErrorCode getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode)
    {
        this.errorCode = errorCode;
    }

    public String toString()
    {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).toString();
    }

}
