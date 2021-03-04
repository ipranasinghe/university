package com.project.ums.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subjectCode;
    private String subjectName;
    private String category;
    private int numberOfCredits;
    private String contributionToGPA;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="semester_id", referencedColumnName = "id")
    private Semester semester;

    @ManyToMany(mappedBy = "subjects")
    Set<User> users = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getContributionToGPA() {
        return contributionToGPA;
    }

    public void setContributionToGPA(String contributionToGPA) {
        this.contributionToGPA = contributionToGPA;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
