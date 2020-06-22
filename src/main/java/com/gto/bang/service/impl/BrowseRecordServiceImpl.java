package com.gto.bang.service.impl;


import com.alibaba.fastjson.JSON;
import com.gto.bang.dao.BrowseRecordMapper;
import com.gto.bang.model.BrowseRecord;
import com.gto.bang.service.BrowseRecordService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 20/6/18.
 */
@Service
class BrowseRecrdServiceImpl implements BrowseRecordService {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BrowseRecrdServiceImpl.class);

    @Autowired
    private BrowseRecordMapper browseRecordMapper;

    @Override
    public void addBrowseRecord(BrowseRecord browseRecord) {
        LOGGER.info("BrowseRecrdService | params={}", JSON.toJSONString(browseRecord));
        browseRecordMapper.insertSelective(browseRecord);
    }

    @Override
    public void updateBrowseRecord(BrowseRecord browseRecord) {
        browseRecordMapper.updateByPrimaryKeySelective(browseRecord);
    }

    @Override
    public BrowseRecord queryByUserId(Integer userId) {
        // 容错性,同时兼容老版本APP终端
        if (userId==null){
            return null;
        }
        return browseRecordMapper.selectByUserId(userId);
    }

    @Override
    public void deleteBrowseRecord(Integer userId) {
        browseRecordMapper.deleteByUserId(userId);
    }
}
