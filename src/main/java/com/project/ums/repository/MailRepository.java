package com.project.ums.repository;

import com.project.ums.models.Inquirie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Inquirie,Integer> {
}
