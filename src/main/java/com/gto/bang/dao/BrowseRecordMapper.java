package com.gto.bang.dao;

import com.gto.bang.model.BrowseRecord;

public interface BrowseRecordMapper {

    BrowseRecord selectByUserId(Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(BrowseRecord record);

    int insertSelective(BrowseRecord record);

    BrowseRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BrowseRecord record);

    int updateByPrimaryKeyWithBLOBs(BrowseRecord record);

    int updateByPrimaryKey(BrowseRecord record);
}