package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.service.AdminInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
public class AccountController {
    @Resource
    private AdminInfoService adminInfoService;
    @PostMapping("/login")
    public Result login(@RequestBody Account user, HttpServletRequest request) {
        //校验数据有没有填
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }
        Integer level= user.getLevel();
        Account loginUser=new Account();
        if (1==level){
            //管理员登录
            loginUser=adminInfoService.login(user.getName(),user.getPassword());
        }
        if (2==level){
            //老师登录

        }
        if (3==level){
            //学生登录

        }
        //session里面把用户信息存一份
        request.getSession().setAttribute("user",loginUser);
        return Result.success(loginUser);
    }
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request){
//先从session获取当前存的登录信息
        Account user= (Account) request.getSession().getAttribute("user");
        //判断登录的是什么角色
        Integer level = user.getLevel();

        if (1==level){
            //获取管理员信息
            AdminInfo adminInfo =adminInfoService.findById(user.getId());
            return Result.success(adminInfo);
        }
        if (2==level){
            //获取老师信息

        }
        if (3==level){
            //获取学生信息

        }
        return Result.success(new Account());
    }
}
