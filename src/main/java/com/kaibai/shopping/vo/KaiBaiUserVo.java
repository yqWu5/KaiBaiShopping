package com.kaibai.shopping.vo;

import lombok.Data;

@Data
public class KaiBaiUserVo
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
