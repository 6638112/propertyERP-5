package com.cnfantasia.server.business.pub.RepeatReqValidation;

import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName: APIRepeatReqInteceptor
 * @Date: 2017-01-03 10:29
 * @Auther: kangduo
 * @Description: (重复提交拦截器，如果重复提交，直接返回上一次的结果)
 */
public class APIRepeatReqInteceptor extends HandlerInterceptorAdapter {
    private Log logger = LogFactory.getLog(getClass());
    private static final Object LOCK = new Object();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatReqValidate validate = method.getAnnotation(RepeatReqValidate.class);
            String userId = UserContext.getOperId();
            if (null != validate && userId != null) {
                int expireSecond = validate.expireSecond();
                String key = RedisCachePrefix.Repeat_Req_Validation + userId + request.getServletPath();
                String value = null;
                synchronized (LOCK) {
                    value = RedisCacheHandler.get(key);
                    if (value == null) {//为空代表未访问，redis设值防止占位并发
                        RedisCacheHandler.set(key, "{}", expireSecond);
                    }
                }
                if (value != null && "{}".equals(value)) {
                    throw new BusinessRuntimeException("repeat.request.submit.handler");
                } else if (value != null && !"{}".equals(value)){
                    returnJson(response, value);
                    return false;
                }
                request.setAttribute(RedisCachePrefix.Repeat_Req_Validation, key);
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (request.getAttribute(RedisCachePrefix.Repeat_Req_Validation) != null) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            RepeatReqValidate validate = handlerMethod.getMethod().getAnnotation(RepeatReqValidate.class);
            String key = (String) request.getAttribute(RedisCachePrefix.Repeat_Req_Validation);

            //拿不到返回的json，只能用多层反射
            Field f = response.getOutputStream().getClass().getDeclaredField("delegate");
            f.setAccessible(true);
            Object o = f.get(response.getOutputStream());
            f = o.getClass().getDeclaredField("delegate");
            f.setAccessible(true);
            o = f.get(o);
            f = o.getClass().getDeclaredField("ob");
            f.setAccessible(true);
            o = f.get(o);
            f = o.getClass().getDeclaredField("outputChunk");
            f.setAccessible(true);
            o = f.get(o);
            //内部存储都是采用的unicode格式（ISO8859-1），转码
            if (DataUtil.isEmpty(o.toString())) {
                RedisCacheHandler.del(key);
            } else {
                RedisCacheHandler.set(key, new String(o.toString().getBytes("iso-8859-1"), "utf-8"), validate.expireSecond());
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }

    private void returnJson(HttpServletResponse response, String JsonValue) {
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(JsonValue);
        } catch (IOException e) {
            logger.error("io close exception", e);
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
