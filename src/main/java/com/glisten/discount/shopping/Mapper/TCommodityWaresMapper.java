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


    @Select("SELECT w.* FROM t_commodity_wares w INNER JOIN  t_commodity_type t  ON w.type_id=t.id " +
            " INNER JOIN t_commodity_category c ON c.id=w.category_id  " +
            " INNER JOIN t_commodity_item i ON i.id=w.item_id " +
            "  WHERE   w.wares_name LIKE CONCAT('%',#{key},'%') OR  w.wares_remarks LIKE CONCAT('%',#{key},'%') OR   t.type_name LIKE CONCAT('%',#{key},'%')  OR c.category_name LIKE CONCAT('%',#{key},'%') OR i.item_name LIKE CONCAT('%',#{key},'%')")
    public List<TCommodityWares> findWaresByKeyName(@Param("key") String key);
}
