package com.gto.bang.service.impl;

import com.gto.bang.dao.AccountDao;
import com.gto.bang.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shenjialong on 16/6/19.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public boolean validate(String username, String password) {
        int num=accountDao.validate(username,password);
        boolean result=num>0?true:false;
        return result;
    }

    @Override
    public boolean register(String username, String password, String phone) {
        int num=accountDao.insert(username,password,phone);
        return num==1?true:false;
    }
}
