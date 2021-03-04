package com.project.ums.services;

import com.project.ums.models.Marks;
import com.project.ums.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    public MarksRepository marksRepository;

    public Marks add(Marks marks){
        return  marksRepository.save(marks);
    }

    public List<Marks> findMarksByUser(Long id){
        return marksRepository.findMarksByUserId(id);
    }
}
