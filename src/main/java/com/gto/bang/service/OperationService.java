package com.gto.bang.service;

import com.gto.bang.model.Operation;

import java.util.List;

/**
 * Created by shenjialong on 20201008
 */
public interface OperationService {
    List<Operation> getPV(String operationType);
    List<Operation> getStudents();
}
