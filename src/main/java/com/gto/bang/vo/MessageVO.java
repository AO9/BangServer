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
public class MessageVO implements RowMapper<MessageVO>, Serializable {

    int id;
    int userId;
    // 1:经验  2:问答 3:系统公告
    int msgType;
    int artId;
    String msgInfo;
    String createtime;
    // 0:未读  1:已读
    int status;

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

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }


    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public MessageVO mapRow(ResultSet rs, int i) throws SQLException {
        MessageVO vo = new MessageVO();
        vo.setUserId(rs.getInt("userid"));
        vo.setArtId(rs.getInt("artid"));
        vo.setMsgType(rs.getInt("msgtype"));
        String createtime=rs.getTimestamp("createtime").toString();
        createtime= StringUtil.formatToDateTime(createtime);
        vo.setCreatetime(createtime);
        vo.setMsgInfo(rs.getString("msginfo"));
        vo.setId(rs.getInt("id"));
        return vo;
    }
}
