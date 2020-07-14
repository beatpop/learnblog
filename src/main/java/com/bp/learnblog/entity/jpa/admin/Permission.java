package com.bp.learnblog.entity.jpa.admin;

import com.bp.learnblog.entity.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 * @author DH
 */
@Entity
@Table(name = "permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
//@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Permission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 角色列表
     */
    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "permission_id", columnDefinition = "permission_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", columnDefinition = "role_id"))
    @JsonIgnoreProperties({"permissions", "users"})
    private List<Role> roles = new ArrayList<>();
}
