package com.tlf.user.server.service;

import com.tlf.cloud.storage.client.CloudStorageService;
import com.tlf.commonlang.exception.MyException;
import com.tlf.commonlang.util.StringEncoder;
import com.tlf.user.core.bo.req.UserReq;
import com.tlf.user.server.entity.TUserInfo;
import com.tlf.user.server.repository.UserRepo;
import com.tlf.user.server.repository.cache.UserTokenCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    CloudStorageService cloudStorageService;

    @Autowired
    UserTokenCache userTokenCache;

    public Boolean veriToken(String authentication) {
        if (null == authentication) {
            throw new MyException("authentication为空");
        }
        String encode = new String(Base64.getDecoder().decode(authentication));
        String[] params = encode.split(":");
        if (params.length < 2) {
            throw new MyException("token格式错误");
        }
        String account = params[0];
        String token = params[1];

        String tokenVerify = userTokenCache.getUserToken(account);
        if (!token.equals(tokenVerify)) {
            throw new MyException("登录token错误");
        }
        return true;
    }

    public String login(UserReq userReq) {
        if (!userReq.verify()) {
            throw new MyException("参数格式不对");
        }
        Long account = Long.parseLong(userReq.getAccount());
        String password = userReq.getPassword();

        TUserInfo tUserInfo = userRepo.findFirstByAccountAndPassword(account, password);
        if (tUserInfo == null) {
            throw new MyException("用户名或密码错误");
        }
        return getToken(tUserInfo);
    }

    private String getToken(TUserInfo tUserInfo) {
        String s = tUserInfo.getId() + ":" + System.currentTimeMillis();
        String token = StringEncoder.encodeByMD5(s);
        userTokenCache.deleteUserToken(tUserInfo.getId().toString());
        userTokenCache.putUserToken(tUserInfo.getId().toString(), token);
        return new String(Base64.getEncoder().encode((tUserInfo.getId() + ":" + token).getBytes()));
    }

    public void register(UserReq userReq) {
        if (!userReq.verify()) {
            throw new MyException("参数格式不对");
        }
        Long account = Long.parseLong(userReq.getAccount());
        String password = userReq.getPassword();

        if (userRepo.findFirstByAccount(account) != null) {
            throw new MyException("用户已存在");
        }

        cloudStorageService.createRootIndex(account);

        TUserInfo tUserInfo = new TUserInfo();
        tUserInfo.setAccount(account);
        tUserInfo.setPassword(password);
        userRepo.save(tUserInfo);

    }
}
