package com.gto.bang.controller;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/**
 * @todo 定义接口, 服务层代码逻辑未实现
 * Created by shenjialong on 20/6/20.
 */
@Controller
public class LogController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    LogService logService;

    @RequestMapping(value = "/v2/log/create")
    @ResponseBody
    public Map<String, Object> create(Integer userId, String operateType, String androidId, String deviceToken) throws IOException {

        LOGGER.info("pv|log|create ....userId={},type={},androidId={}", userId, operateType, androidId, deviceToken);

        if (deviceToken != null) {
            logService.createDevice(androidId,deviceToken);
        }else {
            logService.createLog(userId,operateType,androidId);
        }
        return supports(Constant.SUCCESS);

    }


}
