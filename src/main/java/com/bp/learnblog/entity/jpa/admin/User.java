package com.bp.learnblog.entity.jpa.admin;

import com.bp.learnblog.entity.jpa.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户表
 *
 * @author DH
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Column(name = "`username`", unique = true)
    private String username;

    /**
     * 真实姓名
     */
    @Column(name = "`real_name`")
    private String realName;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "`password`")
    @JsonIgnore
    private String password;

    /**
     * 盐
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "`salt`")
    @JsonIgnore
    private byte[] salt;

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", columnDefinition = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", columnDefinition = "role_id"))
    @JsonIgnoreProperties("users")
    private List<Role> roles = new ArrayList<>();
}
