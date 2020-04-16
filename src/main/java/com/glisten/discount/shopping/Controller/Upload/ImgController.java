package com.glisten.discount.shopping.Controller.Upload;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/img")
public class ImgController {

    @PostMapping("/uplode")
    @ResponseBody
    public String uplodeImg(@RequestParam MultipartFile file){
        String ImgName=UUID.randomUUID() +".jpg";
        try {
            File f= new File("E://"+ImgName);
            file.transferTo(f);
            String fileName = f.getName();
            System.out.println(fileName);
            return ImgName;
        }catch (Exception e){
            return "";
        }

    }
}
