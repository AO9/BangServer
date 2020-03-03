package com.gto.bang.service.impl;

import com.alibaba.fastjson.JSON;
import com.gto.bang.dao.PraiseMapper;
import com.gto.bang.model.Praise;
import com.gto.bang.service.PraiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shenjialong on 20/3/3.
 */
public class PraiseServiceImpl implements PraiseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PraiseServiceImpl.class);

    @Autowired
    PraiseMapper praiseMapper;

    @Override
    public void create(Praise vo) {

        LOGGER.info("praise|service|insert, vo={}", JSON.toJSONString(vo));
        praiseMapper.insertSelective(vo);

    }
}
