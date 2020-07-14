package com.bp.learnblog.entity.mybatis.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色-权限（多对多关联）
 *
 * @author DH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission implements Serializable {
    /**
     * 角色
     */
    private Role role;

    /**
     * 权限
     */
    private Permission permission;
}
