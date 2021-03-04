package com.project.ums.controllers.dto;

public class DepartmentEnrollmentDTO {
    private long userId;
    private int departmentId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
