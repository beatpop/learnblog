package com.bp.learnblog.entity.mybatis.admin;

import com.bp.learnblog.entity.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis（权限）
 *
 * @author DH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 角色列表
     */
    @JsonIgnoreProperties({"permissions", "users"})
    private List<Role> roles = new ArrayList<>();
}
