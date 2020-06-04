package com.bp.learnblog.entity;

import com.bp.learnblog.entity.admin.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 标签
 *
 * @author DH
 */
@Entity
@Table(name = "`tag`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Tag extends BaseEntity {
    /**
     * 标签名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 描述说明
     */
    @Column(name = "`description`")
    private String description;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_id`", columnDefinition = "`user_id`")
    @JsonIgnoreProperties({"roles"})
    private User user;

    @ManyToMany(targetEntity = Post.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "tag_id", columnDefinition = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id", columnDefinition = "post_id"))
    @JsonIgnoreProperties({"user", "tags"})
    private List<Post> posts;
}
