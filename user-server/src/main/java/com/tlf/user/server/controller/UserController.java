package com.tlf.user.server.controller;

import com.tlf.commonlang.bo.RespBase;
import com.tlf.user.core.bo.req.TokenReq;
import com.tlf.user.core.bo.req.UserReq;
import com.tlf.user.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespBase<String> login(@RequestBody UserReq userReq) {
        String token = userService.login(userReq);
        return new RespBase<>(token);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RespBase register(@RequestBody UserReq userReq) {
        userService.register(userReq);
        return RespBase.OK_RESP_BASE;
    }

    @RequestMapping(value = "/veri/token", method = RequestMethod.POST)
    public RespBase<Boolean> veriToken(@RequestBody TokenReq tokenReq) {
        String authentication = tokenReq.getAuthentication();
        Boolean result = userService.veriToken(authentication);
        return new RespBase<>(result);
    }

}
