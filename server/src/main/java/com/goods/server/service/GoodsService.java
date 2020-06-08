package com.goods.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goods.model.entity.GoodsItem;
import com.goods.model.mapper.GoodsItemMapper;
import com.goods.server.entity.Goods;
import com.goods.server.service.redis.CachePassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Time    : 2020/6/8 5:29 下午
 * Author  : mike.liu
 * File    : GoodsService.java
 */
//缓存穿透service
@Service
public class  GoodsService{
    private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

    // 定义Mapper
    @Autowired
    private GoodsItemMapper goodsItemMapper;
    //定义redis操作组件RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
    //定义Json序列化与反序列化框架类
    @Autowired
    private ObjectMapper objectMapper;

    public GoodsItem findGoodInfo(String itemCode){
        GoodsItem good = (GoodsItem) redisTemplate.boundValueOps(itemCode).get();
        if (redisTemplate.hasKey(itemCode)){
            return good;
        }else {
            //没有key,在数据库中查询
            GoodsItem one = goodsItemMapper.selectByCode(itemCode);
            log.info("第一次查询数据库", one);
            redisTemplate.boundValueOps(itemCode).set(one);
            if (one == null){
                redisTemplate.expire(itemCode, 30, TimeUnit.SECONDS);
            }
            return one;
        }
    }
}
