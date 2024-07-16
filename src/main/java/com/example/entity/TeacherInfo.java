package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "teacher_info")
public class TeacherInfo extends Account{
    @Column(name = "zhicheng")
    private String zhicheng;

    public String getZhicheng() {
        return zhicheng;
    }

    public void setZhicheng(String zhicheng) {
        this.zhicheng = zhicheng;
    }

    public String getZhuanyeid() {
        return zhuanyeid;
    }

    public void setZhuanyeid(String zhuanyeid) {
        this.zhuanyeid = zhuanyeid;
    }

    @Column(name = "zhuanyeid")
    private String zhuanyeid;



}
