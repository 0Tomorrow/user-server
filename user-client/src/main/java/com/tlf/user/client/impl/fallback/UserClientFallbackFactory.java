package com.tlf.user.client.impl.fallback;

import com.tlf.commonlang.fallback.MyHystrixClientFallbackFactory;
import com.tlf.user.client.impl.UserClient;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallbackFactory extends MyHystrixClientFallbackFactory<UserClient> {

}
