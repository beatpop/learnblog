package com.bp.learnblog.mapper.admin;

import com.bp.learnblog.entity.mybatis.admin.Role;
import com.bp.learnblog.entity.mybatis.admin.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * userMapper
 *
 * @author DH
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 更新用户
     *
     * @param userId
     * @param user
     * @return
     */
    int updateById(@Param("userId") Long userId, User user);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    int deleteById(@Param("userId") Long userId);

    /**
     * 根据用户ID查找用户信息
     *
     * @param userId
     * @return
     */
    User findOne(@Param("userId") Long userId);

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return
     */
    User findByUsername(@Param("username") String username);

    /**
     * 查找所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 新增用户角色关联到用户角色中间表
     *
     * @param user
     * @param role
     * @return
     */
    int insertUserRole(User user, Role role);

    /**
     * 根据用户ID及角色ID删除用户角色关联（若只传其中一个ID则为删除所有关联）
     * @param userId
     * @param roleId
     * @return
     */
    int deleteUserRoleByUserRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
