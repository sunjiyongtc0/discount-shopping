package com.glisten.discount.shopping.Util;

import org.springframework.util.ResourceUtils;

import java.io.File;

public class DelFile {

    public  static boolean delImgs(String paths){
        boolean st=true;
        try {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        if(!upload.exists()) upload.mkdirs();
        String Imgpath=upload.getAbsolutePath()+"/";
        String[] p=paths.split(",");

        for (int i=0;i<p.length;i++){
            System.out.println(Imgpath+p[i]);
            if(!"".equals(p[i])){
                File img=new File(Imgpath+p[i]);
                if(!img.delete()){
                    st=false;
                    System.out.println(p[i]);
                }
            }
        }
        }catch ( Exception e ){
            System.out.println(e.getMessage());
        }

        return  st;
    }
}
