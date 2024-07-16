package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.*;
import com.example.entity.*;
import com.example.entity.XuankeInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class XuankeInfoService {
    @Resource
    private XuankeInfoDao xuankeInfoDao;
    @Resource
    private KechengInfoDao kechengInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;
    @Resource
    private ZhuanyeInfoDao zhuanyeInfoDao;
    @Resource
    private TeacherInfoDao teacherInfoDao;
    @Resource
    private StudentInfoDao studentInfoDao;

    public void add(XuankeInfo xuankeInfo) {
//        XuankeInfo info = xuankeInfoDao.findByName(xuankeInfo.getName());
//        if (ObjectUtil.isNotEmpty(info)) {
//            throw new CustomException("-1", "学院名称已存在");
//        }
        xuankeInfoDao.insertSelective(xuankeInfo);
    }

    public List<XuankeInfo> findAll(HttpServletRequest httpServletRequest) {
        Account user = (Account) httpServletRequest.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录失效");
        }
        List<XuankeInfo> list = null;
        if(1== user.getLevel()){
            list = xuankeInfoDao.selectAll();
        }
        if(2== user.getLevel()){
            list = xuankeInfoDao.findBycondition(user.getId(), null);
        }
        if(3== user.getLevel()){
            list = xuankeInfoDao.findBycondition(null, user.getId());
        }
//方式1
        for (XuankeInfo xuankeInfo : list) {
            if (ObjectUtil.isNotEmpty(xuankeInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeid());
                xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherid());
                xuankeInfo.setTeacherName(teacherInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getXueshengid());
                xuankeInfo.setXueshengName(studentInfo.getName());
            }

        }
        return list;

//        return zhuanyeInfoDao.selectAll();
    }

    public void update(XuankeInfo xuankeInfo) {
        xuankeInfoDao.updateByPrimaryKeySelective(xuankeInfo);
    }

    public void deleteById(Long id) {
     XuankeInfo xuankeInfo=  xuankeInfoDao.selectByPrimaryKey(id);
    KechengInfo kechengInfo= kechengInfoDao.findByName(xuankeInfo.getName());
    xuankeInfoDao.deleteByPrimaryKey(id);
    kechengInfo.setYixuan(kechengInfo.getYixuan()-1);
    kechengInfoDao.updateByPrimaryKeySelective(kechengInfo);

    }


//    public List<XuankeInfo> find2(String search) {
//        return xuankeInfoDao.find2(search);
//    }

    public PageInfo<XuankeInfo> findPage(Integer pageNum, Integer pageSize,HttpServletRequest httpServletRequest) {
        PageHelper.startPage(pageNum, pageSize);//开启分页,下面就跟根据这两个来查对应数据
//        List<XuankeInfo> list = xuankeInfoDao.selectAll();

        Account user = (Account) httpServletRequest.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录2失效");
        }
        List<XuankeInfo> list = null;
        if(1== user.getLevel()){
            list = xuankeInfoDao.selectAll();
        }
        if(2== user.getLevel()){
            list = xuankeInfoDao.findBycondition(user.getId(), null);
        }
        if(3== user.getLevel()){
            list = xuankeInfoDao.findBycondition(null, user.getId());
        }

        for (XuankeInfo xuankeInfo : list) {
            if (ObjectUtil.isNotEmpty(xuankeInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeid());
                xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherid());
                xuankeInfo.setTeacherName(teacherInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getXueshengid());
                xuankeInfo.setXueshengName(studentInfo.getName());
            }
        }
        return PageInfo.of(list);
    }

    public PageInfo<XuankeInfo> findPageName(Integer pageNum, Integer pageSize, String name,HttpServletRequest httpServletRequest) {
        PageHelper.startPage(pageNum, pageSize);
//        List<XuankeInfo> infos = xuankeInfoDao.findByNamePage(name);
        Account user = (Account) httpServletRequest.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录2失效");
        }
        List<XuankeInfo> list = null;
        if(1== user.getLevel()){
            list = xuankeInfoDao.selectAll();
        }
        if(2== user.getLevel()){
            list = xuankeInfoDao.findBycondition(user.getId(), null);
        }
        if(3== user.getLevel()){
            list = xuankeInfoDao.findBycondition(null, user.getId());
        }

        for (XuankeInfo xuankeInfo : list) {
            if (ObjectUtil.isEmpty(xuankeInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(xuankeInfo.getZhuanyeid());
                xuankeInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(xuankeInfo.getTeacherid());
                xuankeInfo.setTeacherName(teacherInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(xuankeInfo.getTeacherid())) {
                StudentInfo studentInfo = studentInfoDao.selectByPrimaryKey(xuankeInfo.getXueshengid());
                xuankeInfo.setXueshengName(studentInfo.getName());
            }
        }
        return PageInfo.of(list);

    }


    public XuankeInfo find(String name, Long id) {
        return xuankeInfoDao.find(name, id);
    }

    public void kaike(XuankeInfo xuankeInfo) {
        xuankeInfoDao.updateByPrimaryKeySelective(xuankeInfo);
    }
}
