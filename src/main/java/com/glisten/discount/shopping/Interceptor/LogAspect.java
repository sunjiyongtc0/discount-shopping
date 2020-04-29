package com.glisten.discount.shopping.Interceptor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.glisten.discount.shopping.Domain.TLoggerAction;
import com.glisten.discount.shopping.Service.Logger.LogActionService;
import com.glisten.discount.shopping.ThreadWork.LogThread;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LogActionService LogService;

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static LinkedBlockingQueue<TLoggerAction> queue = new LinkedBlockingQueue<TLoggerAction>(100);
    private static ConcurrentHashMap<String,Object> concurrentHashMap=new ConcurrentHashMap<String,Object>();
    private static LogThread lt=new LogThread();

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
//        System.out.println(la.toString());
        LogService.SaveLog(la);
//        if(concurrentHashMap.isEmpty()){
//            concurrentHashMap.put(ip,new Date().getTime());
//            queue.put(la);
//            lt.ActionLog(queue,concurrentHashMap);
//        }else if( !concurrentHashMap.containsKey(ip)){
//            concurrentHashMap.put(ip,new Date().getTime());
//                        queue.put(la);
//            lt.ActionLog(queue,concurrentHashMap);
////          new  LogThread().ActionLog(queue,concurrentHashMap);
//        }
//        for (String s:concurrentHashMap.keySet()){
//            System.out.println(s);
//            System.out.println(concurrentHashMap.get(s));
//            if((long )concurrentHashMap.get(s)+15*60*1000>new Date().getTime()){
//                concurrentHashMap.remove(s);
//            }
//        }



//        // 操作数据库日志表
//        ErpLog erpLog = new ErpLog();
//        erpLog.setErrorCode(0);
//        erpLog.setIsDeleted(0);
//        // 请求信息
//        HttpServletRequest request = ToolUtil.getRequest();
//        erpLog.setType(ToolUtil.isAjaxRequest(request) ? "Ajax请求" : "普通请求");
//        erpLog.setTitle(log.value());
//        erpLog.setHost(request.getRemoteHost());
//        erpLog.setUri(request.getRequestURI().toString());
////  erpLog.setHeader(request.getHeader(HttpHeaders.USER_AGENT));
//        erpLog.setHttpMethod(request.getMethod());
//        erpLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        // 请求的方法参数值
//        Object[] args = joinPoint.getArgs();
//        // 请求的方法参数名称
//        LocalVariableTableParameterNameDiscoverer u
//                = new LocalVariableTableParameterNameDiscoverer();
//        String[] paramNames = u.getParameterNames(method);
//        if (args != null && paramNames != null) {
//            StringBuilder params = new StringBuilder();
//            params = handleParams(params, args, Arrays.asList(paramNames));
//            erpLog.setParams(params.toString());
//        }
//        String retString = JsonUtil.bean2Json(rvt);
//        erpLog.setResponseValue(retString.length() > 5000 ? JsonUtil.bean2Json("请求参数数据过长不与显示") : retString);
//        if (e != null) {
//            erpLog.setErrorCode(1);
//            erpLog.setErrorMessage(e.getMessage());
//        }
//        Date stime = startTime.get();
//        erpLog.setStartTime(stime);
//        erpLog.setEndTime(now);
//        erpLog.setExecuteTime(now.getTime() - stime.getTime());
//        erpLog.setUsername(MySysUser.loginName());
//        HashMap<String, String> browserMap = ToolUtil.getOsAndBrowserInfo(request);
//        erpLog.setOperatingSystem(browserMap.get("os"));
//        erpLog.setBrower(browserMap.get("browser"));
//        erpLog.setId(IdUtil.simpleUUID());
//        logService.insertSelective(erpLog);
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
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof Map) {
//                Set set = ((Map) args[i]).keySet();
//                List list = new ArrayList();
//                List paramList = new ArrayList<>();
//                for (Object key : set) {
//                    list.add(((Map) args[i]).get(key));
//                    paramList.add(key);
//                }
//                return handleParams(params, list.toArray(), paramList);
//            } else {
//                if (args[i] instanceof Serializable) {
//                    Class<?> aClass = args[i].getClass();
//                    try {
//                        aClass.getDeclaredMethod("toString", new Class[]{null});
//                        // 如果不抛出NoSuchMethodException 异常则存在 toString 方法 ，安全的writeValueAsString ，否则 走 Object的 toString方法
//                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i]));
//                    } catch (NoSuchMethodException e) {
//                        params.append(" ").append(paramNames.get(i)).append(": ").append(objectMapper.writeValueAsString(args[i].toString()));
//                    }
//                } else if (args[i] instanceof MultipartFile) {
//                    MultipartFile file = (MultipartFile) args[i];
//                    params.append(" ").append(paramNames.get(i)).append(": ").append(file.getName());
//                } else {
//                    params.append(" ").append(paramNames.get(i)).append(": ").append(args[i]);
//                }
//            }
//        }
        return params;
    }


}