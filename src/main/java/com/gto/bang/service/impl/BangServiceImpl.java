package com.gto.bang.service.impl;

import com.gto.bang.dao.BangDao;
import com.gto.bang.service.BangService;
import com.gto.bang.vo.BangVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 20160807 帮帮功能实现类
 */
@Service
public class BangServiceImpl implements BangService {
    @Autowired
    private BangDao dao;

	@Override
	public Boolean createNewOrder(BangVO vo) {

		return dao.createNewOrder(vo);
	}
}
