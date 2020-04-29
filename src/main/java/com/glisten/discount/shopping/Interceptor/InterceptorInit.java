package com.glisten.discount.shopping.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 全局设置
 * 使拦截器生效
 *
 */
@Configuration
public class InterceptorInit implements WebMvcConfigurer {

//    @Value("${server.path.sysfolder}")
//    private String sysfolder;

    /*
     * 拦截器配置
     * 对所有url拦截，，进入AuthorityInterceptor拦截器方法
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //404也跳主页
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**");
        //图片防盗链
        registry.addInterceptor(new RefererInterceptor()).addPathPatterns("/images/upload/**");
        //防表单重复注册
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/comm/*");

    }

    /**
     * 虚拟路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os=System.getProperty("os.name");
        try {
        //获取跟目录
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //如果上传目录为/static/images/upload/，则可以如下获取：
        File upload = new File(path.getAbsolutePath(),"static/images/upload/");
        String  Imgpath=upload.getAbsolutePath()+"/";
            if(os.toLowerCase().startsWith("win")){
                //新增加一个类用来添加虚拟路径映射
                registry.addResourceHandler("/images/upload/**").addResourceLocations("file:"+ Imgpath);
            }else{
                registry.addResourceHandler("/images/upload/**").addResourceLocations("file:/JavaSpace/static/images/upload/");
            }
        }catch (Exception e){
            System.out.println("添加虚拟路径失败！");
        }
//        File sysFolder = new File(sysfolder);
//        if (!sysFolder.exists()) {
//            sysFolder.mkdirs();
//        }
        // 文件磁盘图片url 映射
        // 配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地绝对路径（需要加file协议）
//        registry.addResourceHandler("/sysfolder/**").addResourceLocations("file:/" + sysfolder);
    }


}
