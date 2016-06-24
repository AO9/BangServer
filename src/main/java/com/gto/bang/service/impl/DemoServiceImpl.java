package com.gto.bang.service.impl;

import com.gto.bang.dao.DemoDao;
import com.gto.bang.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 16/6/19.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoDao dao;
    @Override
    public String getUserInfo(String id) {

        return dao.getUserInfo(id);
    }
}
