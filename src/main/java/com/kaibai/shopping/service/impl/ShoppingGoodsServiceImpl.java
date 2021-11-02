package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.ShoppingGoodsMapper;
import com.kaibai.shopping.pojo.ShoppingGoods;
import com.kaibai.shopping.pojo.ShoppingGoodsType;
import com.kaibai.shopping.service.ShoppingGoodsService;
import com.kaibai.shopping.service.ShoppingGoodsTypeService;
import com.kaibai.shopping.vo.GoodsVo;
import com.kaibai.shopping.vo.QueryInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShoppingGoodsServiceImpl extends ServiceImpl<ShoppingGoodsMapper, ShoppingGoods> implements ShoppingGoodsService {

    @Autowired
    ShoppingGoodsTypeService shoppingGoodsTypeService;

    //商品列表
    @Override
    public ResponseResult getGoodsList(QueryInfoVo queryInfoVo) throws Exception {
        String query = queryInfoVo.getQuery();//搜索关键词
        int pageNum = queryInfoVo.getPageNum();
        int pageSize = queryInfoVo.getPageSize();

        //无条件查询
        if(StringUtils.isEmpty(query)) {
            Page<ShoppingGoods> shoppingGoodsPage = baseMapper.selectPage(new Page<ShoppingGoods>(pageNum, pageSize), null);

            List<ShoppingGoods> shoppingGoodsList = shoppingGoodsPage.getRecords();
            long sumCount = shoppingGoodsPage.getTotal();
            List<GoodsVo> goodsVoList = new ArrayList<>();
            if(null != shoppingGoodsList) {
                for(ShoppingGoods goods : shoppingGoodsList) {
                    GoodsVo goodsVo = new GoodsVo();

                    goodsVo.setId(goods.getId());
                    goodsVo.setGoodsname(goods.getGoodsname());
                    goodsVo.setGoodsprice(goods.getGoodsprice());
                    goodsVo.setGoodsamount(goods.getGoodsamount());
                    goodsVo.setGoodstypeid(goods.getGoodstypeid());
                    goodsVo.setGoodstype(goods.getGoodstype());
                    goodsVo.setGoodsamount(goods.getGoodsamount());
                    goodsVo.setMaketime(new SimpleDateFormat("yyyy-MM-dd").format(goods.getMaketime()));

                    goodsVoList.add(goodsVo);
                }
            }
            return new ResponseResult("200", sumCount, goodsVoList);
        }else {
            //有条件查询
            Page<ShoppingGoods> shoppingGoodsPage = baseMapper.
                    selectPage(new Page<ShoppingGoods>(pageNum, pageSize), new QueryWrapper<ShoppingGoods>().like("goodsname", query));

            List<ShoppingGoods> shoppingGoodsList = shoppingGoodsPage.getRecords();
            long sumCount = shoppingGoodsPage.getTotal();
            List<GoodsVo> goodsVoList = new ArrayList<>();
            if(null != shoppingGoodsList) {
                for(ShoppingGoods goods : shoppingGoodsList) {
                    GoodsVo goodsVo = new GoodsVo();

                    goodsVo.setId(goods.getId());
                    goodsVo.setGoodsname(goods.getGoodsname());
                    goodsVo.setGoodsprice(goods.getGoodsprice());
                    goodsVo.setGoodsamount(goods.getGoodsamount());
                    goodsVo.setGoodstypeid(goods.getGoodstypeid());
                    goodsVo.setGoodstype(goods.getGoodstype());
                    goodsVo.setGoodsamount(goods.getGoodsamount());
                    goodsVo.setMaketime(new SimpleDateFormat("yyyy-MM-dd").format(goods.getMaketime()));

                    goodsVoList.add(goodsVo);
                }
            }
            return new ResponseResult("200", sumCount, goodsVoList);
        }

    }

    //商品详情
    @Override
    public ResponseResult getGoodsInfo(GoodsVo goodsVo) throws Exception {
        String id = goodsVo.getId();
        ShoppingGoods shoppingGoods = baseMapper.selectById(id);

        GoodsVo returnGoodsVo = new GoodsVo();
        if(null != shoppingGoods) {
            returnGoodsVo.setId(shoppingGoods.getId());
            returnGoodsVo.setGoodsname(shoppingGoods.getGoodsname());
            returnGoodsVo.setGoodstype(shoppingGoods.getGoodstype());
            returnGoodsVo.setGoodsprice(shoppingGoods.getGoodsprice());
            returnGoodsVo.setGoodsamount(shoppingGoods.getGoodsamount());
            returnGoodsVo.setGoodstypeid(shoppingGoods.getGoodstypeid());
            returnGoodsVo.setGoodsstatus(shoppingGoods.getGoodsstatus());
            returnGoodsVo.setMaketime(new SimpleDateFormat("yyyy-MM-dd").format(shoppingGoods.getMaketime()));
        }

        return new ResponseResult("200", returnGoodsVo);
    }

    //添加商品
    @Override
    public ResponseResult addGoods(GoodsVo goodsVo) throws Exception {
        String id = goodsVo.getId();

        ShoppingGoods shoppingGoods = new ShoppingGoods();
        shoppingGoods.setId(id);
        shoppingGoods.setGoodsname(goodsVo.getGoodsname());
        shoppingGoods.setGoodstypeid(goodsVo.getGoodstypeid());
        ShoppingGoodsType shoppingGoodsType = shoppingGoodsTypeService.getBaseMapper().selectById(goodsVo.getGoodstypeid());
        shoppingGoods.setGoodstype(shoppingGoodsType.getTypename());
        shoppingGoods.setGoodsamount(goodsVo.getGoodsamount());
        shoppingGoods.setGoodsstatus(goodsVo.getGoodsstatus());
        shoppingGoods.setGoodsprice(goodsVo.getGoodsprice());
        shoppingGoods.setMaketime(new Date());
        shoppingGoods.setOperator("system");

        int result = baseMapper.insert(shoppingGoods);
        if(result == 1) {
            return new ResponseResult("200","新增商品成功！");
        } else
        {
            return new ResponseResult("500","新增商品失败！");
        }

    }

    //更新商品
    @Override
    public ResponseResult updateGoods(GoodsVo goodsVo) throws Exception {
        ShoppingGoods shoppingGoods = new ShoppingGoods();
        shoppingGoods.setId(goodsVo.getId());
        shoppingGoods.setGoodsname(goodsVo.getGoodsname());
        shoppingGoods.setGoodsprice(goodsVo.getGoodsprice());
        shoppingGoods.setGoodstypeid(goodsVo.getGoodstypeid());
        ShoppingGoodsType shoppingGoodsType = shoppingGoodsTypeService.getBaseMapper().selectById(goodsVo.getGoodstypeid());
        shoppingGoods.setGoodstype(shoppingGoodsType.getTypename());
        shoppingGoods.setGoodsstatus(goodsVo.getGoodsstatus());
        shoppingGoods.setGoodsamount(goodsVo.getGoodsamount());
        shoppingGoods.setModifytime(new Date());
        shoppingGoods.setOperator("system");

        int result = baseMapper.updateById(shoppingGoods);

        if(result == 1) {
            return new ResponseResult("200","更新商品成功！");
        } else {
            return new ResponseResult("500","更新商品失败！");
        }
    }

    //删除商品
    @Override
    public ResponseResult deleteGoods(GoodsVo goodsVo) throws Exception {
        String id = goodsVo.getId();

        int result = baseMapper.deleteById(id);

        if(result == 1) {
            return new ResponseResult("200","删除商品成功！");
        } else
        {
            return new ResponseResult("500","删除商品失败！");
        }
    }

}
