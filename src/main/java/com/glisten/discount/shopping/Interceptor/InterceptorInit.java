package com.glisten.discount.shopping.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new RefererInterceptor()).addPathPatterns("/images/upload/**");

    }

    /**
     * 虚拟路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        File sysFolder = new File(sysfolder);
//        if (!sysFolder.exists()) {
//            sysFolder.mkdirs();
//        }
        // 文件磁盘图片url 映射
        // 配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地绝对路径（需要加file协议）
//        registry.addResourceHandler("/sysfolder/**").addResourceLocations("file:/" + sysfolder);
    }


}
