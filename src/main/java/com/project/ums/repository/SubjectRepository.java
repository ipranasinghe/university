package com.project.ums.repository;

import com.project.ums.models.Subject;
import com.project.ums.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
