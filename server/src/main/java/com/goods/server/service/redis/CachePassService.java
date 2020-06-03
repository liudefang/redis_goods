package com.goods.server.service.redis;

//导入包
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goods.model.entity.GoodsItem;
import com.goods.model.mapper.GoodsItemMapper;
import com.google.common.base.Strings;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Time    : 2020/6/2 12:29 下午
 * Author  : mike.liu
 * File    : CachePassService.java
 */
public class CachePassService {
    private static final Logger log = LoggerFactory.getLogger(CachePassService.class);

    // 定义Mapper
    @Autowired
    private GoodsItemMapper goodsItemMapper;
    //定义redis操作组件RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
    //定义Json序列化与反序列化框架类
    @Autowired
    private ObjectMapper objectMapper;
    // 定义缓存中key命名的前缀
    private static final String keyPrefix="good:";
    /**
     * 获取商品详情，如果缓存有，则从缓存中获取；
     * 如果没有，则从数据库查询，并将结果查询结果塞入缓存中
     * @return
     */
    public GoodsItem getGoodInfo(String itemCode) throws Exception{
        //定义商品对象
        GoodsItem good=null;
        //定义缓存中真正的key：由前缀和商品编码组成
        final String key = keyPrefix+itemCode;
        //定义Redis的操作组件ValueOperations
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if(redisTemplate.hasKey(key)){
            log.info("---获取商品详情-缓存中存在该商品---商品编号为：{} ",itemCode);

            //从缓存中查询该商品详情
            Object res = valueOperations.get(key);
            if(res!=null && !Strings.isNullOrEmpty(res.toString())){
                //如果可以找到该商品，则进程json反序列化解析
                good = objectMapper.readValue(res.toString(),GoodsItem.class);
            }
        }else {
            //在缓存中没有找到该商品
            log.info("---获取商品详情-缓存中不存在该商品-从数据库中查找---商品编号为：{} \",itemCode");
            //从数据库中获取该商品详情
            good=goodsItemMapper.selectByCode(itemCode);
            if(good!=null){
                //如果数据库中查找得到该商品，则将其序列化后写入缓冲中
                valueOperations.set(key,objectMapper.writeValueAsString(good));
            }else {
                //过期失效时间TTL设置为30分钟，实际情况根据实际业务决定
                valueOperations.set(key,"",30L, TimeUnit.MINUTES);

            }
        }
        return good;

    }

}
