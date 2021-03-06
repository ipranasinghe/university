package com.project.ums.services;

import com.project.ums.models.Inquirie;
import com.project.ums.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailRepository mailRepository;


    public Inquirie addMail(Inquirie mail){
       return mailRepository.save(mail);
    }

    public void solveMail(int id){
       var mail = mailRepository.findById(id).get();
       mail.setSolved(true);
       mailRepository.save(mail);
    }

    public List<Inquirie> allMails(){
        return mailRepository.findAll();
    }
}
