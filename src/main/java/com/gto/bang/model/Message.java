package com.gto.bang.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer userid;

    private String msginfo;

    private String msgInfo;

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getMsginfo() {
        return msginfo;
    }

    public void setMsginfo(String msginfo) {
        this.msginfo = msginfo == null ? null : msginfo.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}