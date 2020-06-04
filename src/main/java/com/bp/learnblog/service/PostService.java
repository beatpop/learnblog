package com.bp.learnblog.service;

import com.bp.learnblog.entity.Post;

import java.util.List;

/**
 * PostService
 *
 * @author DH
 */
public interface PostService {

    /**
     * 根据id查找博客
     *
     * @param id
     * @return
     */
    public Post findById(Long id);

    /**
     * 查找全部博客
     *
     * @return
     */
    public Iterable<Post> findAll();

    /**
     * 根据用户 id 查找该用户的所有博客
     *
     * @param userId
     * @return
     */
    public List<Post> findAllByUserId(Long userId);

    /**
     * 创建博客文章
     *
     * @param post
     * @return
     */
    public Post createPost(Post post);

    /**
     * 更新博客文件
     *
     * @param id
     * @param post
     * @return
     */
    public Post updatePost(Long id, Post post);

    /**
     * 删除博客文章
     *
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
