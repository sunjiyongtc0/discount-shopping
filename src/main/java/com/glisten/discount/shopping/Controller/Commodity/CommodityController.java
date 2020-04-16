package com.glisten.discount.shopping.Controller.Commodity;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/comm")
public class CommodityController {

    @RequestMapping("/index")
    public String  index(){
        return "/comm/commMain";
    }

    @PostMapping("/addComm")
    @ResponseBody
    public String  addComm(@RequestParam Map<String, Object> params){
        System.out.println(params);

        return "ok";
    }

    @GetMapping("/commList")
    @ResponseBody
    public JSONArray commList(){
        JSONArray ja =new JSONArray();
        for (int i=0;i<5;i++){
            JSONObject jb = new JSONObject();
            jb.put("order",i);jb.put("name","商品"+i);
            if(i%2==1) {
                jb.put("img", "E://63ee5c97-1ac2-49be-a7f7-fcbcb99f9eca.jpg");
            }else {
                jb.put("img","https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg");
            }
            jb.put("price",240+i);jb.put("state",i%2);
            ja.add(jb);
        }
        return ja;
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
