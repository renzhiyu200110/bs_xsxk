package com.example.entity;

import javax.persistence.*;

@Table(name = "zhuanye_info")
public class ZhuanyeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "department")
    private String department;
    @Column(name = "xueyuanid")
    private Integer xueyuanid;
    @Transient
private String xueyuanName;

    public String getXueyuanName() {
        return xueyuanName;
    }

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getXueyuanid() {
        return xueyuanid;
    }

    public void setXueyuanid(Integer xueyuanid) {
        this.xueyuanid = xueyuanid;
    }
}
