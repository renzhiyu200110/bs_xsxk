package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.AdminInfo;
import com.example.entity.StudentInfo;
import com.example.entity.TeacherInfo;
import com.example.service.AdminInfoService;
import com.example.service.StudentInfoService;
import com.example.service.TeacherInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//根账号相关的
@RestController
@RequestMapping
public class AccountController {
    @Resource
    private AdminInfoService adminInfoService;
    @Resource
    private TeacherInfoService teacherInfoService;
    @Resource
    private  StudentInfoService studentInfoService;
    /***登录功能
     * @param user 登录用户信息
     * @param request request 请求
     * @return Result
     * */
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
            loginUser=teacherInfoService.login(user.getName(),user.getPassword());

        }
        if (3==level){
            //学生登录
            loginUser=studentInfoService.login(user.getName(),user.getPassword());

        }
        //session里面把用户信息存一份
        request.getSession().setAttribute("user",loginUser);
        return Result.success(loginUser);
    }

    @PostMapping("/register")
    public Result register(@RequestBody Account user,HttpServletRequest request){
        if (ObjectUtil.isEmpty(user.getName()) || ObjectUtil.isEmpty(user.getPassword()) || ObjectUtil.isEmpty(user.getLevel())) {
            return Result.error("-1", "请完善输入信息");
        }
        Integer level=user.getLevel();
        if (2==level){
            //教师注册
            TeacherInfo teacherInfo=new TeacherInfo();
            BeanUtils.copyProperties(user,teacherInfo);
            teacherInfoService.register(teacherInfo);
        }
        if (3==level){
            //学生注册
            StudentInfo studentInfo=new StudentInfo();
            BeanUtils.copyProperties(user,studentInfo);
            studentInfoService.register(studentInfo);
        }

        return Result.success();
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
            TeacherInfo teacherInfo =teacherInfoService.findById(user.getId());
//            teacherInfo.setPassword("");
            return Result.success(teacherInfo);

        }
        if (3==level){
            //获取学生信息
            StudentInfo studentInfo = studentInfoService.findById(user.getId());
            return Result.success(studentInfo);

        }
        return Result.success(new Account());
    }
    @GetMapping("/logout")
    public  Result logout(HttpServletRequest request){
//        Account user = (Account) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

}
