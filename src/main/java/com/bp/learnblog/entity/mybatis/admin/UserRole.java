package com.bp.learnblog.entity.mybatis.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户-角色（多对多关联）
 *
 * @author DH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    /**
     * 用户
     */
    private User user;

    /**
     * 角色
     */
    private Role role;
}
