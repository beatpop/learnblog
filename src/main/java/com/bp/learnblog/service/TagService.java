package com.bp.learnblog.service;

import com.bp.learnblog.entity.Tag;

import java.util.List;

/**
 * TagService
 *
 * @author DH
 */
public interface TagService {
    /**
     * 根据标签 ID 查找标签详情
     *
     * @param id
     * @return
     */
    public Tag findById(Long id);

    /**
     * 查找所有标签
     *
     * @return
     */
    public Iterable<Tag> findAll();

    /**
     * 根据用户ID查找标签列表
     *
     * @param userId
     * @return
     */
    public List<Tag> findAllByUserId(Long userId);

    /**
     * 创建标签
     *
     * @param tag
     * @return
     */
    public Tag createTag(Tag tag);

    /**
     * 更新标签
     *
     * @param id
     * @param tag
     * @return
     */
    public Tag updateTag(Long id, Tag tag);

    /**
     * 根据ID删除标签
     *
     * @param id
     * @return
     */
    public boolean deleteById(Long id);
}
