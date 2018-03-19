package com.cnfantasia.server.api.ebuy.web;

import com.cnfantasia.server.api.ebuy.entity.ThemeAdvEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.json.JsonResponse;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: ThemeAdvController.
 * @Date: 2017-07-13 15:18
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/themeAdv")
public class ThemeAdvController extends BaseController {

    @Resource
    private IEbuyNewService ebuyNewService;

    /**
     * 获取超市和到家的推广活动商品列表
     * @param from
     * @param advId
     * @return
     */
    @RequestMapping(value = "/productList.json")
    @ResponseBody
    public JsonResponse getThemeAdvProductList(String from, BigInteger advId) {
        int appType = "LA".equals(from) ? 3 : 1;//1 为app商品，3为LA
        ThemeAdvEntity themeAdvDetail = ebuyNewService.getThemeAdvDetail(advId, appType);
        JsonResponse json = new JsonResponse();
        json.putData("adv", themeAdvDetail);
        return json;
    }
}
