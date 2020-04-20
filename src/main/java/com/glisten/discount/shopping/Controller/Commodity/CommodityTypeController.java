package com.glisten.discount.shopping.Controller.Commodity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glisten.discount.shopping.Domain.TCommodityCategory;
import com.glisten.discount.shopping.Domain.TCommodityItem;
import com.glisten.discount.shopping.Domain.TCommodityType;
import com.glisten.discount.shopping.Service.Commodity.CommodityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/commType")
public class CommodityTypeController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private CommodityTypeService cts;


    //商品根类添加/修改
    @PostMapping("/typeAdd")
    @ResponseBody
    public String  typeAdd( @ModelAttribute TCommodityType type ){
        String msg="error";
        System.out.println(type.toString());
        if(type.getId()==null){
            int i= cts.saveType(type);
            if(i==1){
                msg="ok";
            }
        }else{
            int i=cts.updateType(type);
            if(i==1){
                msg="ok";
            }
        }
        return msg;
    }

    //商品根类列表
    @GetMapping("/typeList")
    @ResponseBody
    public JSONArray typeList(){
        List<Map<String,Object>> lt= cts.findAllType();
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(lt));
        return tableData;
    }

    //商品根类删除
    @GetMapping("/typedel/{id}")
    @ResponseBody
    public String typeDel(@PathVariable("id") long id ){
        cts.delType(id);
        return "ok";
    }


    //商品一级类列表
    @GetMapping("/categoryList")
    @ResponseBody
    public JSONArray categoryList(){
        List<Map<String,Object>> lc= cts.findAllCategory();
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(lc));
        return tableData;
    }

    //商品一级类添加/修改
    @PostMapping("/categoryAdd")
    @ResponseBody
    public String  categoryAdd(@ModelAttribute TCommodityCategory cc){
          String typeName=      request.getParameter("typeName");
        System.out.println(cc.toString()+typeName);
        if(cc.getId()==null){
            cts.AddCategory(cc,typeName);
        }else{
            cts.updateCategory(cc,typeName);
        }
        return "ok";
    }

    //一级商品删除
    @GetMapping("/categorydel/{id}")
    @ResponseBody
    public String categoryDel(@PathVariable("id") long id){
        cts.delCategory(id);
        return "ok";
    }

    //寻找指定根类下的所有一类
    @GetMapping("/categoryListByType/{id}")
    @ResponseBody
    public JSONArray  categoryListByType(@PathVariable("id") long id){
        List<Map<String,Object>>  lc=cts.findByType(id);
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(lc));
        return tableData;
    }


//商品二级类列表
    @GetMapping("/itemList")
    @ResponseBody
    public JSONArray itemList(){
        List<Map<String,Object>>  li=cts.findAllItem();
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(li));
        return tableData;
    }


    //商品二级类添加/修改
    @PostMapping("/itemAdd")
    @ResponseBody
    public String  itemAdd(@ModelAttribute TCommodityItem ci){
        if(ci.getId()==null){
            cts.saveItem(ci);
        }else{
            cts.updateItem(ci);
        }

        return "ok";
    }

    @GetMapping("/delItem/{id}")
    @ResponseBody
    public  String delItem(@PathVariable("id") long id){
        cts.delItem(id);
        return "ok";
    }

    //寻找指定根类下的所有二类
    @GetMapping("/itemListByCategory/{cid}")
    @ResponseBody
    public JSONArray itemListByCategory(@PathVariable("cid") long cid){
        List<Map<String,Object>>  lt=cts.findItemByCategory(cid);
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(lt));
        return tableData;
    }

    //商品概述列表
    /**
     * SELECT t.type_name ,c.category_name ,i.item_name FROM t_commodity_category c   RIGHT JOIN t_commodity_type t ON  t.id=c.type_id
     * 				       LEFT JOIN t_commodity_item i  ON i.category_id=c.id
     *  				ORDER BY t.type_order,c.id,i.id
     * */
    @GetMapping("/allType")
    @ResponseBody
    public JSONArray allType(){
        JSONArray ja=new JSONArray();
        List<Map<String,Object>> lt=cts.findAllType();
        for (Map<String,Object> map:lt) {
            JSONObject jo=new JSONObject();
            jo.put("type",map.get("typeName"));
            jo.put("id",map.get("id"));
            List<Map<String,Object>> lc=cts.findByType(Long.valueOf(map.get("id")+""));
            JSONArray category=new JSONArray();
            for (Map<String,Object> mp:lc) {
                JSONObject categoryJSONObj=new JSONObject();
                categoryJSONObj.put("name",mp.get("categoryName"));
                categoryJSONObj.put("id",mp.get("id"));
                List<Map<String,Object>> li=cts.findItemByCategory(Long.valueOf(mp.get("id")+""));
                JSONArray item=new JSONArray();
                for (Map<String,Object> m:li) {
                    JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(m));
                    item.add(itemJSONObj);
                }
                categoryJSONObj.put("item",item);
                category.add(categoryJSONObj);
            }
            jo.put("category",category);
            ja.add(jo);
            }
        return  ja;
    }
}

