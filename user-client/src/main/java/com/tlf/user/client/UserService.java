package com.tlf.user.client;

import com.tlf.user.client.impl.UserClient;
import com.tlf.user.core.bo.req.TokenReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserClient userClient;

    public Boolean veriToken(String authentication) {
        TokenReq tokenReq = new TokenReq();
        tokenReq.setAuthentication(authentication);
        return userClient.veriToken(tokenReq);
    }
}
