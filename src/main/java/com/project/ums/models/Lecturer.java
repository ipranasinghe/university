package com.project.ums.models;

import javax.persistence.*;

@Entity
public class Lecturer extends Profile {
    private int employeeNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="department_id", referencedColumnName = "id")
    private Department department;

    public int getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(int employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
