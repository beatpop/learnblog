package com.bp.learnblog.service.jpa;

import com.bp.learnblog.entity.jpa.Comment;

import java.util.List;

/**
 * CommentService
 *
 * @author DH
 */
public interface CommentService {

    /**
     * 根据ID查找评论详情
     *
     * @param id
     * @return
     */
    public Comment findById(Long id);

    /**
     * 查找所有评论
     *
     * @return
     */
    public Iterable<Comment> findAll();

    /**
     * 根据用户ID查找其所有评论
     *
     * @param userId
     * @return
     */
    public List<Comment> findAllByUserId(Long userId);

    /**
     * 根据博客ID查找所有评论
     *
     * @param postId
     * @return
     */
    public List<Comment> findAllByPostId(Long postId);

    /**
     * 根据博客ID及用户ID查找所有评论
     *
     * @param postId
     * @param userId
     * @return
     */
    public List<Comment> findAllByPostAndUserId(Long postId, Long userId);

    /**
     * 新增评论
     *
     * @param comment
     * @return
     */
    public Comment createComment(Comment comment);

    /**
     * 更新评论
     *
     * @param id
     * @param comment
     * @return
     */
    public Comment updateComment(Long id, Comment comment);

    /**
     * 根据ID删除评论
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
