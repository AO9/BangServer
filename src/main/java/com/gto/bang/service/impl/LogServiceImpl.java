package com.gto.bang.service.impl;

import com.gto.bang.dao.DeviceMapper;
import com.gto.bang.dao.OperationMapper;
import com.gto.bang.model.Device;
import com.gto.bang.model.Operation;
import com.gto.bang.service.LogService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shenjialong on 20/6/22.
 */
@Service
public class LogServiceImpl implements LogService {

    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    OperationMapper operationMapper;

    @Override
    public void createDevice(String androidId, String deviceToken) {

        LOGGER.info("logService|log|createDevice, params: androidId={},deviceToken={}",
                androidId, deviceToken);
        Device device = new Device();
        device.setDeviceId(androidId);
        device.setDeviceToken(deviceToken);
        device.setCreateTime(new Date());
        deviceMapper.insertSelective(device);
    }


    @Override
    public void createLog(Integer userId, String operate, String androidId) {

        LOGGER.info("logService|log|createOperation, params: userId={},operate={},androidId={}",
                userId, operate, androidId);
        Operation operation = new Operation();
        operation.setDeviceId(androidId);
        operation.setOperationType(operate);
        operationMapper.insertSelective(operation);
    }
}
