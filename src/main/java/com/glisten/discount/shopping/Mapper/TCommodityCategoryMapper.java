package com.glisten.discount.shopping.Mapper;

import com.glisten.discount.shopping.Domain.TCommodityCategory;
import com.glisten.discount.shopping.Domain.TCommodityType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TCommodityCategoryMapper extends MyMapper<TCommodityCategory> {

    @Select(" SELECT c.id,c.category_name AS categoryName,c.type_id AS typeId ,c.category_order AS categoryOrder , t.type_name AS typeName  FROM t_commodity_category  c  INNER JOIN t_commodity_type t  ON c.type_id=t.id ORDER BY  t.type_order,c.category_order  ")
    public List<Map<String,Object>> findAllCategory();


    @Select(" SELECT c.id,c.category_name AS categoryName,c.type_id AS typeId ,c.category_order AS categoryOrder , t.type_name AS typeName  FROM t_commodity_category  c  INNER JOIN t_commodity_type t  ON c.type_id=t.id  where c.type_id=#{tid}   ORDER BY  t.type_order,c.category_order ")
    public List<Map<String,Object>> findCategory(@Param("tid") Long tid);

//    @Select(" SELECT i.id,i.item_name AS itemName,c.category_name AS categoryName ,t.type_name AS typeName ,i.item_order AS itemOrder,i.category_id AS categoryId  FROM  t_commodity_item i INNER JOIN  t_commodity_category c ON i.category_id =c.id INNER JOIN t_commodity_type t ON t.id= c.type_id ORDER BY t.type_order,c.category_order,i.item_order ")
//    public List<Map<String,Object>> findAllItem();

}
