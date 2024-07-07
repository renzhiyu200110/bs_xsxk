package com.example.controller;

import com.example.common.Result;
import com.example.entity.AdminInfo;
import com.example.service.AdminInfoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/adminInfo")
public class AdminInfoController {
    @Resource
    private AdminInfoService adminInfoService;
    @PutMapping
    public Result update(@RequestBody AdminInfo adminInfo){
        adminInfoService.update(adminInfo);
        return Result.success();
    }
}
