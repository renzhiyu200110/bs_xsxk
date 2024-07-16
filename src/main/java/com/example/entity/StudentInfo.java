package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "student_info")
public class StudentInfo extends Account {
    @Column(name = "score")
    private Integer score;
    @Column(name = "code")
    private String code;
    @Transient
    private String xueyuanName;

    @Column(name = "xueyuanid")
    private Long xueyuanid;

    public String getXueyuanName() {
        return xueyuanName;
    }

    public void setXueyuanName(String xueyuanName) {
        this.xueyuanName = xueyuanName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getXueyuanid() {
        return xueyuanid;
    }

    public void setXueyuanid(Long xueyuanid) {
        this.xueyuanid = xueyuanid;
    }
}
