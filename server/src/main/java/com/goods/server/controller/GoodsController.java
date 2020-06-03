package com.goods.server.controller;

import com.goods.server.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Time    : 2020/6/3 12:31 下午
 * Author  : mike.liu
 * File    : GoodsController.java
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Goods info(Integer goodsNo, String goodsName){
        Goods goods = new Goods();
        goods.setGoodsNo(goodsNo);
        goods.setGoodsName(goodsName);
        return goods;
    }

    @RequestMapping()
    public String index(){
        return "hello, server works fine";
    }

}
