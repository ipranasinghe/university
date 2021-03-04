package com.project.ums.models;

import javax.persistence.*;

@Entity
public class StudentContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String contactNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="student_id", referencedColumnName = "id")
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
