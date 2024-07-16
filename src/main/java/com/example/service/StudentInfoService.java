package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.StudentInfoDao;
import com.example.dao.TeacherInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.Account;
import com.example.entity.StudentInfo;
import com.example.entity.TeacherInfo;
import com.example.entity.XueyuanInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentInfoService {
    @Resource
    private StudentInfoDao studentInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;

    public void register(StudentInfo user) {
        //教师注册不能重名
        StudentInfo studentInfo = studentInfoDao.findByName(user.getName());
        if (ObjectUtil.isNotEmpty(studentInfo)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        studentInfoDao.insertSelective(user);
    }

    public Account login(String name, String password) {
        StudentInfo studentInfo = studentInfoDao.findByNameAndPass(name, password);
        if (ObjectUtil.isEmpty((studentInfo))) {
            throw new CustomException("-1", "用户名、密码或者角色错误");
        }
        return studentInfo;
    }

    public StudentInfo findById(Long id) {
        return studentInfoDao.selectByPrimaryKey(id);
    }

    public void update(StudentInfo studentInfo) {
        studentInfoDao.updateByPrimaryKeySelective(studentInfo);
    }

    public void add(StudentInfo studentInfo) {
        //不能直接插入数据库,因为新增管理员没有密码，需要初始话一个。2，有重名问题。
        //1先查找是否重复
        StudentInfo info = studentInfoDao.findByName(studentInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        //2看密码
        if (ObjectUtil.isEmpty(studentInfo.getPassword())) {
            studentInfo.setPassword("1");
        }
        studentInfo.setLevel(3);
        studentInfoDao.insertSelective(studentInfo);
    }

    public List<StudentInfo> findAll() {
        List<StudentInfo> list = studentInfoDao.selectAll();
//方式1
        for (StudentInfo studentInfo : list) {
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanid())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanid());
                studentInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }
        return list;
        //房事2
//        return  studentInfoDao.findAllJoinXueyuan();
    }

    public void deleteById(Long id) {
        studentInfoDao.deleteByPrimaryKey(id);
    }

    public PageInfo<StudentInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页,下面就跟根据这两个来查对应数据
        List<StudentInfo> list = studentInfoDao.selectAll();
        for (StudentInfo studentInfo : list) {
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanid())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanid());
                studentInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }
        return PageInfo.of(list);
    }

    public PageInfo<StudentInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentInfo> list = studentInfoDao.findByNamePage(name);
        for (StudentInfo studentInfo : list) {
            if (ObjectUtil.isNotEmpty(studentInfo.getXueyuanid())) {
                XueyuanInfo xueyuanInfo = xueyuanInfoDao.selectByPrimaryKey(studentInfo.getXueyuanid());
                studentInfo.setXueyuanName(xueyuanInfo.getName());
            }
        }
        return PageInfo.of(list);

    }
}


