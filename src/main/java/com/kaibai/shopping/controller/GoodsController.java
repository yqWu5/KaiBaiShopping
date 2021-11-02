package com.kaibai.shopping.controller;

import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.service.ShoppingGoodsService;
import com.kaibai.shopping.vo.GoodsVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/goods/")
public class GoodsController {
    @Autowired
    ShoppingGoodsService shoppingGoodsService;

    //商品列表
    @PostMapping("getGoodsList.do")
    public ResponseResult getGoodsList(@RequestBody QueryInfoVo queryInfoVo) throws Exception {
        return shoppingGoodsService.getGoodsList(queryInfoVo);
    }

    //商品详情信息
    @PostMapping("getGoodsInfoById.do")
    public ResponseResult getGoodsInfo(@RequestBody GoodsVo goodsVo) throws Exception {
        return shoppingGoodsService.getGoodsInfo(goodsVo);
    }

    //添加商品
    @PostMapping("addGoods.do")
    public ResponseResult addGoods(@RequestBody GoodsVo goodsVo) throws Exception {
        return shoppingGoodsService.addGoods(goodsVo);
    }

    //更新商品
    @PostMapping("updateGoods.do")
    public ResponseResult updateGoods(@RequestBody GoodsVo goodsVo) throws Exception {
        return shoppingGoodsService.updateGoods(goodsVo);
    }

    //删除商品
    @PostMapping("deleteGoods.do")
    public ResponseResult deleteGoods(@RequestBody GoodsVo goodsVo) throws Exception
    {
        return shoppingGoodsService.deleteGoods(goodsVo);
    }
}
