package com.example.controller;

import com.example.common.Result;
import com.example.entity.KechengInfo;
import com.example.entity.ZhuanyeInfo;
import com.example.service.KechengInfoService;
import com.example.service.ZhuanyeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//根账号相关的
@RestController
@RequestMapping("/kechengInfo")
public class KechengInfoController {
    @Resource
    private KechengInfoService kechengInfoService;
    @PostMapping
    public Result add(@RequestBody KechengInfo kechengInfo) {
        kechengInfoService.add(kechengInfo);
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
    public Result find(@PathVariable String search){
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
        PageInfo<KechengInfo> info = kechengInfoService .findPageName(pageNum, pageSize, name);
        return Result.success(info);
    }


}
