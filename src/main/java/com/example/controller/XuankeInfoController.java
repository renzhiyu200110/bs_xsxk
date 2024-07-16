package com.example.controller;

import com.example.common.Result;
import com.example.entity.XuankeInfo;
import com.example.service.XuankeInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

//根账号相关的
@RestController
@RequestMapping("/xuankeInfo")
public class XuankeInfoController {
    @Resource
    private XuankeInfoService xuankeInfoService;
    @PutMapping
    public  Result kaike(@RequestBody XuankeInfo xuankeInfo){
        xuankeInfoService.kaike(xuankeInfo);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody XuankeInfo xuankeInfo) {
        xuankeInfoService.add(xuankeInfo);
        return Result.success();

    }
    @GetMapping
    public Result findAll(HttpServletRequest httpServletRequest) {
        List<XuankeInfo> list = xuankeInfoService.findAll(httpServletRequest);
        return Result.success(list);
    }

//    @PutMapping
//    public Result update(@RequestBody XuankeInfo xuankeInfo) {
//        xuankeInfoService.update(xuankeInfo);
//        return Result.success();
//    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        xuankeInfoService.deleteById(id);
        return Result.success();
    }
//    @GetMapping("/{search}")
//    public Result find(@PathVariable String search){
//        List<XuankeInfo> list = xuankeInfoService.find(search);
//        return Result.success(list);
//    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,HttpServletRequest httpServletRequest) {
        PageInfo<XuankeInfo> info = xuankeInfoService.findPage(pageNum, pageSize,httpServletRequest);
        return Result.success(info);
    }

    @GetMapping("/page/{name}")
    public Result findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @PathVariable String name,HttpServletRequest httpServletRequest) {
        PageInfo<XuankeInfo> info = xuankeInfoService .findPageName(pageNum, pageSize, name,httpServletRequest);
        return Result.success(info);
    }


}
