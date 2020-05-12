package com.glisten.discount.shopping.Mapper;

import com.glisten.discount.shopping.Domain.TCommodityWares;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TCommodityWaresMapper extends MyMapper<TCommodityWares> {

    @Select("select * from t_commodity_wares where item_id =#{iid}  and wares_state>0")
    public List<TCommodityWares> findWaresByItemId(@Param("iid") long iid);

    @Select("select * from t_commodity_wares where category_id =#{cid} and wares_state>0")
    public List<TCommodityWares> findWaresByCategoryId(@Param("cid") long cid);

    @Select("select * from t_commodity_wares where type_id =#{tid} and wares_state>0 AND ( category_id  IN (SELECT MIN(category_id) FROM t_commodity_wares WHERE type_id =#{tid} AND wares_state>0 ) OR category_id IS NULL ) ")
    public List<TCommodityWares> findWaresByTypeId(@Param("tid") long cid);


    @Select("SELECT w.* FROM t_commodity_wares w left JOIN  t_commodity_type t  ON w.type_id=t.id " +
            " left JOIN t_commodity_category c ON c.id=w.category_id  " +
            " left JOIN t_commodity_item i ON i.id=w.item_id " +
            "  WHERE   w.wares_name LIKE CONCAT('%',#{key},'%') OR  w.wares_remarks LIKE CONCAT('%',#{key},'%') OR   t.type_name LIKE CONCAT('%',#{key},'%')  OR c.category_name LIKE CONCAT('%',#{key},'%') OR i.item_name LIKE CONCAT('%',#{key},'%')  and w.wares_state>0 order by w.id desc")
    public List<TCommodityWares> findWaresByKeyName(@Param("key") String key);

    @Select("select * from t_commodity_wares where item_id=#{w.itemId} and wares_state>0 and id!=#{w.id} ORDER BY RAND() LIMIT 2" )
    public List<TCommodityWares> findWaresByRandom(@Param("w") TCommodityWares w );
}
