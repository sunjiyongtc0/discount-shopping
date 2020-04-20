package com.glisten.discount.shopping.Mapper;

import com.glisten.discount.shopping.Domain.TCommodityWares;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TCommodityWaresMapper extends MyMapper<TCommodityWares> {

    @Select("select * from t_commodity_wares where item_id =#{iid} ")
    public List<TCommodityWares> findWaresByItemId(@Param("iid") long iid);

    @Select("select * from t_commodity_wares where category_id =#{cid} ")
    public List<TCommodityWares> findWaresByCategoryId(@Param("cid") long cid);

}
