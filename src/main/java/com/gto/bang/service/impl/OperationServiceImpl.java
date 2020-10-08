package com.gto.bang.service.impl;

import com.gto.bang.dao.OperationMapper;
import com.gto.bang.model.Operation;
import com.gto.bang.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    OperationMapper operationMapper;
    @Override
    public List<Operation> getStudents() {
        return operationMapper.selectStutdents();
    }
}
