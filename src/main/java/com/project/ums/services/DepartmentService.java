package com.project.ums.services;

import com.project.ums.controllers.dto.DepartmentEnrollmentDTO;
import com.project.ums.models.Department;
import com.project.ums.models.Role;
import com.project.ums.models.Semester;
import com.project.ums.models.User;
import com.project.ums.repository.DepartmentRepository;
import com.project.ums.repository.SemesterRepository;
import com.project.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    public UserRepository userRepository;

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public Optional<Department> findById(int id){
        return departmentRepository.findById(id);
    }

    public void addDepartmentEnrollment(DepartmentEnrollmentDTO dto) {

        User user = userRepository.findById(dto.getUserId()).get();
        Department department = departmentRepository.findById(dto.getDepartmentId()).get();
        department.adduser(user);
        departmentRepository.save(department);
    }
}
