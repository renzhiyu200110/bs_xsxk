package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.KechengInfo;
import com.example.entity.XuankeInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.exception.CustomException;
import com.example.service.KechengInfoService;
import com.example.service.XuankeInfoService;
import com.example.service.ZhuanyeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//根账号相关的
@RestController
@RequestMapping("/kechengInfo")
public class KechengInfoController {
    @Resource
    private KechengInfoService kechengInfoService;
    @Resource
    private XuankeInfoService xuankeInfoService;

    @PostMapping
    public Result add(@RequestBody KechengInfo kechengInfo) {
        kechengInfoService.add(kechengInfo);
        return Result.success();

    }

    @PostMapping("/xuanke")
    public Result xuanke(@RequestBody KechengInfo kechengInfo, HttpServletRequest httpServletRequest) {

//先判断已选人数是否满了，把课程信息放到课程信息表，把剩余字段补全，已选人数+1，
        Account user = (Account) httpServletRequest.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException("-1", "登录已失效");
        }

        //判断是否选过
        XuankeInfo info = xuankeInfoService.find(kechengInfo.getName(), user.getId());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException("-1", "不要重复选择！！！！");
        }
        XuankeInfo xuankeInfo = new XuankeInfo();
        BeanUtils.copyProperties(kechengInfo, xuankeInfo);
        xuankeInfo.setId(null);

        //赋值id
        xuankeInfo.setXueshengid(user.getId());
        xuankeInfo.setStatus("待开课");

        xuankeInfoService.add(xuankeInfo);
        kechengInfo.setYixuan(kechengInfo.getYixuan() + 1);
        kechengInfoService.update(kechengInfo);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        List<KechengInfo> list = kechengInfoService.findAll();
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody KechengInfo kechengInfo) {
        kechengInfoService.update(kechengInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        kechengInfoService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{search}")
    public Result find(@PathVariable String search) {
        List<KechengInfo> list = kechengInfoService.find(search);
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<KechengInfo> info = kechengInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name) {
        PageInfo<KechengInfo> info = kechengInfoService.findPageName(pageNum, pageSize, name);
        return Result.success(info);
    }


}
