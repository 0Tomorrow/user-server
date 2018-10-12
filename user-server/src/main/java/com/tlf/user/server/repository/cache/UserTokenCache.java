package com.tlf.user.server.repository.cache;

import com.tlf.commondata.cache.CacheableService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserTokenCache implements CacheableService {

    @Value("${cache.loginCacheTime: 24}")
    Long loginCacheTime;

    @CachePut(value = "UserToken", key = "#key.toString()", condition = "#key != null")
    public String putUserToken(String key, String token) {
        return token;
    }

    @CacheEvict(value = "UserToken", key = "#key.toString()", condition = "#key != null")
    public void deleteUserToken(String key){}

    @Cacheable(value = "UserToken", key = "#key.toString()", condition = "#key != null")
    public String getUserToken(String key) {
        return null;
    }

    @Override
    public Map<String, Long> initCacheExpireTime() {
        Map<String, Long> map = new HashMap<>();
        map.put("UserToken", loginCacheTime * HOUR);
        return map;
    }

    @Override
    public int cacheDbIndex() {
        return 0;
    }
}
