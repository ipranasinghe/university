package com.project.ums.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Profile {

    private String indexNumber;
    private String initials;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="department_id", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "student")
    private Set<StudentContact> studentContacts = new HashSet<>();

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<StudentContact> getStudentContacts() {
        return studentContacts;
    }

    public void setStudentContacts(Set<StudentContact> studentContacts) {
        this.studentContacts = studentContacts;
    }
}
