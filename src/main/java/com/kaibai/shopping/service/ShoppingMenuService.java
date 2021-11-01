package com.kaibai.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.pojo.ShoppingMenu;

public interface ShoppingMenuService extends IService<ShoppingMenu> {
    ResponseResult getMenusInfo() throws Exception;
}
