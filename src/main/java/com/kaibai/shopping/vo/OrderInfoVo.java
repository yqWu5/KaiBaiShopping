package com.kaibai.shopping.vo;

import lombok.Data;

import java.util.List;



@Data
public class OrderInfoVo
{
    private OrderVo orderVo;

    private List<SubOrderVo> subOrderVoList;

    private List<OrderTranportVo> orderTranportVoList;
}
