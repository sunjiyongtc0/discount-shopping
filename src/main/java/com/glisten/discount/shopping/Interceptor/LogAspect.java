package com.glisten.discount.shopping.Interceptor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.glisten.discount.shopping.Domain.TLoggerAction;
import com.glisten.discount.shopping.Service.Logger.LogActionService;
import com.glisten.discount.shopping.ThreadWork.LogThread;
import com.glisten.discount.shopping.ThreadWork.ThreadLog;
import com.glisten.discount.shopping.Util.Judge;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 定义为切面
 *
 *  *@Before  在切点方法之前执行
 *  *@After  在切点方法之后执行
 *  *@AfterReturning 切点方法返回后执行
 *  *@AfterThrowing 切点方法抛异常执行
 *  *@Around 属于环绕增强，能控制切点执行前，执行后，，用这个注解后，程序抛异常，会影响@AfterThrowing这个注解
 *
 */
@Aspect
@Order(5)
@Component
public class LogAspect {
    @Value("${devSwitch}")
    private boolean devSwitch;
    @Autowired
    private  LogActionService LogService;


    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
//    private static LinkedBlockingQueue<TLoggerAction> queue = new LinkedBlockingQueue<TLoggerAction>(100);
//    private static ConcurrentHashMap<String,Object> concurrentHashMap=new ConcurrentHashMap<String,Object>();
//    private static LogThread lt=new LogThread();

    @Pointcut("@annotation(com.glisten.discount.shopping.Interceptor.Log)")
    public void pointcut() {
    }

    /**
     * 前置通知，在Controller层操作前拦截
     *
     * @param joinPoint 切入点
     */
    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 正常情况返回
     *
     * @param joinPoint 切入点
     * @param rvt  正常结果
     */
    @AfterReturning(pointcut = "pointcut()", returning = "rvt")
    public void doAfter(JoinPoint joinPoint, Object rvt) throws Exception {
        handleLog(joinPoint, null, rvt);
    }

    /**
     * 异常信息拦截
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) throws Exception {
//        handleLog(joinPoint, e, null);
    }

    @Async
    public void handleLog(final JoinPoint joinPoint, final Exception e, Object rvt) throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr(); // 获取IP地址
        String osAndBrower = request.getHeader("user-agent");
        String os = Judge.whatOS(osAndBrower);
        String brower = Judge.whatBrower(osAndBrower);
        // 获得注解
        Method method = getMethod(joinPoint);
        Log log = getAnnotationLog(method);
        if (log == null) {
            return;
        }
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(now);
        TLoggerAction la=new TLoggerAction();
        la.setActionName(log.value());
        la.setActionTime(dateString);
        la.setHostIp(ip);
        la.setActionFunction(os + ":" + brower);
        if(!devSwitch) {
            logger.info(la.toString());
            LogService.SaveLog(la);
        }
//        ThreadLog.add(la);
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationLog(Method method) {
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method;
        }
        return null;
    }

    private StringBuilder handleParams(StringBuilder params, Object[] args, List paramNames) throws JsonProcessingException {

        return params;
    }


}