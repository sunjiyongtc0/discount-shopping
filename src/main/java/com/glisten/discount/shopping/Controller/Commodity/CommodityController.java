package com.glisten.discount.shopping.Controller.Commodity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glisten.discount.shopping.Domain.TCommodityWares;
import com.glisten.discount.shopping.Service.Commodity.CommodityService;
import com.glisten.discount.shopping.Util.DelFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comm")
public class CommodityController {

    @Autowired
    private CommodityService cs;

    @RequestMapping("/index")
    public String  index(){
        return "comm/commMain";
    }

    @PostMapping("/addComm")
    @ResponseBody
//    public String  addComm(@RequestParam Map<String, Object> params){
    public String  addComm(@ModelAttribute TCommodityWares wares){
        System.out.println(wares.toString());
        if(wares.getId()==null){
            cs.SaveWares(wares);
        }else{
            cs.updateWares(wares);
        }

        return "ok";
    }

    @GetMapping("/commList")
    @ResponseBody
    public JSONArray commList(){
        List<TCommodityWares> lw= cs.findWaresList();
        JSONArray tableData= JSONArray.parseArray(JSON.toJSONString(lw));
        return tableData;
//        for (int i=0;i<5;i++){
//            JSONObject jb = new JSONObject();
//            jb.put("order",i);jb.put("name","商品"+i);
//            if(i%2==1) {
//                jb.put("filepath", "63ee5c97-1ac2-49be-a7f7-fcbcb99f9eca.jpg,4d3ea53c084bad6931a56d5158a48jpeg.jpeg");
//            }else {
//                jb.put("filepath","https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg,4d3ea53c084bad6931a56d5158a48jpeg.jpeg");
//            }
//            jb.put("price",240+i);jb.put("state",i%2);jb.put("type","根类0");
//            jb.put("category","一级分类1");jb.put("item","二级分类0");jb.put("remarks","在修改时显示");
//            ja.add(jb);
//        }
//        return ja;
    }

    @PostMapping("delComm")
    @ResponseBody
    public  String delComm(@ModelAttribute TCommodityWares wares){
        String imgPath = wares.getWaresImg();

        if(cs.delWares(wares.getId())==1) {
           //图片附带删除
            System.out.println(  DelFile.delImgs(imgPath));
        }
        return "ok";
    }





    @GetMapping("/commListByKeyword/{data}")
    @ResponseBody
    public JSONArray commListByKeyword(@PathVariable("data") String data){
        System.out.println("data"+data);
        JSONArray ja =new JSONArray();
        for (int i=0;i<19;i++){
            JSONObject jb = new JSONObject();
            jb.put("color",i);jb.put("name","商品"+i);
            if(i%7==1) {
                jb.put("img", "E://63ee5c97-1ac2-49be-a7f7-fcbcb99f9eca.jpg");
            }else {
                jb.put("img","https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg");
            }
            jb.put("price",240+i);jb.put("state",i%2);
            ja.add(jb);
        }
        return ja;
    }

}