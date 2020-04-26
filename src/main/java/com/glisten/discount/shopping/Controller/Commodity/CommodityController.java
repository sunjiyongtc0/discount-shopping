package com.glisten.discount.shopping.Controller.Commodity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.glisten.discount.shopping.Domain.TCommodityWares;
import com.glisten.discount.shopping.Interceptor.Token;
import com.glisten.discount.shopping.Service.Commodity.CommodityService;
import com.glisten.discount.shopping.Util.DelFile;
import com.glisten.discount.shopping.Util.FindIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comm")
public class CommodityController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private CommodityService cs;

    @PostMapping("/setSession")
    @ResponseBody
    public String setSession(){
        String t= request.getParameter("time");
        String secret= request.getParameter("secret");
        if(secret.equals("0")){
            if(Long.valueOf(t)>0){
                String ip= FindIp.getIpAddr(request);
                HttpSession session = request.getSession();
                session.setAttribute(ip,t);
            }
            return "ok";
        }else{
            return "error";
        }
    }

    @PostMapping("/getToken")
    @ResponseBody
    @Token(generate = true)
    public String getToken(){
        return  (String) request.getSession().getAttribute("token");
    }

    @RequestMapping("/index")
    public String  CommIndex(){

        return "comm/commMain";
    }

    @RequestMapping("/m-i/{id}")
    public ModelAndView MI(@PathVariable("id") long id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("comm/m-comm");
        mav.addObject("w",JSON.toJSON(cs.findWaresById(id)));
        return mav;
    }

    @PostMapping("/addComm")
    @ResponseBody
    @Token(remove = true)
    public String  addComm(@ModelAttribute TCommodityWares wares){
        if(wares.getId()==null){
           if( StringUtil.isNotEmpty(wares.getWaresName())){
               cs.SaveWares(wares);
           }else{
               return "error";
           }
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
             DelFile.delImgs(imgPath);
        }
        return "ok";
    }





    @GetMapping("/commListByKeyword/{type}/{id}")
    @ResponseBody
    public JSONArray commListByKeyword(@PathVariable("type") long type ,@PathVariable("id") long id){
        JSONArray ja =new JSONArray();
        if (type==0) {
            for (int i = 0; i < 19; i++) {
                JSONObject jb = new JSONObject();
                jb.put("waresRemarks", i);
                jb.put("waresName", "商品" + i);
                jb.put("waresImg", "f5010b4f-9c8d-4153-a340-0ac786c901c5.jpg");
                jb.put("waresPrice", 240 + i);
                jb.put("waresState", i % 2);
                ja.add(jb);
            }
        }else if(type==1){
            List<TCommodityWares> lcw=cs.findWaresByTypeId(id);
            ja=JSONArray.parseArray(JSON.toJSONString(lcw));

        }else if(type==2){
            List<TCommodityWares> lcw=cs.findWaresByCategoryId(id);
            ja=JSONArray.parseArray(JSON.toJSONString(lcw));
        }else if(type==3){
            List<TCommodityWares> lcw=cs.findWaresByItemId(id);
            ja=JSONArray.parseArray(JSON.toJSONString(lcw));
        }
        return ja;
    }

    @GetMapping(value = {"/commListByKeywords/{key}","/commListByKeywords/"})
    @ResponseBody
    public JSONArray commListByKeywords(@PathVariable(value = "key" ,required = false) String key){
        if(StringUtil.isEmpty(key)){
            key="";
        }
        JSONArray ja =new JSONArray();
        List<TCommodityWares> lcw=cs.findWaresByKeyName(key);
        ja=JSONArray.parseArray(JSON.toJSONString(lcw));
        return ja;
}

}
