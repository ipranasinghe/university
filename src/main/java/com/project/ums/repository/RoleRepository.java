package com.project.ums.repository;

import com.project.ums.models.Role;
import com.project.ums.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByName(String name);
    Role findRoleById(int id);
}
