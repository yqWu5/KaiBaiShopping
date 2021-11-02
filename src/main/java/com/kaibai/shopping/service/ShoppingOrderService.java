package com.kaibai.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.pojo.ShoppingOrder;
import com.kaibai.shopping.vo.OrderVo;
import com.kaibai.shopping.vo.QueryInfoVo;

public interface ShoppingOrderService extends IService<ShoppingOrder> {

    ResponseResult getOrderList(QueryInfoVo queryInfoVo) throws Exception;

    ResponseResult getOrderDetailInfo(OrderVo orderVo) throws Exception;

    ResponseResult updateOrderStatus(OrderVo orderVo) throws Exception;
}
