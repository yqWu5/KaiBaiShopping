package com.kaibai.shopping.vo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodsTypeVo
{
    private String id;

    private String typename;

    private Boolean status;

    private Date maketime;

    private Date modifytime;

    private String operator;
}
