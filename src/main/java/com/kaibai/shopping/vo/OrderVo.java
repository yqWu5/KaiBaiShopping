package com.kaibai.shopping.vo;

import lombok.Data;

//前端接收
@Data
public class OrderVo
{
    private String id;

    private Float orderprice;

    private String orderstatus;

    private String orderstatusdes;

    private String ordercreatetime;

    private String orderdestination;
}
