package com.bp.learnblog.service;

import com.bp.learnblog.entity.admin.Role;
import com.bp.learnblog.entity.admin.User;
import com.bp.learnblog.service.admin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;


@Slf4j
@SpringBootTest
public class UserServiceTests {
    @Resource
    private UserService userService;

    @Test
    @Transactional
    @Rollback
    public void testCreateUser() {
        User user = new User();
        Long userId = 10L;
        user.setId(userId);
        user.setUsername("testUser");
        user.setPassword("123456");
        Role role = Mockito.mock(Role.class);
        role.setId(1L);
        user.setRoles(Collections.singletonList(role));
        User insertUser = userService.createUser(user);
        Assertions.assertEquals(userId, insertUser.getId());
        log.info("CreateUser: {}", insertUser.toString());
    }
}
