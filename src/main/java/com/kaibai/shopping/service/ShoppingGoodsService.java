package com.kaibai.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.pojo.ShoppingGoods;
import com.kaibai.shopping.vo.GoodsVo;
import com.kaibai.shopping.vo.QueryInfoVo;

public interface ShoppingGoodsService extends IService<ShoppingGoods> {
    ResponseResult getGoodsList(QueryInfoVo queryInfoVo) throws Exception;

    ResponseResult getGoodsInfo(GoodsVo goodsVo) throws Exception;

    ResponseResult addGoods(GoodsVo goodsVo)throws Exception;

    ResponseResult updateGoods(GoodsVo goodsVo) throws Exception;

    ResponseResult deleteGoods(GoodsVo goodsVo) throws Exception;
}
