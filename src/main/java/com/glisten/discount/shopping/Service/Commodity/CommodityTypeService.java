package com.glisten.discount.shopping.Service.Commodity;


import com.glisten.discount.shopping.Domain.TCommodityCategory;
import com.glisten.discount.shopping.Domain.TCommodityType;
import com.glisten.discount.shopping.Mapper.TCommodityCategoryMapper;
import com.glisten.discount.shopping.Mapper.TCommodityTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommodityTypeService {

    @Autowired
    private TCommodityTypeMapper commodityTypeMapper;
    @Autowired
    private TCommodityCategoryMapper commodityCategoryMapper;

    // type类操作

    public List<Map<String,Object>> findAllType(){
        return commodityTypeMapper.findAllType();
    }

    public  int  saveType(TCommodityType type){
         return commodityTypeMapper.insert(type);
    }

    public  int updateType(TCommodityType type){
        return commodityTypeMapper.updateByPrimaryKey(type);
    }

    public  int delType(long id){
        return commodityTypeMapper.deleteByPrimaryKey(id);
    }

    //Category类操作

    public List<Map<String,Object>> findByType(Long tid){
        return commodityCategoryMapper.findCategory(tid);
    }
    public List<Map<String,Object>> findAllCategory(){
        return commodityCategoryMapper.findAllCategory();
    }

    public int AddCategory(TCommodityCategory cc ,String name){
       Map<String,Object> m= commodityTypeMapper.findOneByTypeName(name);
       cc.setTypeId(Long.valueOf(m.get("id")+""));
       return  commodityCategoryMapper.insert(cc);
    }

    public int updateCategory(TCommodityCategory cc ,String name){
        Map<String,Object> m= commodityTypeMapper.findOneByTypeName(name);
        cc.setTypeId(Long.valueOf(m.get("id")+""));
        return  commodityCategoryMapper.updateByPrimaryKey(cc);
    }

    public int delCategory(long id){
        return  commodityCategoryMapper.deleteByPrimaryKey(id);
    }
}
