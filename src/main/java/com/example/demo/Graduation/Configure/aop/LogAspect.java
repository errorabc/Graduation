package com.example.demo.Graduation.Configure.aop;


import com.example.demo.Graduation.Annotation.LogAop;
import com.example.demo.Graduation.Dao.LogDao.LogDao;
import com.example.demo.Graduation.Tool.DateTime;
import com.example.demo.Graduation.Tool.IpUntil;
import com.example.demo.Graduation.entity.LogEntity;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogDao logDao;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.example.demo.Graduation.Annotation.LogAop)")
    public void pointcut() {
    }

    //切面 配置通知
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }


    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogEntity sysLog = new LogEntity();
        LogAop logAnnotation = method.getAnnotation(LogAop.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += paramNames[i] + ": " + args[i]+"   ";
            }
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String LoginUsername = (String) SecurityUtils.getSubject().getPrincipal();//已经登录的用户

        String uuid = UUID.randomUUID().toString();
        sysLog.setId(uuid);
        // 设置IP地址
        sysLog.setIp(IpUntil.getIpAdrress(request));
        // 模拟一个用户名
        sysLog.setUsername(LoginUsername);
        sysLog.setTime((int) time);
        System.out.println(DateTime.strToDateLong(df.format(new Date()))+"   Test");
        sysLog.setCreate_time(DateTime.strToDateLong(df.format(new Date())));
        // 保存系统日志
        logDao.saveSysLog(sysLog);
    }


}
