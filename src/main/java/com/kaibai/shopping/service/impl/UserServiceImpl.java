package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.IdGenerater;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.UserMapper;
import com.kaibai.shopping.pojo.KaiBaiUser;
import com.kaibai.shopping.pojo.ShoppingCode;
import com.kaibai.shopping.service.ShoppingCodeService;
import com.kaibai.shopping.service.UserService;
import com.kaibai.shopping.uitl.GetUserName;
import com.kaibai.shopping.uitl.RedisUtil;
import com.kaibai.shopping.vo.LoginVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import com.kaibai.shopping.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, KaiBaiUser> implements UserService {

    @Autowired
    ShoppingCodeService shoppingCodeService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public ResponseResult login(String username, String password) throws Exception {
        QueryWrapper<KaiBaiUser> queryWrapper = new QueryWrapper();

        //根据账号查询密码
        KaiBaiUser user = baseMapper.selectOne(queryWrapper.like("username", username));

        if(null != user && user.getPassword().equals(password)) {
            //账号被禁用则无法登录
            if("2".equals(user.getStatus())) {
                return new ResponseResult("300", "账号被禁用，请联系管理员解封");
            }

            LoginVo loginVo = new LoginVo();

            String token = IdGenerater.getId() + "-" + user.getUsername() + user.getRole();
            //redis存放token - 2小时
            redisUtil.set(user.getUsername(), token, 120l, TimeUnit.MINUTES);

            loginVo.setUsername(user.getUsername());
            loginVo.setPassword(user.getPassword());
            loginVo.setToken(token);
            return new ResponseResult("200", loginVo);
        }else {
            return new ResponseResult("300", "用户名或密码不存在");
        }

    }

    @Override
    public ResponseResult getUsersInfo(QueryInfoVo queryInfoVo) throws Exception {

        //获得角色名称
        List<ShoppingCode> shoppingCodes = shoppingCodeService.list(new QueryWrapper<ShoppingCode>().eq("codetype", "userrole"));

        //分页查询
        IPage<KaiBaiUser> UserPage = baseMapper.selectPage(new Page<>(queryInfoVo.getPageNum(), queryInfoVo.getPageSize()), null);
        //得到总数
        long count = UserPage.getTotal();
        //数据封装给前端页面
        List<UserInfoVo> userInfoVoList = new ArrayList<>();

        for(KaiBaiUser user : UserPage.getRecords()) {
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setId(user.getId());
            userInfoVo.setUsername(user.getUsername());
            userInfoVo.setEmail(user.getEmail());
            userInfoVo.setPhone(user.getPhone());
            userInfoVo.setRole(user.getRole());
            for(ShoppingCode code : shoppingCodes) {
                if(user.getRole().equals(code.getCodename())) { //如果当前user的角色名等于角色列表中的名称则为vo实体类添加role代码，用于前端展示
                    userInfoVo.setRoleDescription(code.getCodevlaue());
                }
            }

            userInfoVo.setStatus(user.getStatus());
            userInfoVoList.add(userInfoVo);
        }

        return new ResponseResult("200", count, userInfoVoList);
    }

    @Override
    public ResponseResult updateUser(UserInfoVo userInfoVo) throws Exception {
        String id = userInfoVo.getId();
        KaiBaiUser oldUser = baseMapper.selectById(id);

        if(null == oldUser) {
            return new ResponseResult("500","用户不存在，更新失败");
        }else {

            KaiBaiUser newUser = new KaiBaiUser();
            newUser.setId(id);

            //判断用户名
            if(StringUtils.isEmpty(userInfoVo.getUsername())) { //如果用户名为空
                newUser.setUsername(oldUser.getUsername());
            }else if(!oldUser.getUsername().trim().equals(userInfoVo.getUsername().trim())) { //如果新username != 旧username，则判断用户名是否与数据库中有重复的
                List<KaiBaiUser> usernameList = baseMapper.selectList(new QueryWrapper<KaiBaiUser>().eq("username", userInfoVo.getUsername()));
                if(null != usernameList & usernameList.size() >= 1) {
                    return new ResponseResult("500","该用户名已存在，请更改用户名!");
                }
                newUser.setUsername(userInfoVo.getUsername());
            }else {
                newUser.setUsername(userInfoVo.getUsername());
            }

            //判断密码
            if(StringUtils.isEmpty(userInfoVo.getPassword())) {
                newUser.setPassword(oldUser.getPassword());
            }else {
                newUser.setPassword(userInfoVo.getPassword());
            }

            //判断手机号
            if(StringUtils.isEmpty(userInfoVo.getPhone())) {
                newUser.setPhone(oldUser.getPhone());
            }else {
                newUser.setPhone(userInfoVo.getPhone());
            }

            //判断邮箱
            if(StringUtils.isEmpty(userInfoVo.getEmail())) {
                newUser.setEmail(oldUser.getEmail());
            }else {
                newUser.setEmail(userInfoVo.getEmail());
            }

            //判断角色
            if(StringUtils.isEmpty(userInfoVo.getRole())) {
                newUser.setRole(oldUser.getRole());
            }else {
                newUser.setRole(userInfoVo.getRole());
            }

            newUser.setMaketime(oldUser.getMaketime());
            newUser.setStatus(userInfoVo.getStatus());
            newUser.setModifytime(new Date());
            newUser.setOperator("system");

            baseMapper.updateById(newUser);

            return new ResponseResult("200","更新用户信息成功！");
        }
    }

    @Override
    public ResponseResult getUserInfoById(UserInfoVo userInfoVo) throws Exception {
        String id = userInfoVo.getId();
        UserInfoVo returnUserInfoVo = new UserInfoVo();
        KaiBaiUser kaiBaiUser = baseMapper.selectById(id);

        if(null == kaiBaiUser) {
            return new ResponseResult("500","查询用户信息失败！");
        }else {
            returnUserInfoVo.setId(kaiBaiUser.getId());
            returnUserInfoVo.setUsername(kaiBaiUser.getUsername());
            returnUserInfoVo.setPassword(kaiBaiUser.getPassword());
            returnUserInfoVo.setEmail(kaiBaiUser.getEmail());
            returnUserInfoVo.setPhone(kaiBaiUser.getPhone());
            returnUserInfoVo.setStatus(kaiBaiUser.getStatus());
            returnUserInfoVo.setRole(kaiBaiUser.getRole());
            return new ResponseResult("200",returnUserInfoVo);
        }
    }

    @Override
    public ResponseResult addUser(UserInfoVo userInfoVo) throws Exception {
        //获取前端数据
        String id = IdGenerater.getId();//工具生成唯一id
        String username = userInfoVo.getUsername();
        String password = userInfoVo.getPassword();
        String phone = userInfoVo.getPhone();
        String email = userInfoVo.getEmail();
        String role = userInfoVo.getRole();
        String status = userInfoVo.getStatus();

        //检查用户名不能重复
        List<KaiBaiUser> usernameList = baseMapper.selectList(new QueryWrapper<KaiBaiUser>().eq("username", userInfoVo.getUsername()));
        if(null != usernameList & usernameList.size() >= 1) {
            return new ResponseResult("500","此用户名已存在，请更改用户名");
        }

        //新增用户
        KaiBaiUser user = new KaiBaiUser();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        user.setStatus(status);
        user.setMaketime(new Date());
        user.setOperator("system");

        int count = baseMapper.insert(user);
        if(count == 1) {
            return new ResponseResult("200","新增用户成功!");
        }
        else {
            return  new ResponseResult("500","新增用户失败！");
        }

    }

    @Override
    public ResponseResult deleteUser(String id) throws Exception {
        int count = baseMapper.deleteById(id);
        if(count == 1) {
            return new ResponseResult("200","删除用户成功！");
        } else
        {
            return new ResponseResult("500","删除用户失败");
        }
    }

    @Override
    public ResponseResult queryUserInfo(QueryInfoVo queryInfoVo) throws Exception {
        //查找角色
        List<ShoppingCode> shoppingCodes = shoppingCodeService.list(new QueryWrapper<ShoppingCode>().eq("codetype", "userrole"));

        int pageNum = queryInfoVo.getPageNum();//当前是第几页
        int pageNo = queryInfoVo.getPageSize();//每页显示几条

        //根据关键词查找记录总数
        long sumCount = baseMapper.selectCount(new QueryWrapper<KaiBaiUser>().like("username", queryInfoVo.getQuery()));
        int count = pageNo;
        //分页查询
        Page<KaiBaiUser> page = new Page<>(pageNum, count);
        List<KaiBaiUser> userList = baseMapper.selectPage(page, new QueryWrapper<KaiBaiUser>().like("username", queryInfoVo.getQuery())).getRecords();

        List<UserInfoVo> userInfoVoList = new ArrayList<>();
        for(KaiBaiUser user : userList) {
            UserInfoVo userInfoVo = new UserInfoVo();
            userInfoVo.setId(user.getId());
            userInfoVo.setUsername(user.getUsername());
            userInfoVo.setEmail(user.getEmail());
            userInfoVo.setPhone(user.getPhone());
            userInfoVo.setRole(user.getRole());
            for(ShoppingCode code : shoppingCodes) {
                if(user.getRole().equals(code.getCodename())) {
                    userInfoVo.setRoleDescription(code.getCodevlaue());
                }
            }
            userInfoVo.setStatus(user.getStatus());
            userInfoVoList.add(userInfoVo);
        }
        return new ResponseResult("200",sumCount,userInfoVoList);
    }

}
