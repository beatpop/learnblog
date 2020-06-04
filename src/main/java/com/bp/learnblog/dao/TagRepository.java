package com.bp.learnblog.dao;

import com.bp.learnblog.entity.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * 标签 DAO
 *
 * @author DH
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
}
