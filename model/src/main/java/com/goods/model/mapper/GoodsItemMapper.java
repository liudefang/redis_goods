package com.goods.model.mapper;

import com.goods.model.entity.GoodsItem;
import com.goods.model.entity.GoodsItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 定义动态sql执行所在的Mapper
public interface GoodsItemMapper {
    long countByExample(GoodsItemExample example);

    int deleteByExample(GoodsItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsItem record);

    int insertSelective(GoodsItem record);

    List<GoodsItem> selectByExample(GoodsItemExample example);

    GoodsItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsItem record, @Param("example") GoodsItemExample example);

    int updateByExample(@Param("record") GoodsItem record, @Param("example") GoodsItemExample example);

    int updateByPrimaryKeySelective(GoodsItem record);

    int updateByPrimaryKey(GoodsItem record);

    // 根据商品编码，查询商品详情
    GoodsItem selectByCode(@Param("code") String code);
}
