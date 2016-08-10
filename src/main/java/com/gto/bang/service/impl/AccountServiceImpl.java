package com.gto.bang.service.impl;

import com.gto.bang.dao.AccountDao;
import com.gto.bang.service.AccountService;
import com.gto.bang.vo.UserVo;
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
    public UserVo validate(String username, String password) {
        UserVo userBo=accountDao.validate(username,password);
        return userBo;
    }

    @Override
    public int register(String username, String password, String phone) {
        int num=accountDao.insert(username,password,phone);
        return num;
    }
}
