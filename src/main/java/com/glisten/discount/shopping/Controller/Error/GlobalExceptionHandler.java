package com.glisten.discount.shopping.Controller.Error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局设置
 * 数据异常抛出到error.html页面
 *展示相关错误内容
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("ExceptionHandler ===>====" + e.getMessage());
        e.printStackTrace();
        // 这里可根据不同异常引起的类做不同处理方式
        String exceptionName = ClassUtils.getShortName(e.getClass());
        log.error("ExceptionHandler ===>" + exceptionName);
        ModelAndView mav = new ModelAndView();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mav.addObject("nowDate",df.format(new Date()));
//        mav.addObject("stackTrace", e.getStackTrace());
        mav.addObject("stackTrace", e.getStackTrace());
        mav.addObject("Exception", e);
        mav.addObject("errorMessage", e.toString());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("forward:/error");
        return mav;
    }
}
