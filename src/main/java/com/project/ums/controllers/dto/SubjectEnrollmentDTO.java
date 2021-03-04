package com.project.ums.controllers.dto;

public class SubjectEnrollmentDTO {
    private long userId;
    private int subjectId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
