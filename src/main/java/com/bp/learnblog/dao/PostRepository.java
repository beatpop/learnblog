package com.bp.learnblog.dao;

import com.bp.learnblog.entity.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * 博客dao
 *
 * @author DH
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
