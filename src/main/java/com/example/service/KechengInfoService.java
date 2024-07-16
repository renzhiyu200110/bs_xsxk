package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.dao.KechengInfoDao;
import com.example.dao.TeacherInfoDao;
import com.example.dao.XueyuanInfoDao;
import com.example.dao.ZhuanyeInfoDao;
import com.example.entity.KechengInfo;
import com.example.entity.TeacherInfo;
import com.example.entity.XueyuanInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KechengInfoService {
    @Resource
    private KechengInfoDao kechengInfoDao;
    @Resource
    private XueyuanInfoDao xueyuanInfoDao;
    @Resource
    private ZhuanyeInfoDao zhuanyeInfoDao;
    @Resource
    private TeacherInfoDao teacherInfoDao;

    public void add(KechengInfo kechengInfo) {
        KechengInfo info = kechengInfoDao.findByName(kechengInfo.getName());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException("-1", "课程名称已存在");
        }
        kechengInfoDao.insertSelective(kechengInfo);
    }

    public List<KechengInfo> findAll() {
        List<KechengInfo> list = kechengInfoDao.selectAll();
//方式1
        for (KechengInfo kechengInfo : list) {
            if (ObjectUtil.isNotEmpty(kechengInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(kechengInfo.getZhuanyeid());
                kechengInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(kechengInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(kechengInfo.getTeacherid());
                kechengInfo.setTeacherName(teacherInfo.getName());
            }
        }
        return list;

//        return zhuanyeInfoDao.selectAll();
    }

    public void update(KechengInfo kechengInfo) {
        kechengInfoDao.updateByPrimaryKeySelective(kechengInfo);
    }

    public void deleteById(Long id) {
        kechengInfoDao.deleteByPrimaryKey(id);
    }


    public List<KechengInfo> find(String search) {
        return kechengInfoDao.find(search);
    }

    public PageInfo<KechengInfo> findPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页,下面就跟根据这两个来查对应数据
        List<KechengInfo> infos = kechengInfoDao.selectAll();
        for (KechengInfo kechengInfo : infos) {
            if (ObjectUtil.isNotEmpty(kechengInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(kechengInfo.getZhuanyeid());
                kechengInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(kechengInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(kechengInfo.getTeacherid());
                kechengInfo.setTeacherName(teacherInfo.getName());
            }
        }
        return PageInfo.of(infos);
    }

    public PageInfo<KechengInfo> findPageName(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<KechengInfo> infos = kechengInfoDao.findByNamePage(name);
        for (KechengInfo kechengInfo : infos) {
            if (ObjectUtil.isNotEmpty(kechengInfo.getZhuanyeid())) {
                ZhuanyeInfo zhuanyeInfo = zhuanyeInfoDao.selectByPrimaryKey(kechengInfo.getZhuanyeid());
                kechengInfo.setZhuanyeName(zhuanyeInfo.getName());
            }
            if (ObjectUtil.isNotEmpty(kechengInfo.getTeacherid())) {
                TeacherInfo teacherInfo = teacherInfoDao.selectByPrimaryKey(kechengInfo.getTeacherid());
                kechengInfo.setTeacherName(teacherInfo.getName());
            }
        }
        return PageInfo.of(infos);

    }
}
