package com.gto.bang.dao;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface AccountDao {
    int validate(String username,String password);

    int insert(String username,String password,String phone);
}
