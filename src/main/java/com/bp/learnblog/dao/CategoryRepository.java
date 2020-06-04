package com.bp.learnblog.dao;

import com.bp.learnblog.entity.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * 类别 DAO
 *
 * @author DH
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
