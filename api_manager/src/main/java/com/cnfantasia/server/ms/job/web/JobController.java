package com.cnfantasia.server.ms.job.web;

import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.ms.pub.session.UserContext;

import java.io.IOException;
import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: JobController.
 * @Date: 2017-09-05 17:41
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/job")
public class JobController {

    @Resource
    private ISysParamManager sysParamManager;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public void indexPage(HttpServletResponse response) throws IOException {
        BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
        String key = RedisCachePrefix.JOB_ADMIN_TOKEN + userId;
        String value = RedisCacheHandler.get(key);
        String url = sysParamManager.getSysParaValue(SysParamKey.JOB_ADMIN_BASE_URL) + "toLogin";
        if (!"-1".equals(value)) {
            value = UUIDGenerater.getId();
            RedisCacheHandler.set(key, value, 60 * 30);
            url = url + "?token=" + value + "&userId=" + userId;
        }
        response.sendRedirect(url);
    }

    @RequestMapping(value = "/tokenValidation.json", method = RequestMethod.GET)
    @ResponseBody
    public String validateToken(BigInteger userId, String token) {
        String key = RedisCachePrefix.JOB_ADMIN_TOKEN + userId;
        String value = RedisCacheHandler.get(key);
        if (token.equals(value)) {
            RedisCacheHandler.set(key, "-1", 60);
            return "success";
        }
        return "fail";
    }

}
