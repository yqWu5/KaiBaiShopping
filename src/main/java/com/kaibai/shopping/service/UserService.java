package com.kaibai.shopping.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.pojo.KaiBaiUser;
import com.kaibai.shopping.vo.QueryInfoVo;
import com.kaibai.shopping.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;


public interface UserService extends IService<KaiBaiUser> {
    ResponseResult login(String username , String password) throws Exception;

    ResponseResult getUsersInfo(QueryInfoVo queryInfoVo) throws  Exception;

    ResponseResult updateUser(UserInfoVo userInfoVo) throws Exception;

    ResponseResult getUserInfoById(UserInfoVo userInfoVo) throws Exception;

    ResponseResult addUser(UserInfoVo userInfoVo) throws Exception;

    ResponseResult deleteUser(String id) throws  Exception;

    ResponseResult queryUserInfo(QueryInfoVo queryInfoVo) throws Exception;
}
