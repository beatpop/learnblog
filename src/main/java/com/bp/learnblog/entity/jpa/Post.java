package com.bp.learnblog.entity.jpa;

import com.bp.learnblog.entity.jpa.admin.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 博客文章表
 *
 * @author DH
 */
@Entity
@Table(name = "`post`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Post extends BaseEntity {
    /**
     * 标题
     */
    @NotBlank(message = "博客标题不可为空")
    @Column(name = "`title`")
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不可为空")
    @Column(name = "`content`")
    private String content;

    /**
     * 描述
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 作者（用户id）
     */
    @NotEmpty(message = "用户未登录，请登录后操作")
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_id`", columnDefinition = "`user_id`")
    @JsonIgnoreProperties("roles")
    private User user;

    /**
     * 所属标签
     */
    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag", joinColumns = @JoinColumn(name = "post_id", columnDefinition = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", columnDefinition = "tag_id"))
    @JsonIgnoreProperties("roles")
    private List<Tag> tags;

    /**
     * 评价列表
     */
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`post_id`")
    @JsonIgnoreProperties({"user", "post"})
    private List<Comment> comments;

    /**
     * 所属类别
     */
    @ManyToMany(targetEntity = Category.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "`post_category`", joinColumns = @JoinColumn(name = "`post_id`", columnDefinition = "`post_id`"),
            inverseJoinColumns = @JoinColumn(name = "`category_id`", columnDefinition = "`category_id`"))
    @JsonIgnoreProperties({"posts"})
    private List<Category> categories;
}
