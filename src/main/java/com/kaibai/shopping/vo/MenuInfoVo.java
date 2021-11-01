package com.kaibai.shopping.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuInfoVo
{
    private String id;
    private String authName;
    private String path;
    private List<MenuInfoVo> children = new ArrayList<MenuInfoVo>();
}
