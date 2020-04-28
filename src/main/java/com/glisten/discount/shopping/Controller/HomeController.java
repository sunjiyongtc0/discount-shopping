package com.glisten.discount.shopping.Controller;


import com.glisten.discount.shopping.Interceptor.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/home")
public class HomeController {

    @Log("电脑端用户登录")
    @RequestMapping("/index")
    public String index(){
        return "home";
    }

    @RequestMapping("/m-index")
    public String mIndex(){
        return "m-home";
    }

    @Log("移动端用户登录")
    @RequestMapping("/m-i")
    public String mI(){
        return "m-index";
    }
}
