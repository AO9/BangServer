package com.gto.bang.service.impl;

import com.alibaba.fastjson.JSON;
import com.gto.bang.common.constant.Constant;
import com.gto.bang.dao.UserMapper;
import com.gto.bang.model.User;
import com.gto.bang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 20191208 重构
 */
@Service
public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getNewUsers(int num) {
        return userMapper.getNewUsers(num);
    }

    @Override
    public List<User> getUsers(int num) {
        return userMapper.getUsers(num);
    }

    @Override
    public User queryByUserNameAndPassword(String userName, String password) {
        User condition = new User();
        condition.setUserName(userName);
        condition.setPassword(password);
        return queryUser(condition);
    }

    @Override
    public User queryUser(User condition) {
        User user = userMapper.selectByCondition(condition);
        return user;
    }

    @Override
    public void updateLoginTime(int userId) {
        LOGGER.info("register|service|updateLoginTime begin .... userId={}", userId);
        User userInfo = new User();
        userInfo.setId(userId);
        userInfo.setLastLoginTime(new Date());
        update(userInfo);
        LOGGER.info("register|service|updateLoginTime end");

    }

    @Override
    public void register(User user) {
        LOGGER.info("user|service|register, begin .... userVo={}", JSON.toJSONString(user));
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        int result = userMapper.insertSelective(user);
        LOGGER.info("user|service|register,  end result={},userVo={}", result, JSON.toJSONString(user));
    }

    @Override
    public void update(User user) {
        LOGGER.info("user|service|update begin .... userInfo={}", JSON.toJSONString(user));
        int result = userMapper.updateByPrimaryKeySelective(user);
        LOGGER.info("user|service|update end result={}", result);
    }

    @Override
    public User updateUserInfo(int updateType, String content, int userId) {
        User userInfo = new User();
        userInfo.setId(userId);
        switch (updateType) {
            case Constant.UPDATE_GENDER:
                userInfo.setGender(Byte.valueOf(content));
                break;
            case Constant.UPDATE_REGION:
                userInfo.setCity(content);
                break;
            case Constant.UPDATE_NAME:
                userInfo.setUserName(content);
                break;
            case Constant.UPDATE_SIGNATRUE:
                userInfo.setSignature(content);
                break;
            case Constant.UPDATE_ACADEMY:
                userInfo.setAcademy(content);
                break;
            default:
                break;
        }
        update(userInfo);
        return userInfo;
    }


}
