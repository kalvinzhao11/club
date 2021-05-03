package com.kal.club.Repo;

import com.kal.club.Entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepo extends CrudRepository<Role, Long> {
    Role findByNameIgnoreCase(String roleName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE roles SET name = :name, last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP_WHERE roleid = :roleid", nativeQuery = true)
    void updateRoleName(String uname, long roleid, String name);
}
