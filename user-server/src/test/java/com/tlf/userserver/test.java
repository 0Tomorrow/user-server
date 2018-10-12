package com.tlf.userserver;

import com.tlf.user.server.UserServerApplication;
import com.tlf.user.server.repository.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServerApplication.class)
public class test {
    @Autowired
    UserRepo userRepo;

    @Test
    public void test() {
    }
}
