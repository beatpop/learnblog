package com.bp.learnblog.entity.mybatis.admin;

import com.bp.learnblog.entity.mybatis.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis(角色)
 *
 * @author DH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    @JsonIgnoreProperties("roles")
    private List<User> users = new ArrayList<>();

    @JsonIgnoreProperties("roles")
    private List<Permission> permissions = new ArrayList<>();
}
