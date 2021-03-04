package com.project.ums.repository;

import com.project.ums.models.Department;
import com.project.ums.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
