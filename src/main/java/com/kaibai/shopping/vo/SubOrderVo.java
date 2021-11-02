package com.kaibai.shopping.vo;

import lombok.Data;

/**
 * @Auther: zhengpeng
 * @Date: 2019/12/14 15:42
 * @Description:
 */
@Data
public class SubOrderVo
{
    private String id;

    private String orderid;

    private String goodsid;

    private Integer goodscount;

    private String goodsname;

    private Float goodssumprice;
}
