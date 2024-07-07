package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.TeacherInfoDao;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.TeacherInfo;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}


