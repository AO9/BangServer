package com.gto.bang.service.impl;

import com.gto.bang.service.LogService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 20/6/22.
 */
@Service
public class LogServiceImpl implements LogService {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Override
    public void createLog(Integer userId, String operate, String ext) {

        LOGGER.info("logService|log|create, params: userId={},operate={},ext={}",
                userId, operate, ext);
    }
}
