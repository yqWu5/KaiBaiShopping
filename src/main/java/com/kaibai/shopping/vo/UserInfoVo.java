package com.kaibai.shopping.vo;

import lombok.Data;

/**
 * @Auther: zhengpeng
 * @Date: 2019/11/22 13:46
 * @Description:
 */
@Data
public class UserInfoVo
{
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private String roleDescription;
    private String status;
    private String statusDescription;

}
