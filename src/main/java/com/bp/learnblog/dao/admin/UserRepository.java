package com.bp.learnblog.dao.admin;

import com.bp.learnblog.entity.admin.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserDAO
 * @author DH
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return User
     */
    public User findByUsername(String username);
}
