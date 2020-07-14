package com.bp.learnblog.service.jpa.impl;

import com.bp.learnblog.dao.jpa.PostRepository;
import com.bp.learnblog.entity.jpa.Post;
import com.bp.learnblog.service.jpa.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 博客 PostService
 *
 * @author DH
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private PostRepository postRepository;

    /**
     * 根据id查找博客
     *
     * @param id
     * @return
     */
    @Override
    public Post findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * 查找全部博客
     *
     * @return
     */
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * 根据用户 id 查找该用户的所有博客
     *
     * @param userId
     * @return
     */
    @Override
    public List<Post> findAllByUserId(Long userId) {
        return postRepository.findAllByUserOrderById(userId);
    }

    /**
     * 创建博客文章
     *
     * @param post
     * @return
     */
    @Override
    public Post createPost(Post post) {
        // 判断新增必填的字段不为空
        if (post != null && post.getTitle() != null && post.getUser() != null) {
            Post createPost = new Post();
            createPost.setTitle(post.getTitle());
            createPost.setContent(post.getContent());
            createPost.setDescription(post.getDescription());
            if (post.getTags().size() > 0) {
                createPost.setTags(post.getTags());
            }
            if (post.getCategories().size() > 0) {
                createPost.setCategories(post.getCategories());
            }
            postRepository.save(createPost);
            return createPost;
        }
        return null;
    }

    /**
     * 更新博客文件
     *
     * @param id
     * @param post
     * @return
     */
    @Override
    public Post updatePost(Long id, Post post) {
        // 查找博客文章是否存在
        Post updatePost = postRepository.findById(id).orElse(null);
        if (updatePost != null && post != null && post.getTitle() != null) {
            updatePost.setTitle(post.getTitle());
            updatePost.setContent(post.getContent());
            updatePost.setDescription(post.getDescription());
            updatePost.setCategories(post.getCategories());
            updatePost.setTags(post.getTags());
            postRepository.save(updatePost);
            return updatePost;
        }
        return null;
    }

    /**
     * 删除博客文章
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        try {
            postRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete post by Id# " + id + "# faithfully: {}", e.getMessage());
            return false;
        }
    }
}
