package com.bp.learnblog.service.jpa.admin;

import com.bp.learnblog.entity.jpa.admin.User;

/**
 * userService
 *
 * @author DH
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    public User findByUsername(String username);

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    public User findById(Long id);

    /**
     * 查找所有用户
     *
     * @return
     */
    public Iterable<User> findAll();

    /**
     * 校验用户名密码
     *
     * @param username 用户名
     * @param password 密码
     * @return Boolean
     */
    public boolean checkUserPassword(String username, String password);

    /**
     * 创建用户（用户注册）
     *
     * @param user 用户信息
     * @return
     */
    public User createUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    public User updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
