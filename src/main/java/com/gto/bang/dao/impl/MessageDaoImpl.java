package com.gto.bang.dao.impl;

import com.gto.bang.common.string.StringUtil;
import com.gto.bang.dao.MessageDao;
import com.gto.bang.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shenjialong on 16/6/21.
 */
@Repository
public class MessageDaoImpl implements MessageDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(MessageDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean createNewMessage(MessageVO messageVO) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        // new Date()为获取当前系统时间

        String sql = "insert into message (userid,msginfo,createtime) values (?,?,?)";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{messageVO.getUserId(),messageVO.getMsgInfo(),df.format(new Date())}, new int[]{Types.INTEGER,
                        Types.VARCHAR, Types.DATE});
        return num >= 1;
    }


    /**
     * 批量标志为已读
     * @param messageIds
     * @return
     */
    @Override
    public Boolean updateStatus(String messageIds) {

        String sql = "update message set status=1 where id in ("+messageIds+")";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{}, new int[]{});

        System.out.println("设置已读状态 返回值 num:"+num);
        return num >= 1;
    }

    @Override
    public List<MessageVO> getSystemMessage(int userId, int startId) {

        List<MessageVO> list=new ArrayList<MessageVO>();
        LOGGER.info("[Message][getSystemMessage] by userid:{} startId:{}", userId,startId);
        StringBuilder sb =new StringBuilder("select * from message");
        Object [] params=new Object[]{userId,startId};
        sb.append(" where userid=? and id>? ");
        sb.append(" order by createtime desc limit 20");
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        list=transfer(maps);

        return list;
    }

    @Override
    public List<MessageVO> getMessageList(int userId, int status) {
        List<MessageVO> list=new ArrayList<MessageVO>();
        LOGGER.info("[Message][getMessageList] by userid:{} status:{}", userId,status);
        StringBuilder sb =new StringBuilder("select * from message");
        Object [] params=new Object[]{userId,status};
        sb.append(" where userid=? and status=? ");
        sb.append(" order by createtime desc");
        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        list=transfer(maps);
        return list;
    }


    /**
     * 转换成vo list
     * @param maps
     * @return
     */
    public  List<MessageVO> transfer(List<Map<String,Object>> maps){
        List<MessageVO> list=new ArrayList<MessageVO>();
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            MessageVO vo  = new MessageVO();
            String createtime=map.get("createtime").toString();
            createtime= StringUtil.formatToDateTime(createtime);
            vo.setCreatetime(createtime);
            vo.setStatus(Integer.valueOf(map.get("status").toString()));
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setUserId(Integer.valueOf(map.get("userid").toString()));
            vo.setMsgInfo(map.get("msginfo").toString());
            list.add(vo);
        }
        return list;
    }
}
