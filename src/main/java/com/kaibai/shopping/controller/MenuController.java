package com.kaibai.shopping.controller;

import com.kaibai.shopping.common.ResponseResult;
import com.kaibai.shopping.service.ShoppingMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/menu/")
public class MenuController {
    @Autowired
    ShoppingMenuService shoppingMenuService;

    @PostMapping("get_menus.do")
    private ResponseResult getMenus() throws Exception {
        return shoppingMenuService.getMenusInfo();
    }
}
