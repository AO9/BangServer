package com.gto.bang.dao.impl;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.dao.CommentDao;
import com.gto.bang.vo.CommentVO;
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
 * Created by shenjialong on 16/7/1.
 */
@Repository
public class CommentDAOImpl implements CommentDao {
    public  static Logger LOGGER = LoggerFactory.getLogger(QuestionDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createNewComment(CommentVO vo) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        LOGGER.info("[Comment][createNewComment] 创建新评论:{},username:{},content:{}",
                vo.getUsername(),vo.getContent());

        String sql = "insert into comment (userid,username,artid,content,type,createtime,status,arttitle) values (?,?,?,?,?,?,?,?)";
        int num = jdbcTemplate.update(
                sql,
                new Object[]{vo.getUserId(),vo.getUsername(),vo.getActId(),vo.getContent(),vo.getType(), df.format(new Date()), Constant.UNREAD,vo.getArtTitle()
                }, new int[]{Types.INTEGER,
                        Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER,Types.DATE,Types.INTEGER, Types.VARCHAR});
        return num >= 1;
    }

    @Override
    public List<CommentVO> getCommentList(Integer type, Integer artId, Integer startId) {

            List<CommentVO> list=new ArrayList<CommentVO>();
            LOGGER.info("[Comment][getCommentList] by startId:{}", startId);
            StringBuilder sb =new StringBuilder("select * from comment");
            Object [] params=new Object[]{type,artId,startId};
            sb.append(" where type=? and artid=? and id>=?");
            sb.append(" order by createtime desc limit 20");

            List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
            for(int i=0;i<maps.size();i++){
                Map<String,Object> map=maps.get(i);
                CommentVO vo  = new CommentVO();
                vo.setActId(Integer.valueOf(map.get("artid").toString()));
                vo.setCreatetime(map.get("createtime").toString());
                vo.setUsername(map.get("username").toString());
                vo.setUserId(Integer.valueOf(map.get("userid").toString()));
                vo.setContent(map.get("content").toString());
                vo.setId(Integer.valueOf(map.get("id").toString()));
                vo.setType(Integer.valueOf(map.get("type").toString()));
                list.add(vo);
            }

            return list;


    }

    /**
     * 获取未读评论列表()
     * @param artIds type
     * @return
     */
    @Override
    public List<CommentVO> getUnReadCommentListByArtIds(List<Integer> artIds,int type) {

        List<CommentVO> list=new ArrayList<CommentVO>();
        LOGGER.info("[Comment][getUnReadCommentListByArtIds] by artIds:{}", artIds.toString());
        StringBuilder sb =new StringBuilder("select * from comment");

        if(null==artIds|| artIds.size()==0){
            return null;
        }

        StringBuilder artIdsStr =new StringBuilder("");

        for(int i=0;i<artIds.size();i++){
            if(i==0){
                artIdsStr.append(artIds.get(i));
            }else{
                artIdsStr.append(",").append(artIds.get(i));
            }
        }

        Object [] params=new Object[]{artIdsStr.toString(),type};
        sb.append(" where artid in (?) and status=0 and type=?");
        // 暂时限制最多返回20条 未读的消息
        sb.append(" order by createtime desc limit 20");

        List<Map<String,Object>> maps= jdbcTemplate.queryForList(sb.toString(),params);
        for(int i=0;i<maps.size();i++){
            Map<String,Object> map=maps.get(i);
            CommentVO vo  = new CommentVO();
            vo.setActId(Integer.valueOf(map.get("artid").toString()));
            vo.setCreatetime(map.get("createtime").toString());
            vo.setUsername(map.get("username").toString());
            vo.setUserId(Integer.valueOf(map.get("userid").toString()));
            vo.setContent(map.get("content").toString());
            vo.setId(Integer.valueOf(map.get("id").toString()));
            vo.setType(Integer.valueOf(map.get("type").toString()));
            vo.setType(Integer.valueOf(map.get("status").toString()));
            vo.setArtTitle(map.get("arttitle").toString());
            list.add(vo);
        }

        return list;


    }


}
