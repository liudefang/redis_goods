package com.goods.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Time    : 2020/6/3 12:24 下午
 * Author  : mike.liu
 * File    : Goods.java
 */
@Data
public class Goods implements Serializable {

    private Integer goodsNo;
    private String goodsName;

    public Goods(){}
    public Goods(Integer goodsNo, String goodsName){
        this.goodsNo = goodsNo;
        this.goodsName = goodsName;
    }

    public void setGoodsNo(Integer goodsNo) {
        this.goodsNo = goodsNo;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
