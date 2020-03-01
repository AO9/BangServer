package com.gto.bang.vo;

import com.gto.bang.common.string.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 20170226
 */
public class ProductVO implements RowMapper<ProductVO>, Serializable {

    int id;
    String title;
    int authorId;
    String username;
    int price;
    String content;
    String createTime;
    String updateTime;
    String phone;
    String wechat;
    int viewtimes;
    int praise;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    String level;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getViewtimes() {
        return viewtimes;
    }
    public void setViewtimes(int viewtimes) {
        this.viewtimes = viewtimes;
    }
    public ProductVO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public ProductVO mapRow(ResultSet rs, int i) throws SQLException {
        ProductVO vo = new ProductVO();
        vo.setId(rs.getInt("id"));
        vo.setAuthorId(rs.getInt("authorid"));
        vo.setPrice(rs.getInt("price"));
        vo.setTitle(rs.getString("title"));
        vo.setContent(rs.getString("content"));
        String createtime=rs.getTimestamp("createtime").toString();
        createtime= StringUtil.formatToDateTime(createtime);
        vo.setCreateTime(createtime);
        vo.setUpdateTime(rs.getTimestamp("updatetime").toString());
        return vo;
    }
}
