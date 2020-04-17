package com.glisten.discount.shopping.Controller.Commodity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glisten.discount.shopping.Domain.TCommodityCategory;
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

//商品二级类列表
    @GetMapping("/itemList")
    @ResponseBody
    public JSONArray itemList(){
        JSONArray tableData=new JSONArray();
        for (int i=0;i<2;i++){
            JSONObject jb = new JSONObject();
            jb.put("order",i);
            jb.put("name","二级分类"+i);
            jb.put("category",i%2);
            tableData.add(jb);
        }
        return tableData;
    }
    //商品二级类添加
    @PostMapping("/itemAdd")
    @ResponseBody
    public String  itemAdd( @RequestParam("order") String  order,@RequestParam("name") String name,@RequestParam("category") String category ){
        System.out.println(order);
        System.out.println(name);
        System.out.println(category);
        return "ok";
    }

    //商品概述列表
    /**
     * SELECT t.type_name ,c.category_name ,i.item_name FROM t_commodity_category c   RIGHT JOIN t_commodity_type t ON  t.id=c.type_id
     * 				       LEFT JOIN t_commodity_item i  ON i.category_id=c.id
     *  				ORDER BY t.type_order,c.id,i.id
     * */
    @GetMapping("/allType")
    public JSONArray allType(){
        JSONArray ja=new JSONArray();
        String[][] sd={{"男子","鞋类","运动鞋"},{"男子","鞋类","休闲鞋"},{"男子","服饰","卫衣"},{"男子","服饰","球服"},{"女子","鞋类","瓢鞋"},{"女子","鞋类","高跟鞋"},{"女子","服饰","裙子"},{"女子","服饰","短裤"},{"儿童","男装","短裤"},{"儿童","男鞋","休闲鞋"},{"儿童","女装",""},{"儿童","女鞋","休闲鞋"},{"儿童","挂件",""},{"手绘","",""},{"流行","",""}};
//        String type="";
//        String category="";
//        String item="";
//        for(int  i=0;i<sd.length;i++){
//            if(!type.equals(sd[i][1])){
//                JSONObject jb1=new JSONObject();
//                JSONArray ja1=new JSONArray();
//                type=sd[i][1];
//                jb1.put("type",type);
//                if(!category.equals(sd[i][2])){
//                    JSONObject jb2=new JSONObject();
//                    JSONArray ja2=new JSONArray();
//                    category= sd[i][2];
//                    jb2.put("name",category);
//                    if(!item.equals(sd[i][3])){
//                        JSONObject jb3=new JSONObject();
//                        item= sd[i][3];
//                        jb3.put("name",category);
//                        ja2.add(jb3);
//                    }
//                    jb2.put("item",ja2);
//                    ja1.add(jb2);
//                }
//                jb1.put("category",ja1);
//            }
//
//       }

        return  ja;
    }
}

