package com.cnfantasia.server.log;

import com.cnfantasia.server.business.pub.exception.ExceptionParseUtil;
import com.cnfantasia.server.domainbase.taskLogger.entity.TaskLogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: TaskLog
 * @Date: 2016-12-15 16:16
 * @Auther: kangduo
 * @Description: ()
 */

public class JobLog {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private JobLogManager taskLogManager;
    public void setTaskLogManager(JobLogManager taskLogManager) {
        this.taskLogManager = taskLogManager;
    }

    public Object timeAround(ProceedingJoinPoint joinPoint) {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        String exception = null;
        long startTime = System.currentTimeMillis();
        try {
            //执行目录方法
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            exception = ExceptionParseUtil.parseExceptionMessage(e);
        }
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String clazzName = signature.getDeclaringTypeName().substring(signature.getDeclaringTypeName().lastIndexOf(".") + 1, signature.getDeclaringTypeName().length());
        String methodName = signature.getName();
        long dealTime = endTime - startTime;

        TaskLogger logger = new TaskLogger();
        logger.setClassName(clazzName);
        logger.setMethodName(methodName);
        logger.setTaskStartTime(sdf.format(new Date(startTime)));
        logger.setTaskEndTime(sdf.format(new Date(endTime)));
        logger.setDealTime(dealTime);
        logger.setExcepStackInfo(exception);
        logger.setIsUndefinedException(exception == null ? 0 : 1);

        taskLogManager.insertTaskLoggerCache(logger);
        return obj;
    }
}
