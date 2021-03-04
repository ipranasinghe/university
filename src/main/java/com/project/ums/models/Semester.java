package com.project.ums.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String batch;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startsOn;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endsOn;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Date getStartsOn() {
        return startsOn;
    }

    public void setStartsOn(Date startsOn) {
        this.startsOn = startsOn;
    }

    public Date getEndsOn() {
        return endsOn;
    }

    public void setEndsOn(Date endsOn) {
        this.endsOn = endsOn;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
