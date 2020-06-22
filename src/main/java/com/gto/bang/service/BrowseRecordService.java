package com.gto.bang.service;

import com.gto.bang.model.BrowseRecord;

/**
 * 20200618
 */
public interface BrowseRecordService {

    void addBrowseRecord(BrowseRecord browseRecord);

    void updateBrowseRecord(BrowseRecord browseRecord);

    BrowseRecord queryByUserId(Integer userId);

    void deleteBrowseRecord(Integer userId);

}


