package com.gto.bang.vo;

import com.gto.bang.common.string.StringUtil;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 消息VO类
 * Created by shenjialong on 16/7/3.
 */
public class FeedbackVO implements RowMapper<FeedbackVO>, Serializable {

    int id;
    int userId;
    String content;
    String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public FeedbackVO mapRow(ResultSet rs, int i) throws SQLException {
        FeedbackVO vo = new FeedbackVO();
        vo.setUserId(rs.getInt("userid"));
        vo.setId(rs.getInt("id"));
        vo.setContent(rs.getString("content"));
        String createtime=rs.getTimestamp("createtime").toString();
        createtime= StringUtil.formatToDateTime(createtime);
        vo.setCreatetime(createtime);
        return vo;
    }
}
