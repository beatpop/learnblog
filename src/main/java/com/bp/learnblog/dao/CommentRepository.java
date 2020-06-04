package com.bp.learnblog.dao;

import com.bp.learnblog.entity.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * 评价 DAO
 *
 * @author DH
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
