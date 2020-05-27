package com.goods.model.mapper;

import com.goods.model.entity.GoodsItem;
import com.goods.model.entity.GoodsItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}