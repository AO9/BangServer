package com.gto.bang.dao;

import com.gto.bang.model.Message;

import java.util.List;

public interface MessageMapper {

    List<Message> selectByCondition(Message condition);

    int deleteByPrimaryKey(Integer id);

    int insert(Message record);
    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}