package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.mapper.ShoppingOrderDetailMapper;
import com.kaibai.shopping.pojo.ShoppingOrderDetail;
import com.kaibai.shopping.service.ShoppingOrderDetailService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingOrderDetailServiceImpl extends ServiceImpl<ShoppingOrderDetailMapper, ShoppingOrderDetail> implements ShoppingOrderDetailService {
}
