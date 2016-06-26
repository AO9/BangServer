package com.gto.bang.vo;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenjialong on 16/6/26.
 */
public class UserVo implements RowMapper<UserVo>,Serializable {

    int id;
    String username;
    String password;
    String phone;
    String email;
    String city;
    String createtime;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public UserVo mapRow(ResultSet rs, int i) throws SQLException {
        UserVo userBo = new UserVo();
        userBo.setId(rs.getInt("id"));
        userBo.setUsername(rs.getString("username"));
        userBo.setCity(rs.getString("city"));
        userBo.setEmail(rs.getString("email"));
        userBo.setPassword(rs.getString("password"));
        userBo.setPhone(rs.getString("phone"));
        return userBo;
    }
}
