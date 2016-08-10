package com.gto.bang.service;

import com.gto.bang.vo.UserVo;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface AccountService {
    UserVo validate(String username, String password);

    int register(String username,String password,String phone);
}


