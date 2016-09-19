package com.gto.bang.service;

import com.gto.bang.vo.UserVo;

import java.util.List;

/**
 * Created by shenjialong on 16/7/03.
 */
public interface AdminService {
    List<UserVo> getUsers(String num);
}


