package com.glisten.discount.shopping.Controller;


import com.alibaba.fastjson.JSON;
import com.glisten.discount.shopping.Interceptor.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/home")
public class HomeController {


    @Autowired
    private HttpServletResponse response;
    @Log("电脑端用户登录")
    @RequestMapping("/index")
    public String index(){
        return "home";
    }



    @RequestMapping("/m-index/{id}")
    public ModelAndView MI(@PathVariable("id") long id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("m-home");
        mav.addObject("type", id);
        return mav;
    }

    @Log("移动端用户登录")
    @RequestMapping("/m-i")
    public String mI(){
        return "m-index";
    }

    @RequestMapping("/m-about")
    public String mAbout(){
        return "m-about";
    }
}
