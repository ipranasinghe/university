package com.project.ums.controllers.dto;

import com.project.ums.models.Inquirie;
import com.project.ums.models.Subject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private long id;
    private String userName;
    private String password;
    private int type;
    private String email;
    private String nic;
    private String name;
    private String role;

    private String indexNumber;
    private String employeeNumber;

    private List<Subject> subjects;
    private List<Inquirie> mails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Inquirie> getMails() {
        return mails;
    }

    public void setMails(List<Inquirie> mails) {
        this.mails = mails;
    }
}
