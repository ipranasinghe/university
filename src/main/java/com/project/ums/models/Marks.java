package com.project.ums.models;

import javax.persistence.*;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private float ese;
    private float tutorials;
    private float quizzes;
    private float assignments;
    private float practicals;

    private int subjectID;

    private Long userId;

    private int semesterId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getEse() {
        return ese;
    }

    public void setEse(float ese) {
        this.ese = ese;
    }

    public float getTutorials() {
        return tutorials;
    }

    public void setTutorials(float tutorials) {
        this.tutorials = tutorials;
    }

    public float getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(float quizzes) {
        this.quizzes = quizzes;
    }

    public float getAssignments() {
        return assignments;
    }

    public void setAssignments(float assignments) {
        this.assignments = assignments;
    }

    public float getPracticals() {
        return practicals;
    }

    public void setPracticals(float practicals) {
        this.practicals = practicals;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }
}
