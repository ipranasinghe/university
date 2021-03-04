package com.project.ums.repository;

import com.project.ums.models.Profile;
import com.project.ums.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {
    Profile findProfileByNic(String Nic);
}
