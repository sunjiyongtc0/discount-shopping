package com.glisten.discount.shopping.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/home")
public class HomeController {


    @RequestMapping("/index")
    public String index(){
        return "home";
    }


}
