package com.gto.bang.vo;

import org.apache.commons.net.ntp.TimeStamp;

/**
 * Created by shenjialong on 16/6/23.
 */
public class ExperienceVO {
    int id;
    String eTitle;
    int eUserid;
    String eContent;
    TimeStamp eCreateTime;
    TimeStamp eUpdateTime;
    String eKeyword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String geteTitle() {
        return eTitle;
    }

    public void seteTitle(String eTitle) {
        this.eTitle = eTitle;
    }

    public int geteUserid() {
        return eUserid;
    }

    public void seteUserid(int eUserid) {
        this.eUserid = eUserid;
    }

    public String geteContent() {
        return eContent;
    }

    public void seteContent(String eContent) {
        this.eContent = eContent;
    }

    public TimeStamp geteCreateTime() {
        return eCreateTime;
    }

    public void seteCreateTime(TimeStamp eCreateTime) {
        this.eCreateTime = eCreateTime;
    }

    public TimeStamp geteUpdateTime() {
        return eUpdateTime;
    }

    public void seteUpdateTime(TimeStamp eUpdateTime) {
        this.eUpdateTime = eUpdateTime;
    }

    public String geteKeyword() {
        return eKeyword;
    }

    public void seteKeyword(String eKeyword) {
        this.eKeyword = eKeyword;
    }
}
