package com.project.ums.services;

import com.project.ums.models.Subject;
import com.project.ums.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    public SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }

    public Optional<Subject> findById(int id){
        return subjectRepository.findById(id);
    }

    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }
}
