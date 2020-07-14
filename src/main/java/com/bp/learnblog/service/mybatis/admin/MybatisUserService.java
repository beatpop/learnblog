package com.bp.learnblog.service.mybatis.admin;

import com.bp.learnblog.entity.mybatis.admin.User;

import java.util.List;

/**
 * UserService
 *
 * @author DH
 */
public interface MybatisUserService {
    /**
     * 查找所有用户
     *
     * @return
     */
    public List<User> findAll();

    /**
     * 根据用户 ID 查找用户
     *
     * @param userId
     * @return
     */
    public User findByUserId(Long userId);

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
