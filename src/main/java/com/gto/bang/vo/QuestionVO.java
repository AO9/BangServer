package com.gto.bang.vo;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenjialong on 16/6/28.
 */
public class QuestionVO implements RowMapper<QuestionVO>, Serializable {
    int id;
    String qTitle;
    Integer qUserid;
    String qContent;
    String qCreateTime;
    TimeStamp qUpdateTime;
    String qAnwserNum;

    public QuestionVO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public Integer getqUserid() {
        return qUserid;
    }

    public void setqUserid(Integer qUserid) {
        this.qUserid = qUserid;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqCreateTime() {
        return qCreateTime;
    }

    public void setqCreateTime(String qCreateTime) {
        this.qCreateTime = qCreateTime;
    }

    public TimeStamp getqUpdateTime() {
        return qUpdateTime;
    }

    public void setqUpdateTime(TimeStamp qUpdateTime) {
        this.qUpdateTime = qUpdateTime;
    }

    public String getqAnwserNum() {
        return qAnwserNum;
    }

    public void setqAnwserNum(String qAnwserNum) {
        this.qAnwserNum = qAnwserNum;
    }





    @Override
    public QuestionVO mapRow(ResultSet rs, int i) throws SQLException {
        QuestionVO vo  = new QuestionVO();
        vo.setId(rs.getInt("id"));
        vo.setqAnwserNum(rs.getString("qanwsernum"));
        vo.setqTitle(rs.getString("qtitle"));
        vo.setqContent(rs.getString("qcontent"));
        vo.setqCreateTime(rs.getTimestamp("qcreatetime").toString());
        return vo;
    }
}
