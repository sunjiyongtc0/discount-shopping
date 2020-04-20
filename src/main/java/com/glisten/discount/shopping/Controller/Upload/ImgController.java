package com.glisten.discount.shopping.Controller.Upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/img")
public class ImgController {

    //注解从配置文件读取值的用法
//    @Value("${ImgPath}")
    private static String  Imgpath;

    static  {
        try {
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            //如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"static/images/upload/");
            if(!upload.exists()) upload.mkdirs();
            Imgpath=upload.getAbsolutePath()+"/";
            System.out.println("upload url:"+Imgpath);
        }catch ( Exception e ){
            System.out.println(e.getMessage());
        }



    }
    @PostMapping("/uplode")
    @ResponseBody
    public String uplodeImg(@RequestParam MultipartFile file){

        String ImgName=UUID.randomUUID() +".jpg";
        try {
            File f= new File(Imgpath+ImgName);
//            File f= new File(ImgName);
            file.transferTo(f);
            String fileName = f.getName();
            System.out.println(fileName);
            return ImgName;
        }catch (Exception e){
            return "";
        }

    }

    @GetMapping("/imgPath")
    @ResponseBody
    public String imgPath(){
        return Imgpath;
    }
}
