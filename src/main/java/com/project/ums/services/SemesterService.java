package com.project.ums.services;

import com.project.ums.models.Semester;
import com.project.ums.models.Subject;
import com.project.ums.repository.SemesterRepository;
import com.project.ums.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    public SemesterRepository semesterRepository;

    public List<Semester> findAll(){
        return semesterRepository.findAll();
    }

    public Semester save(Semester subject){
        return semesterRepository.save(subject);
    }

    public Optional<Semester> findById(int id){
        return semesterRepository.findById(id);
    }

    public void delete(Semester semester) {
        semesterRepository.delete(semester);
    }
}
