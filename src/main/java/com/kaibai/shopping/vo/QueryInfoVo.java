package com.kaibai.shopping.vo;

import lombok.Data;

/**
 * @Auther: zhengpeng
 * @Date: 2019/11/22 17:50
 * @Description:
 */
//分页查询
@Data
public class QueryInfoVo
{
    private int pageNum  ; //当前显示多少页
    private int pageSize ; //每页显示多少条
    private String query; //搜索关键字
    private int sumCount; //总条数
    private int total; //总页数


}
