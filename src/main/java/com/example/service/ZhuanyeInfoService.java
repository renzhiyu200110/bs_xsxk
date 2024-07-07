package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.AdminInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.entity.*;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class XueyuanInfoService {
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;


    public void add(XueyuanInfo xueyuanInfo) {
      XueyuanInfo info =  xueyuanInfoDao.findByName(xueyuanInfo.getName());
      if(ObjectUtil.isNotEmpty(info)){
          throw new CustomException("-1","学院名称已存在");
      }
        xueyuanInfoDao.insertSelective(xueyuanInfo);
    }

    public List<XueyuanInfo> findAll() {
        return xueyuanInfoDao.selectAll();
    }

    public void update(XueyuanInfo xueyuanInfo) {
        xueyuanInfoDao.updateByPrimaryKeySelective(xueyuanInfo);
    }

    public void deleteById(Long id) {
        xueyuanInfoDao.deleteByPrimaryKey(id);
    }


    public List<XueyuanInfo> find(String search) {
        return xueyuanInfoDao.find(search);
    }

    public PageInfo<XueyuanInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开启分页,下面就跟根据这两个来查对应数据
        List<XueyuanInfo>infos = xueyuanInfoDao.selectAll();
        return PageInfo.of(infos);
    }

    public PageInfo<XueyuanInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum,pageSize);
        List<XueyuanInfo>infos=xueyuanInfoDao.findByNamePage(name);
        return PageInfo.of(infos);

    }
}
