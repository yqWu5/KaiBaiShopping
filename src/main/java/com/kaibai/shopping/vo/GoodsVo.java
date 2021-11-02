package com.kaibai.shopping.vo;

import lombok.Data;

@Data
public class GoodsVo
{
    private String id;

    private String goodsname;

    private Float goodsprice;

    private String goodstypeid;

    private String goodstype;

    private Integer goodsamount;

    private Boolean goodsstatus;

    private String maketime;
}
