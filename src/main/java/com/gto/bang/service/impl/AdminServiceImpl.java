package com.gto.bang.service.impl;

import com.gto.bang.dao.AdminDao;
import com.gto.bang.service.AdminService;
import com.gto.bang.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shenjialong
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao dao;

	@Override
	public List<UserVo> getUsers(String num) {
		return dao.getUsers(num);
	}
}
