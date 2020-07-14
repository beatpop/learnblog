package com.bp.learnblog.service.jpa;

import com.bp.learnblog.entity.jpa.Category;

import java.util.List;

/**
 * CategoryService
 *
 * @author DH
 */
public interface CategoryService {
    /**
     * 根据ID查找类别详情
     *
     * @param id
     * @return
     */
    public Category findById(Long id);

    /**
     * 查找所有类别
     *
     * @return
     */
    public Iterable<Category> findAll();

    /**
     * 根据用户ID查找所有类别
     *
     * @param userId
     * @return
     */
    public List<Category> findAllByUserId(Long userId);

    /**
     * 根据博客ID查找所有所属类别
     *
     * @param postId
     * @return
     */
    public List<Category> findAllByPostId(Long postId);

    /**
     * 创建类别
     *
     * @param category
     * @return
     */
    public Category createCategory(Category category);

    /**
     * 更新类别
     *
     * @param id
     * @param category
     * @return
     */
    public Category updateCategory(Long id, Category category);

    /**
     * 删除类别
     *
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
