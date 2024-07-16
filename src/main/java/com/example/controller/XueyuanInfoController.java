package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.AdminInfoService;
import com.example.service.StudentInfoService;
import com.example.service.TeacherInfoService;
import com.example.service.XueyuanInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//根账号相关的
@RestController
@RequestMapping("/xueyuanInfo")
public class XueyuanInfoController {
    @Resource
    private XueyuanInfoService xueyuanInfoService;

    @PostMapping
    public Result add(@RequestBody XueyuanInfo xueyuanInfo) {
        xueyuanInfoService.add(xueyuanInfo);
        return Result.success();

    }

    @GetMapping
    public Result findAll() {
        List<XueyuanInfo> list = xueyuanInfoService.findAll();
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody XueyuanInfo xueyuanInfo) {
        xueyuanInfoService.update(xueyuanInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xueyuanInfoService.deleteById(id);
        return Result.success();
    }
@GetMapping("/{search}")
    public Result find(@PathVariable String search){
    List<XueyuanInfo> list = xueyuanInfoService.find(search);
    return Result.success(list);
}
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<XueyuanInfo> info = xueyuanInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name) {
        PageInfo<XueyuanInfo> info = xueyuanInfoService.findPageName(pageNum, pageSize, name);
        return Result.success(info);
    }


}
