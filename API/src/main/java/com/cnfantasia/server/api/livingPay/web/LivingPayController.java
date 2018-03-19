package com.cnfantasia.server.api.livingPay.web;

import com.cnfantasia.server.api.livingPay.entity.AdEntity;
import com.cnfantasia.server.api.livingPay.entity.PayItem;
import com.cnfantasia.server.api.livingPay.entity.PayRecord;
import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.livingFeePayRecord.entity.LivingFeePayRecord;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenfq
 * @Date: 2017-11-13 11:08
 */
@Controller
@RequestMapping(value = "livingPay")
public class LivingPayController extends BaseController {
    @Resource
    LivingPayService livingPayService;

    /**
     * 查询生活缴费项列表，如水电气、固话或手机
     * @param request
     * @return
     */
    @RequestMapping(value = "/qryLivingPayItemList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryLivingPayItemList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //缴费项目
        List<PayItem> payItemList = livingPayService.qryPayItemList();
        jsonResponse.putData("list", payItemList);
        //滚动广告
        List<AdEntity> adsList = livingPayService.qryadsList();
        jsonResponse.putData("adsList", adsList);

        return jsonResponse;
    }

    /**
     * 查询生活缴费账单列表，
     * @param request
     * @return
     */
    @RequestMapping(value = "/qryLivingPayRecordList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse qryLivingPayRecordList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger userId = UserContext.getOperIdBigInteger();
        if(userId == null)
            userId = ParamUtils.getBigInteger(request, "userId", null);

        List<PayRecord> livingFeePayRecordList = livingPayService.qryLivingPayRecordList(userId);
        jsonResponse.putData("list", livingFeePayRecordList);
        return jsonResponse;
    }
}
