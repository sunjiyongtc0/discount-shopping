package com.glisten.discount.shopping.Interceptor;

import com.glisten.discount.shopping.Util.FindIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


/**
 * 连接器内容，，获取session存储的用户信息，不存在跳转到登录页
 * */
public class AuthorityInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = null;
        //获取执行方法上的注解
        if(handler instanceof  HandlerMethod){
            handlerMethod = (HandlerMethod) handler;
        }else{
            return true;
        }
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();
        if("CommIndex".equals(methodName)){
           HttpSession session=request.getSession();
            String ip= FindIp.getIpAddr(request);
            if(session.getAttribute(ip)!=null){
                        return true;
            }else{
                response.sendRedirect("/home/index");
                return false;
            }

        }
//        if("login".equals(methodName)||"register".equals(methodName)||"registerinto".equals(methodName)||"logininto".equals(methodName)||"scanLogin".equals(methodName)){
//            logger.info("====拦截到了方法,为登录注册相关！放行");
//            return true;
//        }else {
//            HttpSession session = request.getSession();
//            if (session != null && session.getAttribute("user") != null) {
//                TPUser user = (TPUser) session.getAttribute("user");
//                logger.info("====拦截方法，获取用户存在"+user.getId());
//                return true;
//            }else{
//                response.sendRedirect(request.getContextPath()+"/login");
//                logger.info("====拦截方法，请求不被允许。。。。退回到登录页面");
//                return  false;
//            }
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // logger.info("执行完方法之后进执行(Controller方法调用之后)，但是此时还没进行视图渲染");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // logger.info("整个请求都处理完咯，DispatcherServlet也渲染了对应的视图咯，此时我可以做一些清理的工作了");
    }

}