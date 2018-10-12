package com.tlf.user.server.repository;

import com.tlf.commondata.repository.MyRepository;
import com.tlf.user.server.entity.TUserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepo extends MyRepository<TUserInfo, Long> {
    TUserInfo findFirstByAccountAndPassword(Long account, String password);

    TUserInfo findFirstByAccount(Long account);

    @Modifying
    @Transactional
    void deleteAllByAccount(Long account);
}
