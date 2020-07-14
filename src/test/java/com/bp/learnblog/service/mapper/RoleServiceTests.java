package com.bp.learnblog.service.mapper;

import com.bp.learnblog.mapper.admin.PermissionMapper;
import com.bp.learnblog.mapper.admin.RoleMapper;
import com.bp.learnblog.mapper.admin.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * RoleServiceTests
 */
@Transactional
@SpringBootTest
public class RoleServiceTests {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private SqlSession sqlSession;

    @Rollback
    @Test
    public void testFindOne() {
//        System.out.println(roleMapper.findOne(1L));
//        System.out.println(roleMapper.findAll());
        System.out.println(roleMapper.findAllByPermissionId(1L));
//        System.out.println(permissionMapper.findAllByRoleId(1L));
//        System.out.println(permissionMapper.findAll());
        System.out.println(permissionMapper.findAllByRoleId(1L));
        System.out.println(userMapper.deleteUserRoleByUserRoleId(1L, 2L));
    }
}
