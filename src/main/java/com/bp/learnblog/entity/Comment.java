package com.bp.learnblog.entity;

import com.bp.learnblog.entity.admin.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * 评论（回帖）
 *
 * @author DH
 */
@Entity
@Table(name = "`comment`")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Comment extends BaseEntity {
    /**
     * 评论标题
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 回复内容
     */
    @Column(name = "`content`")
    private String content;

    /**
     * 对应用户
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`user_id`", columnDefinition = "`user_id`")
    @JsonIgnoreProperties("roles")
    private User user;

    /**
     * 对应博客文章
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`post_id`", columnDefinition = "`post_id`")
    private Post post;

    /**
     * 父级评论
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`parent_id`", columnDefinition = "`parent_id`")
    @JsonIgnore
    private Comment parent;

    /**
     * 评论回复(子评价)
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "`parent_id`", columnDefinition = "`parent_id`")
    @JsonIgnoreProperties({"user", "post", "parent"})
    private List<Comment> comments;
}
