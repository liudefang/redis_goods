package com.goods.server.controller;

import com.goods.server.entity.Goods;
import com.goods.server.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Time    : 2020/6/3 12:31 下午
 * Author  : mike.liu
 * File    : GoodsController.java
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Map<String,Object> getGoods(@RequestParam String itemCode){
        //定义接口返回的格式，主要包括code，msg和data
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("code", 0);
        resMap.put("msg","成功");
        try {
            //调用缓存穿透处理服务类得到返回结果，并将其添加进结果Map中
            resMap.put("data",goodsService.findGoodInfo(itemCode));
        }catch (Exception e){
            resMap.put("code", -1);
            resMap.put("msg","失败" + e.getMessage());
        }
        return resMap;
    }

    @RequestMapping()
    public String index(){
        return "hello, server works fine";
    }

}
