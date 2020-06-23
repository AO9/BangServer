package com.gto.bang.service;

/**
 * Created by shenjialong on 20/6/22.
 */
public interface LogService {
    void createLog(Integer userId,String operate,String ext);
}
