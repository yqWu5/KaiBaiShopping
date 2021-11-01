package com.kaibai.shopping.controller;

import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.service.ShoppingGoodsTypeService;
import com.kaibai.shopping.vo.GoodsTypeVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/goodsType/")
public class GoodsTypeController {
    @Autowired
    private ShoppingGoodsTypeService shoppingGoodsTypeService;

    //商品类别列表
    @PostMapping("getGoodsType.do")
    public ResponseResult getGoodsTypeList(@RequestBody QueryInfoVo queryInfoVo) throws Exception {
        return shoppingGoodsTypeService.getGoodsTypeList(queryInfoVo);
    }

    //新增商品类别
    @PostMapping("addGoodsType.do")
    public ResponseResult addGoodsType(@RequestBody GoodsTypeVo goodsTypeVo) throws Exception {
        return shoppingGoodsTypeService.addGoodSType(goodsTypeVo);
    }

    //删除商品类别
    @PostMapping("deleteGoodsType.do")
    public ResponseResult deleteGoodsType(@RequestBody GoodsTypeVo goodsTypeVo) throws Exception {
        return shoppingGoodsTypeService.deleteGoodsType(goodsTypeVo);
    }

    //更新商品类别
    @PostMapping("updateGoodsType.do")
    public ResponseResult updateGoodsType(@RequestBody GoodsTypeVo goodsTypeVo) throws Exception {
        return shoppingGoodsTypeService.updateGoodType(goodsTypeVo);
    }
}
