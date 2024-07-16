package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.TeacherInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.TeacherInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherInfoService {
@Resource
  private   TeacherInfoDao teacherInfoDao;

  public void register(TeacherInfo user) {
    //教师注册不能重名
    TeacherInfo teacherInfo= teacherInfoDao.findByName(user.getName());
    if(ObjectUtil.isNotEmpty(teacherInfo)){
      throw new CustomException(ResultCode.USER_EXIST_ERROR);
    }
    teacherInfoDao.insertSelective(user);

  }

  public Account login(String name, String password) {
    TeacherInfo teacherInfo = teacherInfoDao.findByNameAndPass(name, password);
    if (ObjectUtil.isEmpty((teacherInfo))) {
      throw new CustomException("-1", "用户名、密码或者角色错误");
    }
    return teacherInfo;
  }

  public TeacherInfo findById(Long id) {
    return teacherInfoDao.selectByPrimaryKey(id);
  }

  public void update(TeacherInfo teacherInfo) {
    teacherInfoDao.updateByPrimaryKeySelective(teacherInfo);
  }

  public void add(TeacherInfo teacherInfo) {
    //不能直接插入数据库,因为新增管理员没有密码，需要初始话一个。2，有重名问题。

    //1先查找是否重复
    TeacherInfo info = teacherInfoDao.findByName(teacherInfo.getName());
    if (ObjectUtil.isNotEmpty(info)) {
      throw new CustomException(ResultCode.USER_EXIST_ERROR);
    }
    //2看密码
    if(ObjectUtil.isEmpty(teacherInfo.getPassword())){
      teacherInfo.setPassword("1");
    }
    teacherInfo.setLevel(2);
    teacherInfoDao.insertSelective(teacherInfo);
  }

  public List<TeacherInfo> findAll() {
    return teacherInfoDao.selectAll();
  }

  public void deleteById(Long id) {
    teacherInfoDao.deleteByPrimaryKey(id);
  }

  public PageInfo<TeacherInfo> findPage(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum,pageSize);//开启分页,下面就跟根据这两个来查对应数据
    List<TeacherInfo>infos = teacherInfoDao.selectAll();
    return PageInfo.of(infos);
  }

  public PageInfo<TeacherInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
    PageHelper.startPage(pageNum,pageSize);
    List<TeacherInfo>infos=teacherInfoDao.findByNamePage(name);
    return PageInfo.of(infos);

  }
}


