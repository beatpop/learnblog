package com.bp.learnblog.service.mybatis.impl.admin;

import com.bp.learnblog.entity.mybatis.admin.User;
import com.bp.learnblog.mapper.admin.UserMapper;
import com.bp.learnblog.service.mybatis.admin.MybatisUserService;
import com.bp.learnblog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserServiceImpl
 *
 * @author DH
 */
@Slf4j
@Service
public class MyBatisUserServiceImpl implements MybatisUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 查找所有用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        if (redisUtil.exists("userList")) {
            log.info("0-findAll UserList in Redis Cache.........");
            return (List<User>) redisUtil.get("userList");
        } else {
            List<User> users = userMapper.findAll();
            redisUtil.setEx("userList", users, 60);
            log.info("1-findAll UserList in Mysql.........");
            return users;
        }
    }

    /**
     * 根据用户 ID 查找用户
     *
     * @param userId
     * @return
     */
    @Override
    public User findByUserId(Long userId) {
        if (redisUtil.hExists("users", userId)) {
            log.info("0-find User with userId-{} in Redis Cache.........", userId);
            return (User) redisUtil.hGet("users", userId);
        } else {
            User user = userMapper.findOne(userId);
            redisUtil.hSet("users", userId, user);
            redisUtil.expire("users", 60);
            log.info("1-find User with userId-{} in MySQL.........", userId);
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        username = username.trim();
        return userMapper.findByUsername(username);
    }
}
