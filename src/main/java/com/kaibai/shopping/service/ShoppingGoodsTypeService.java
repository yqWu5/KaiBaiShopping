package com.kaibai.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.ShoppingGoodsTypeMapper;
import com.kaibai.shopping.pojo.ShoppingGoodsType;
import com.kaibai.shopping.vo.GoodsTypeVo;
import com.kaibai.shopping.vo.QueryInfoVo;

public interface ShoppingGoodsTypeService extends IService<ShoppingGoodsType> {
    ResponseResult getGoodsTypeList(QueryInfoVo queryInfoVo) throws Exception;

    ResponseResult addGoodSType(GoodsTypeVo goodsTypeVo) throws Exception;

    ResponseResult deleteGoodsType(GoodsTypeVo goodsTypeVo) throws Exception;

    ResponseResult updateGoodType(GoodsTypeVo goodsTypeVo) throws Exception;
}
