package com.gto.bang.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.gto.bang.common.ViewAwareJsonMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanglei on 2017/8/18.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        ViewAwareJsonMessageConverter fastConverter = new ViewAwareJsonMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
        		 SerializerFeature.QuoteFieldNames, SerializerFeature.DisableCircularReferenceDetect);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastConverter);
    }
}
