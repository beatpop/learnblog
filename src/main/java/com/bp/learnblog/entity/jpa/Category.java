package com.bp.learnblog.entity.jpa;

import com.bp.learnblog.entity.jpa.admin.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 博客文章分类
 *
 * @author DH
 */
@Entity
@Table(name = "`category`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Category extends BaseEntity {
    /**
     * 分类名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 分类描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 作者（用户id）
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_id`", columnDefinition = "`user_id`")
    @JsonIgnoreProperties("roles")
    private User user;

    /**
     * 当前类别下的博客文章
     */
    @ManyToMany(targetEntity = Post.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "post_category", joinColumns = @JoinColumn(name = "`category_id`", columnDefinition = "`category_id`"),
            inverseJoinColumns = @JoinColumn(name = "`post_id`", columnDefinition = "`post_id`"))
    @JsonIgnoreProperties({"user", "tags", "comments"})
    private List<Post> posts;
}
