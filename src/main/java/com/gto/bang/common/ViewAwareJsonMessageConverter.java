package com.gto.bang.common;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gto.bang.common.constant.Constant;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ViewAwareJsonMessageConverter
 */
public class ViewAwareJsonMessageConverter extends FastJsonHttpMessageConverter {

	@Override
	 protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
	                                                                             HttpMessageNotWritableException {
			if(object instanceof Map) {
				@SuppressWarnings("unchecked")
				Map<String,Object> map = (Map<String,Object>) object;
				Map<String,Object> statusInfo = new HashMap<String, Object>();
				Object status = map.get("status");
				if(status != null) {
					int code = Integer.parseInt(String.valueOf(status));
					if(Constant.SUCCESS_STATUS != code) {
						super.writeInternal(object, outputMessage);
					} else {
						statusInfo.put("global", "OK");
						map.put("statusInfo", statusInfo);
						super.writeInternal(object, outputMessage);
					}
				}
			} else {
				super.writeInternal(object, outputMessage);
			}
	    }
}
