package com.project.ums.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    private DepartmentCode departmentCode;
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<User> user = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(DepartmentCode departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public void adduser(User user) {
        this.user.add(user);
        user.setDepartment(this);
    }
}
