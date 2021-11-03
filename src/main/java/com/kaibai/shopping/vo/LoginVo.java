package com.kaibai.shopping.vo;

import lombok.Data;

//前端接收
@Data
public class LoginVo
{
    private String username;
    private String password;
    private String token;
}
