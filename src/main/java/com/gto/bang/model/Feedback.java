package com.gto.bang.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Feedback {
    private Integer id;

    private Integer userid;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    private String content;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}