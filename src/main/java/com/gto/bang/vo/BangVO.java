package com.gto.bang.vo;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenjialong on 16/8/7.
 */
public class BangVO  implements RowMapper<BangVO>, Serializable {

    int id;
    String username;
    String phone;
    String email;
    String school;
    String academy;
    String city;
    String createtime;
    int userid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    @Override
    public BangVO mapRow(ResultSet rs, int i) throws SQLException {
        BangVO vo = new BangVO();
        vo.setId(rs.getInt("id"));
        vo.setUsername(rs.getString("username"));
        vo.setPhone(rs.getString("phone"));
        vo.setEmail(rs.getString("email"));
        vo.setSchool(rs.getString("school"));
        vo.setAcademy(rs.getString("academy"));
        vo.setCreatetime(rs.getTimestamp("createtime").toString());
        vo.setCity(rs.getString("city"));
        vo.setUserid(rs.getInt("userid"));
        return vo;

    }
}
