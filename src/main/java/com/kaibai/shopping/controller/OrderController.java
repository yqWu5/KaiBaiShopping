package com.kaibai.shopping.controller;

import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.service.ShoppingOrderService;
import com.kaibai.shopping.vo.OrderVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order/")
public class OrderController {
    @Autowired
    ShoppingOrderService shoppingOrderService;

    //订单列表
    @PostMapping("getOrderList.do")
    public ResponseResult getOrderList(@RequestBody QueryInfoVo queryInfoVo) throws Exception {
        return shoppingOrderService.getOrderList(queryInfoVo);
    }

    //订单详情与物流信息
    @PostMapping("getOrderDetail.do")
    public ResponseResult getOrderDetail(@RequestBody OrderVo orderVo) throws Exception {
        return shoppingOrderService.getOrderDetailInfo(orderVo);
    }

    //更新订单状态
    @PostMapping("updateOrderStatus.do")
    public ResponseResult updateOrderStatus(@RequestBody OrderVo orderVo) throws Exception {
        return shoppingOrderService.updateOrderStatus(orderVo);
    }
}
