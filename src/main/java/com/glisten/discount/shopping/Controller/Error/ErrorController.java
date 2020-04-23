package com.glisten.discount.shopping.Controller.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 错误响应 对应的错误error的页面配置
 * */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;
    /**
     * 404页面
     */
    @GetMapping(value = "/404")
    public ModelAndView error_404() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/error");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mav.addObject("nowDate",df.format(new Date()));
        mav.addObject("Exception", "404");
        mav.addObject("errorMessage", "页面未找到！");
        mav.addObject("url", request.getRequestURL());
        return mav;
    }

    /**
     * 500页面
     */
    @GetMapping(value = "/500")
    public ModelAndView error_500() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/error");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mav.addObject("nowDate",df.format(new Date()));
        mav.addObject("Exception", "500");
        mav.addObject("errorMessage", "服务器内部错误！");
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
