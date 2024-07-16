package com.example.entity;

import javax.persistence.*;

@Table(name = "kecheng_info")
public class KechengInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "discription")
    private String discription;
    @Column(name = "score")
    private Integer score;
    @Column(name = "zhuanyeid")
    private Long zhuanyeid;
    @Column(name = "teacherid")
    private Long teacherid;
    @Column(name = "kaiban")
    private Integer kaiban;
    @Column(name = "yixuan")
    private Integer yixuan;
    @Column(name = "time")
    private String time;
    @Column(name = "location")
    private String location;
    @Transient
private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getZhuanyeName() {
        return zhuanyeName;
    }

    public void setZhuanyeName(String zhuanyeName) {
        this.zhuanyeName = zhuanyeName;
    }

    @Transient
    private String zhuanyeName;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getZhuanyeid() {
        return zhuanyeid;
    }

    public void setZhuanyeid(Long zhuanyeid) {
        this.zhuanyeid = zhuanyeid;
    }

    public Long getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Long teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getKaiban() {
        return kaiban;
    }

    public void setKaiban(Integer kaiban) {
        this.kaiban = kaiban;
    }

    public Integer getYixuan() {
        return yixuan;
    }

    public void setYixuan(Integer yixuan) {
        this.yixuan = yixuan;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
