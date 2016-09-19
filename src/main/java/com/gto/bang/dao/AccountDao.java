package com.gto.bang.dao;

import com.gto.bang.vo.UserVo;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface AccountDao {
    UserVo validate(String username, String password);

    UserVo userInfo(String authorId);

    int insert(String username,String password,String phone,String school,String education);
}
