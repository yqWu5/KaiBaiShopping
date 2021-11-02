package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.ShoppingOrderMapper;
import com.kaibai.shopping.pojo.ShoppingCode;
import com.kaibai.shopping.pojo.ShoppingOrder;
import com.kaibai.shopping.pojo.ShoppingOrderDetail;
import com.kaibai.shopping.pojo.ShoppingTransportTrace;
import com.kaibai.shopping.service.ShoppingCodeService;
import com.kaibai.shopping.service.ShoppingOrderDetailService;
import com.kaibai.shopping.service.ShoppingOrderService;
import com.kaibai.shopping.service.ShoppingTransportTraceService;
import com.kaibai.shopping.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingOrderServiceImpl extends ServiceImpl<ShoppingOrderMapper, ShoppingOrder> implements ShoppingOrderService {

    @Autowired
    ShoppingOrderDetailService shoppingOrderDetailService;
    @Autowired
    ShoppingTransportTraceService shoppingTransportTraceService;
    @Autowired
    ShoppingCodeService shoppingCodeService;

    //无条件订单查询
    private List<OrderVo> queryShoppingOrder(int pageNum, int pageSize) {
        List<OrderVo> list = new ArrayList<>();
        Page<ShoppingOrder> shoppingOrderPage = baseMapper.selectPage(new Page<>(pageNum, pageSize), null);

        List<ShoppingOrder> shoppingOrderList = shoppingOrderPage.getRecords();
        if(null != shoppingOrderList) {
            for(ShoppingOrder order : shoppingOrderList) {
                list.add(transformOrderVo(order));
            }
        }

        return list;
    }

    //有条件订单查询
    private List<OrderVo> queryShoppingOrder(int pageNum, int pageSize,String query) {
        List<OrderVo> list = new ArrayList<>();
        //根据订单号查询
        ShoppingOrder shoppingOrder = baseMapper.selectById(query.trim());

        if(null != shoppingOrder) {
            list.add(transformOrderVo(shoppingOrder));
        }
        return list;
    }

    //ShoppingOrder实体类转OrderVo - 用于前端显示
    private OrderVo transformOrderVo(ShoppingOrder shoppingOrder)
    {
        OrderVo orderVo = new OrderVo();

        orderVo.setId(shoppingOrder.getId());//id
        orderVo.setOrderprice(shoppingOrder.getOrderprice());//价格
        orderVo.setOrderdestination(shoppingOrder.getOrderdestination());//目的地
        orderVo.setOrderstatus(shoppingOrder.getOrderstatus());//状态码
        orderVo.setOrderstatusdes(shoppingOrder.getOrderstatusdes());//状态中文
        orderVo.setOrdercreatetime(new SimpleDateFormat("yyyy-MM-dd").format(shoppingOrder.getOrdercreatetime()));

        return orderVo;
    }

    //订单列表
    @Override
    public ResponseResult getOrderList(QueryInfoVo queryInfoVo) throws Exception {
        int pageNum = queryInfoVo.getPageNum();
        int pageSize = queryInfoVo.getPageSize();
        //关键词
        String query = queryInfoVo.getQuery();

        List<OrderVo> orderVoList = new ArrayList<>();
        long total = 0;
        if(StringUtils.isEmpty(query)) {
            //无关键词查询
            total = count();
            orderVoList = queryShoppingOrder(pageNum,pageSize);
        }else {
            orderVoList = queryShoppingOrder(pageNum,pageSize,query);
            total = orderVoList.size();
        }
        return new ResponseResult("200", total, orderVoList);
    }

    //订单详情
    @Override
    public ResponseResult getOrderDetailInfo(OrderVo orderVo) throws Exception {
        String id = orderVo.getId();

        //订单详情前端类
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        //订单基本信息
        OrderVo newOrderVo = null;
        //订单详情
        List<SubOrderVo> subOrderVoList = new ArrayList<>();
        //订单物流信息
        List<OrderTranportVo> orderTranportVoList = new ArrayList<>();

        //获取订单总体信息
        ShoppingOrder shoppingOrder = baseMapper.selectById(id);
        newOrderVo = transformOrderVo(shoppingOrder);

        //获取子订单的信息 - orderDetail表
        List<ShoppingOrderDetail> shoppingOrderDetailList = new ArrayList<>();
        shoppingOrderDetailList = shoppingOrderDetailService.getBaseMapper().selectList(new QueryWrapper<ShoppingOrderDetail>().eq("orderid", id));
        if(null != shoppingOrderDetailList) {
            for(ShoppingOrderDetail detail : shoppingOrderDetailList) {
                subOrderVoList.add(transformSubOrderVo(detail));
            }
        }

        //获取物流信息
        List<ShoppingTransportTrace> shoppingTransportTraceList = new ArrayList<>();
        shoppingTransportTraceList = shoppingTransportTraceService.getBaseMapper().selectList(new QueryWrapper<ShoppingTransportTrace>().eq("orderid", id));
        if(null != shoppingTransportTraceList) {
            for(ShoppingTransportTrace trace : shoppingTransportTraceList) {
                orderTranportVoList.add(transformOrderTranportVo(trace));
            }
        }

        //组装数据
        orderInfoVo.setOrderVo(newOrderVo);
        orderInfoVo.setSubOrderVoList(subOrderVoList);
        orderInfoVo.setOrderTranportVoList(orderTranportVoList);

        return new ResponseResult("200", orderInfoVo);
    }

    //更改订单状态
    @Override
    public ResponseResult updateOrderStatus(OrderVo orderVo) throws Exception {
        String id = orderVo.getId();
        //新状态码
        String orderStatus = orderVo.getOrderstatus();
        //新状态中文
        String orderStatusDes = "";
        //之前的状态
        ShoppingOrder shoppingOrder = baseMapper.selectById(id);

        List<ShoppingCode> shoppingCodeList = shoppingCodeService.list(new QueryWrapper<ShoppingCode>().eq("codetype", "orderStatus"));
        for(ShoppingCode code : shoppingCodeList) {
            if(orderStatus.equals(code.getCodename())) {
                orderStatusDes = code.getCodevlaue();
                break;
            }
        }

        shoppingOrder.setOrderstatus(orderStatus);
        shoppingOrder.setOrderstatusdes(orderStatusDes);
        int count = baseMapper.updateById(shoppingOrder);
        if(count == 1) {
            return new ResponseResult("200","更新成功！");
        } else {
            return new ResponseResult("500","更新失败！");
        }

    }

    //ShoppingOrderDetail实体类转成前端实体类
    private SubOrderVo transformSubOrderVo(ShoppingOrderDetail shoppingOrderDetail) {
        SubOrderVo subOrderVo = new SubOrderVo();

        subOrderVo.setId(shoppingOrderDetail.getId());
        subOrderVo.setOrderid(shoppingOrderDetail.getOrderid());
        subOrderVo.setGoodsid(shoppingOrderDetail.getGoodsid());
        subOrderVo.setGoodsname(shoppingOrderDetail.getGoodsname());
        subOrderVo.setGoodscount(shoppingOrderDetail.getGoodscount());
        subOrderVo.setGoodssumprice(shoppingOrderDetail.getGoodssumprice());

        return subOrderVo;
    }

    //物流信息转换
    private OrderTranportVo transformOrderTranportVo(ShoppingTransportTrace shoppingTransportTrace) {
        OrderTranportVo orderTranportVo = new OrderTranportVo();

        orderTranportVo.setId(shoppingTransportTrace.getId());
        orderTranportVo.setOrderid(shoppingTransportTrace.getOrderid());
        orderTranportVo.setArrivetime(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(shoppingTransportTrace.getArrivetime()));
        orderTranportVo.setArrivedes(shoppingTransportTrace.getArrivedes());

        return orderTranportVo;
    }
}
