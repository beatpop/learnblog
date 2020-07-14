package com.bp.learnblog.dao.jpa;

import com.bp.learnblog.entity.jpa.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * 类别 DAO
 *
 * @author DH
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
