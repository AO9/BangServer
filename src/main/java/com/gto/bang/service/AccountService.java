package com.gto.bang.service;

/**
 * Created by shenjialong on 16/6/19.
 */
public interface AccountService {
    boolean validate(String username,String password);

    boolean register(String username,String password,String phone);
}


