package com.bp.learnblog.dao.admin;

import com.bp.learnblog.entity.admin.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * RoleDAO
 * @author DH
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * 根据userId查找角色列表
     *
     * @param userId
     * @return
     */
    @Query(value = "SELECT r.* FROM role r LEFT JOIN user_role ur ON r.id = ur.role_id \n" +
            "WHERE ur.user_id = :userId  GROUP BY r.id;", nativeQuery = true)
    public List<Role> findAllByUserId(@Param("userId") Long userId);
}
