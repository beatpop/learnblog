package com.bp.learnblog.entity.admin;

import com.bp.learnblog.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 * @author DH
 */
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Role extends BaseEntity implements Serializable {
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

    @ManyToMany(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id", columnDefinition = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", columnDefinition = "user_id"))
    @JsonIgnoreProperties("roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @ManyToMany(targetEntity = Permission.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", columnDefinition = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", columnDefinition = "permission_id"))
    @JsonIgnoreProperties("roles")
    private List<Permission> permissions = new ArrayList<>();
}
