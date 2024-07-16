package com.example.controller;

import com.example.common.Result;
import com.example.entity.XueyuanInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.service.XueyuanInfoService;
import com.example.service.ZhuanyeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//根账号相关的
@RestController
@RequestMapping("/zhuanyeInfo")
public class ZhuanyeInfoController {
    @Resource
    private ZhuanyeInfoService zhuanyeInfoService;
    @PostMapping
    public Result add(@RequestBody ZhuanyeInfo zhuanyeInfo) {
        zhuanyeInfoService.add(zhuanyeInfo);
        return Result.success();

    }
    @GetMapping
    public Result findAll() {
        List<ZhuanyeInfo> list = zhuanyeInfoService.findAll();
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody ZhuanyeInfo zhuanyeInfo) {
        zhuanyeInfoService.update(zhuanyeInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zhuanyeInfoService.deleteById(id);
        return Result.success();
    }
    @GetMapping("/{search}")
    public Result find(@PathVariable String search){
        List<ZhuanyeInfo> list = zhuanyeInfoService.find(search);
        return Result.success(list);
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<ZhuanyeInfo> info = zhuanyeInfoService.findPage(pageNum, pageSize);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name) {
        PageInfo<ZhuanyeInfo> info = zhuanyeInfoService.findPageName(pageNum, pageSize, name);
        return Result.success(info);
    }


}
