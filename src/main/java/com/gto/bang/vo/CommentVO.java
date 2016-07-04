package com.gto.bang.vo;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 评论VO类
 * Created by shenjialong on 16/7/1.
 */
public class CommentVO  implements RowMapper<CommentVO>, Serializable {

    int id;
    String username;
    int userId;
    int type;
    int actId;
    String content;
    String createtime;
    int statuts;
    String artTitle;

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

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public int getType() {

        return type;
    }

    public void setType(int type) {

        this.type = type;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public int getActId() {

        return actId;
    }

    public void setActId(int actId) {

        this.actId = actId;
    }

    public String getCreatetime() {

        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getStatuts() {
        return statuts;
    }

    public void setStatuts(int statuts) {
        this.statuts = statuts;
    }

    public String getArtTitle() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }

    @Override
    public CommentVO mapRow(ResultSet rs, int i) throws SQLException {
        CommentVO vo = new CommentVO();
        vo.setId(rs.getInt("id"));
        vo.setContent(rs.getString("content"));
        vo.setUserId(rs.getInt("userid"));
        vo.setUsername(rs.getString("username"));
        vo.setCreatetime(rs.getTimestamp("c").toString());
        vo.setType(rs.getInt("type"));
        vo.setActId(rs.getInt("actId"));
        return vo;
    }
}
