package com.gto.bang.dao;

import com.gto.bang.vo.UserVo;

import java.util.List;

/**
 * Created by shenjialong on 16/6/21.
 */
public interface AdminDao {

    List<UserVo> getUsers(String num);
}
