package com.glisten.discount.shopping.Mapper;

import com.glisten.discount.shopping.Domain.TCommodityType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TCommodityTypeMapper extends MyMapper<TCommodityType> {


    @Select(" select id,type_name as typeName,type_order as typeOrder from t_commodity_type order by type_order")
    public List<Map<String,Object>> findAllType();

    @Select("select id,type_name as typeName,type_order as typeOrder  from t_commodity_type where type_name=#{name}")
    public  Map<String,Object>  findOneByTypeName(@Param("name") String name );
}
