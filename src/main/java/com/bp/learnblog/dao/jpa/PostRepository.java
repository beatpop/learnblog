package com.bp.learnblog.dao.jpa;

import com.bp.learnblog.entity.jpa.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 博客dao
 *
 * @author DH
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    /**
     * 根据用户ID查找博客
     *
     * @param userId
     * @return
     */
    public List<Post> findAllByUserOrderById(Long userId);
}
