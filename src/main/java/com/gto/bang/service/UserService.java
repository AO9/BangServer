package com.gto.bang.service;

import com.gto.bang.model.User;

import java.util.List;

/**
 * 20191208 重构
 */
public interface UserService {
    User queryUser(User condition);
    void register(User user);
    void updateLoginTime(int userId);
    User queryByUserNameAndPassword(String userName, String password);
    User updateUserInfo(int updateType, String content, int userId);
    void update(User user);
    List<User> getUsers(int num);

}


