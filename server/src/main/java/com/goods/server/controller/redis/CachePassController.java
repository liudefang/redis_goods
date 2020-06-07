package com.goods.server.controller.redis;

import com.goods.server.service.redis.CachePassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Time    : 2020/6/2 9:55 上午
 * Author  : mike.liu
 * File    : CachePassController.java
 */

// 缓存穿透实战controller
@RestController
public class CachePassController {
    private static final Logger log = LoggerFactory.getLogger(CachePassController.class);
    private static final String prefix="cache";
    //定义缓存穿透处理服务类
    @Autowired
    private CachePassService cachePassService;
    /**
     * 获取热销商品信息
     * @param itemCode
     * @return
     */
    @RequestMapping(value = prefix+"/goods/info",method = RequestMethod.GET)
    public Map<String,Object> getGoods(@RequestParam String itemCode){
        //定义接口返回的格式，主要包括code，msg和data
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("code", 0);
        resMap.put("msg","成功");
        try {
            //调用缓存穿透处理服务类得到返回结果，并将其添加进结果Map中
            resMap.put("data",cachePassService.getGoodInfo(itemCode));
        }catch (Exception e){
            resMap.put("code", -1);
            resMap.put("msg","失败" + e.getMessage());
        }
        return resMap;
    }


}
