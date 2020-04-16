package com.glisten.discount.shopping.Controller.Manage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @GetMapping("/findPage")
    public String  findPage(){

        return "";
    }
}
