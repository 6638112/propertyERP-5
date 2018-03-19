package com.cnfantasia.wl.wechat.web;

import com.cnfantasia.server.api.livingPay.entity.LivingSubmitParams;
import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.livingFeePayRecord.service.LivingFeePayRecordBaseService;

import java.io.IOException;
import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wenfq
 * @Date: 2017-11-14 16:18
 */
@Controller
@RequestMapping(value = "livingPay")
public class LivingPayController extends BaseController {

    @Resource
    LivingPayService livingPayService;
    @Resource
    LivingFeePayRecordBaseService livingFeePayRecordBaseService;
    @Resource
    private IHttpClient simpleHttpClient;

    /**
     * 查看可缴费项目列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewLivingPayItemList.do", method = RequestMethod.GET)
    public ModelAndView viewLivingPayItemList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginHelper.shareSessionKey(request, response, simpleHttpClient);
        return new ModelAndView("/feePay/index");
    }
    
    /**
     * 查看已缴费记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/viewLivingPayHistoryList.do", method = RequestMethod.GET)
    public ModelAndView viewLivingPayHistoryList(HttpServletRequest request) {
        return new ModelAndView("/feePay/historyBill");
    }

    /**
     * 提交订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/submitOrder.do", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse submitOrder(HttpServletRequest request, LivingSubmitParams submitParams) {
        if (StringUtils.isEmpty(submitParams.getChargeObject()))
            throw new ValidateRuntimeException("充值对象不能为空");
        if (submitParams.getFeeTypeId() <= 0)
            throw new ValidateRuntimeException("缴费类型不能为空");

        if (submitParams.getAmount() <= 0 && submitParams.getFeeTypeId() != 6) {
            throw new ValidateRuntimeException("缴费金额必须大于0");
        }
        if(submitParams.getFeeTypeId() == 6 && submitParams.getAmount() < 0) {//宽带业务充值金额为零时表示开户
            throw new ValidateRuntimeException("缴费金额必须大于0");
        }

        if (submitParams.getFeeTypeId() <= 3 && StringUtils.isEmpty(submitParams.getLinkTel()))
            throw new ValidateRuntimeException("缴水电煤气费时，联系电话不能为空");

        if(submitParams.getUserId() == null)
            submitParams.setUserId(new BigInteger((LoginHelper.getRegist3rdResponseFromSession(request).getUserId())));

        BigInteger orderId = livingPayService.submitOrder(submitParams);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putData("orderId", orderId);
        return jsonResponse;
    }
}
