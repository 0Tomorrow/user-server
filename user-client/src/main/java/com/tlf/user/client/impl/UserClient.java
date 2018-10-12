package com.tlf.user.client.impl;

import com.tlf.user.client.impl.fallback.UserClientFallbackFactory;
import com.tlf.user.core.bo.req.TokenReq;
import com.tlf.user.core.config.RemoteConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = RemoteConfig.SERVICE_NAME, fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @RequestMapping(value = "/user/veri/token")
    Boolean veriToken(@RequestBody TokenReq tokenReq);
}
