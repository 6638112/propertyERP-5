package com.cnfantasia.server.ms.cache.web;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.omsSysParam.IOmsSysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.ms.pub.session.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * @ClassName: OmsCacheController
 * @Date: 2016-11-09 13:16
 * @Auther: kangduo
 * @Description:(运营平台清理缓存controller)
 */
@RequestMapping(value = "/omsCache")
@Controller
public class OmsCacheController extends BaseController {

    @Resource
    private IOmsSysParamManager omsSysParamManager;
    /**
     * 清理omsSysPara缓存
     * @return
     */
    @RequestMapping(value = "/refreshOmsSysParam.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse refreshOmsSysParam() {
        JsonResponse json = new JsonResponse();
        if (UserContext.getCurrUser() == null || UserContext.getCurrUser().getId() == null) {
            json.setStatus("0001");
        } else {
            omsSysParamManager.updSysParaValue();
            json.setDataValue("ok");
        }
        return json;
    }
}
