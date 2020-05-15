package com.glisten.discount.shopping.Service.Commodity;


import com.glisten.discount.shopping.Domain.TCommodityCategory;
import com.glisten.discount.shopping.Domain.TCommodityItem;
import com.glisten.discount.shopping.Domain.TCommodityType;
import com.glisten.discount.shopping.Domain.TCommodityWares;
import com.glisten.discount.shopping.Mapper.TCommodityCategoryMapper;
import com.glisten.discount.shopping.Mapper.TCommodityItemMapper;
import com.glisten.discount.shopping.Mapper.TCommodityTypeMapper;
import com.glisten.discount.shopping.Mapper.TCommodityWaresMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommodityService {

    @Autowired
    private TCommodityWaresMapper tCommodityWaresMapper ;
    public int SaveWares(TCommodityWares wares){
        return tCommodityWaresMapper.insert(wares);
    }

    public List<TCommodityWares> findWaresList(){
        return tCommodityWaresMapper.selectAll();
    }

    public  int updateWares(TCommodityWares wares){
        return  tCommodityWaresMapper.updateByPrimaryKey(wares);
    }

    public  int delWares(long id){
        return tCommodityWaresMapper.deleteByPrimaryKey(id);
    }

    //home页展示效果数据
     public  List<TCommodityWares>  findWaresByItemId(long id){
        return tCommodityWaresMapper.findWaresByItemId(id);
     }
     public  List<TCommodityWares>  findWaresByCategoryId(long id){
        return tCommodityWaresMapper.findWaresByCategoryId(id);
     }

    public  List<TCommodityWares>  findWaresByTypeId(long id){
        return tCommodityWaresMapper.findWaresByTypeId(id);
    }

    public  TCommodityWares  findWaresById(long id){
        return tCommodityWaresMapper.selectByPrimaryKey(id);
    }
    public  List<TCommodityWares>  findWaresByKeyName(String key){
        return tCommodityWaresMapper.findWaresByKeyName(key);
    }

    public List<TCommodityWares>  findWaresByRandom(TCommodityWares w){
        return tCommodityWaresMapper.findWaresByRandom(w);
    }
    public  List<TCommodityWares>  findWaresByactivityId(){
        return tCommodityWaresMapper.findWaresByactivityId();
    }



}
