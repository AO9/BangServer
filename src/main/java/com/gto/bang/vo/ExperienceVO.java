package com.gto.bang.vo;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenjialong on 16/6/23.
 */
public class ExperienceVO implements RowMapper<ExperienceVO>, Serializable {
    int id;
    String eTitle;
    int eUserid;
    String eContent;
    String eCreateTime;
    TimeStamp eUpdateTime;
    String eKeyword;

    public ExperienceVO(){}

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

    public String geteCreateTime() {
        return eCreateTime;
    }

    public void seteCreateTime(String eCreateTime) {
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

    @Override
    public ExperienceVO mapRow(ResultSet rs, int i) throws SQLException {
        ExperienceVO evo  = new ExperienceVO();
        evo.setId(rs.getInt("id"));
        evo.seteKeyword(rs.getString("ekeyword"));
        evo.seteTitle(rs.getString("etitle"));
        evo.seteContent(rs.getString("econtent"));
        evo.seteCreateTime(rs.getTimestamp("ecreatetime").toString());
        return evo;
    }
}
