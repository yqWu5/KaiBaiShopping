package com.kaibai.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.mapper.ShoppingMenuMapper;
import com.kaibai.shopping.pojo.ShoppingMenu;
import com.kaibai.shopping.service.ShoppingMenuService;
import com.kaibai.shopping.vo.MenuInfoVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingMenuServiceImpl extends ServiceImpl<ShoppingMenuMapper, ShoppingMenu> implements ShoppingMenuService {

    @Override
    public ResponseResult getMenusInfo() throws Exception {

        List<MenuInfoVo> menuInfoVoList = new ArrayList<>();

        //一级菜单
        List<ShoppingMenu> shoppingMenuList = baseMapper.selectList(new QueryWrapper<ShoppingMenu>().eq("status", "1"));
        for(ShoppingMenu menu : shoppingMenuList) {
            if("1".equals(menu.getLevel())) {
                MenuInfoVo menuInfoVo = new MenuInfoVo();
                menuInfoVo.setId(menu.getId());
                menuInfoVo.setAuthName(menu.getAuthname());
                menuInfoVo.setPath(menu.getPath());

                menuInfoVoList.add(menuInfoVo);
            }

        }

        //二级菜单
        for(MenuInfoVo vo : menuInfoVoList) {
            for(ShoppingMenu menu : shoppingMenuList) {
                if("2".equals(menu.getLevel()) && menu.getParentid().equals(vo.getId())) {
                    MenuInfoVo subMenuInfoVo = new MenuInfoVo();
                    subMenuInfoVo.setId(menu.getId());
                    subMenuInfoVo.setAuthName(menu.getAuthname());
                    subMenuInfoVo.setPath(menu.getPath());
                    vo.getChildren().add(subMenuInfoVo);
                }
            }
        }
        return new ResponseResult("200",menuInfoVoList);
    }
}
