package com.glisten.discount.shopping.Util;

public class Judge {


    //判断是啥操作系统
    public  static  String whatOS(final String str){
       String osStr="未知";
        if(str.toLowerCase().indexOf("android") != -1){
            osStr="安卓用户";
        }else if(str.toLowerCase().indexOf("iphone") != -1 || str.toLowerCase().indexOf("ipad") != -1 || str.toLowerCase().indexOf("ipod") != -1){
            osStr="苹果用户";
        }else{
            osStr="电脑用户";
        }
        return osStr;
    }

    //判断是啥浏览器-简单判断下
    public static String whatBrower(final String str){
        String browerStr = "未知";
        if(str.toLowerCase().indexOf("micromessenger")!= -1){
            browerStr="微信浏览器";
        } else if(str.toLowerCase().contains("ucbrowser")){
            browerStr = "UC浏览器";
        }else  if(str.toLowerCase().contains("chrome")){
            browerStr = "Chrome浏览器";
        }else if(str.toLowerCase().contains("firefox")){
            browerStr = "Firefox浏览器";
        }else if(str.toLowerCase().contains("safari")){
            browerStr = "Safari浏览器";
        }
        return  browerStr;
    }
}
