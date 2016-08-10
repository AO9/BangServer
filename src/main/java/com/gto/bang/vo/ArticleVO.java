package com.gto.bang.vo;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 文章VO类,整合经验和问答到一个表中
 * Created by shenjialong on 16/7/03.
 */

public class ArticleVO implements RowMapper<ArticleVO>, Serializable {
    int id;
    int type;
    String title;
    int authorId;
    String content;
    String createTime;
    String updateTime;
    String keyword;
    String username;

    public ArticleVO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public ArticleVO mapRow(ResultSet rs, int i) throws SQLException {
        ArticleVO vo = new ArticleVO();
        vo.setId(rs.getInt("id"));
        vo.setAuthorId(rs.getInt("authorid"));
        vo.setTitle(rs.getString("title"));
        vo.setType(rs.getInt("type"));
        vo.setKeyword(rs.getString("keyword"));
        vo.setContent(rs.getString("content"));
        vo.setCreateTime(rs.getTimestamp("createtime").toString());
        vo.setUpdateTime(rs.getTimestamp("updatetime").toString());
        return vo;
    }
}
