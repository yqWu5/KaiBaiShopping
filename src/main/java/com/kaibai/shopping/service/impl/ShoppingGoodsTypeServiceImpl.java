package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.IdGenerater;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.ShoppingGoodsTypeMapper;
import com.kaibai.shopping.pojo.ShoppingGoodsType;
import com.kaibai.shopping.service.ShoppingGoodsTypeService;
import com.kaibai.shopping.vo.GoodsTypeVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingGoodsTypeServiceImpl extends ServiceImpl<ShoppingGoodsTypeMapper, ShoppingGoodsType> implements ShoppingGoodsTypeService {

    @Override
    public ResponseResult getGoodsTypeList(QueryInfoVo queryInfoVo) throws Exception {
        //条件查询
        Page<ShoppingGoodsType> page = new Page<>(queryInfoVo.getPageNum(), queryInfoVo.getPageSize());

        Page<ShoppingGoodsType> shoppingGoodsTypePage = baseMapper.selectPage(page, null);
        List<ShoppingGoodsType> list = shoppingGoodsTypePage.getRecords();
        long total = shoppingGoodsTypePage.getTotal();

        List<GoodsTypeVo> goodsTypeVoList = new ArrayList<>();
        if(null != list) {
            for(ShoppingGoodsType type : list) {
                GoodsTypeVo goodsTypeVo = new GoodsTypeVo();
                goodsTypeVo.setId(type.getId());
                goodsTypeVo.setTypename(type.getTypename());
                goodsTypeVo.setStatus(type.getStatus());

                goodsTypeVoList.add(goodsTypeVo);
            }
        }

        return new ResponseResult("200", total, goodsTypeVoList);
    }

    @Override
    public ResponseResult addGoodSType(GoodsTypeVo goodsTypeVo) throws Exception {
        ShoppingGoodsType shoppingGoodsType = new ShoppingGoodsType();

        shoppingGoodsType.setId(IdGenerater.getId());
        shoppingGoodsType.setTypename(goodsTypeVo.getTypename());
        shoppingGoodsType.setStatus(true);
        shoppingGoodsType.setMaketime(new Date());
        shoppingGoodsType.setOperator("system");

        int count = baseMapper.insert(shoppingGoodsType);

        if(count == 1) {
            return new ResponseResult("200","新增商品种类成功！");
        } else {
            return new ResponseResult("500","新增商品种类失败！");
        }

    }

    @Override
    public ResponseResult deleteGoodsType(GoodsTypeVo goodsTypeVo) throws Exception {
        String id = goodsTypeVo.getId();

        int count = baseMapper.deleteById(id);

        if(count == 1) {
            return new ResponseResult("200","删除商品种类成功！");
        } else {
            return new ResponseResult("500","删除商品种类失败");
        }

    }

    @Override
    public ResponseResult updateGoodType(GoodsTypeVo goodsTypeVo) throws Exception {
        String id = goodsTypeVo.getId();
        //查询旧类别信息
        ShoppingGoodsType oldGoodsType = baseMapper.selectById(id);
        if(null == oldGoodsType) {
            return new ResponseResult("500","没有找到对应的商品种类，请刷新后重试！");
        }else {
            ShoppingGoodsType newGoodsType = new ShoppingGoodsType();
            newGoodsType.setId(id);
            if(StringUtils.isEmpty(goodsTypeVo.getTypename())) { //如果新类别名字为空
                newGoodsType.setTypename(oldGoodsType.getTypename());
            }else {
                newGoodsType.setTypename(goodsTypeVo.getTypename());
            }

            if(goodsTypeVo.getStatus() != null) { //如果新状态为空
                newGoodsType.setStatus(goodsTypeVo.getStatus());
            }else {
                newGoodsType.setStatus(oldGoodsType.getStatus());
            }

            newGoodsType.setMaketime(oldGoodsType.getMaketime());
            newGoodsType.setModifytime(new Date());
            newGoodsType.setOperator("system");
            int count = baseMapper.updateById(newGoodsType);
            if(count == 1) {
                return new ResponseResult("200","更新商品类别成功！");
            } else {
                return new ResponseResult("500","更新商品类别失败！");
            }
        }
    }
}
