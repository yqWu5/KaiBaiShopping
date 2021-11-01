package com.kaibai.shopping.controller;

import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.service.UserService;
import com.kaibai.shopping.vo.LoginVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import com.kaibai.shopping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@CrossOrigin
public class UserController {
    @Autowired
    UserService UserService;

    //登录
    @PostMapping("login.do")
    public ResponseResult login(@RequestBody LoginVo loginVo) throws Exception {
        return UserService.login(loginVo.getUsername(), loginVo.getPassword());
    }

    //用户信息
    @PostMapping("getUsersInfo.do")
    public ResponseResult getUsersInfo(@RequestBody QueryInfoVo queryInfoVo) throws Exception {
        return UserService.getUsersInfo(queryInfoVo);
    }

    //更新用户信息
    @PostMapping("updateUserInfo.do")
    public ResponseResult updateUsersInfo(@RequestBody UserInfoVo userInfoVo) throws Exception {
        return UserService.updateUser(userInfoVo);
    }

    //根据id获取用户信息 - 用于更新用户信息
    @PostMapping("getUserInfoById.do")
    public ResponseResult getUserInfoById(@RequestBody UserInfoVo userInfoVo) throws Exception {
        return UserService.getUserInfoById(userInfoVo);
    }

    //新增用户
    @PostMapping("addUser.do")
    public ResponseResult addUser(@RequestBody UserInfoVo userInfoVo) throws Exception {
        return UserService.addUser(userInfoVo);
    }

    //删除用户
    @PostMapping("deleteUser.do")
    public  ResponseResult deleteUser(@RequestBody UserInfoVo userInfoVo) throws Exception {
        return  UserService.deleteUser(userInfoVo.getId());
    }

    //条件查找
    @PostMapping("queryUser.do")
    public ResponseResult queryUser(@RequestBody QueryInfoVo queryInfoVo) throws Exception {
        return UserService.queryUserInfo(queryInfoVo);
    }
}
