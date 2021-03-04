package com.project.ums.repository;

import com.project.ums.models.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {

    List<Marks> findMarksByUserId(int id);
}
