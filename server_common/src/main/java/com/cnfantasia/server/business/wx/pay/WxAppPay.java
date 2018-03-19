package com.cnfantasia.server.business.wx.pay;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.wx.WxCommUtil;
import com.cnfantasia.server.business.wx.pay.util.HttpUtil;
import com.cnfantasia.server.business.wx.pay.util.MD5Util;
import com.cnfantasia.server.business.wx.pay.util.WxPayCommonConfigUtil;
import com.cnfantasia.server.business.wx.pay.util.WxPayCommonUtil;
import com.cnfantasia.server.business.wx.pay.util.XMLUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信app支付
 * created on 2017-04-06 14:55
 *
 * @author nextyu
 */
public class WxAppPay {

    private static Logger logger = LoggerFactory.getLogger(WxAppPay.class);

    private WxAppPay() {
    }

    /**
     * 构建支付信息
     * 移动端解析获取sdk需要传入的参数信息
     *
     * @param wxAppPayDTO
     * @return 支付信息
     */
    public static WxAppPayVO buildPayInfo(WxAppPayDTO wxAppPayDTO) {
        String randomString = UUIDGenerater.generateShortUuid();
        WxAppPayVO wxAppPayVO = new WxAppPayVO();
        try {
            Map<String, String> map = unifiedOrder(wxAppPayDTO, randomString);
            String reqXml = map.get("requestXml");
            String resXml = map.get("resultXml");
            wxAppPayVO.setReqXml(reqXml);
            wxAppPayVO.setResXml(resXml);

            String prepayId = XMLUtil.parseXML(resXml).get("prepay_id");
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

            StringBuilder sb = new StringBuilder();
            sb.append("appid=").append(wxAppPayDTO.getAppId());
            sb.append("&noncestr=").append(randomString);
            sb.append("&package=Sign=WXPay");
            sb.append("&partnerid=").append(wxAppPayDTO.getMchId());
            sb.append("&prepayid=").append(prepayId);
            sb.append("&timestamp=").append(timestamp);
            sb.append("&key=").append(wxAppPayDTO.getApiKey());

            String newSign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();

            /*Map<String, String> map2 = new HashMap<String, String>();
            map2.put("appid", wxAppPayDTO.getAppId());
            map2.put("noncestr", randomString);
            map2.put("package", "Sign=WXPay");
            map2.put("partnerid", wxAppPayDTO.getMchId());
            map2.put("prepayid", prepayId);
            map2.put("timestamp", timestamp);
            map2.put("sign", newSign);*/

            wxAppPayVO.setAppId(wxAppPayDTO.getAppId());
            wxAppPayVO.setNonceStr(randomString);
            wxAppPayVO.setPackageName("Sign=WXPay");
            wxAppPayVO.setPartnerId(wxAppPayDTO.getMchId());
            wxAppPayVO.setPrepayId(prepayId);
            wxAppPayVO.setTimestamp(timestamp);
            wxAppPayVO.setSign(newSign);

        } catch (Exception e) {
            logger.error("WxAppPay buildPayInfo error", e);
        }
        return wxAppPayVO;
    }

    /**
     * 统一下单
     *
     * @param wxAppPayDTO
     * @param randomString
     * @return
     * @throws Exception
     */
    private static Map<String, String> unifiedOrder(WxAppPayDTO wxAppPayDTO, String randomString) throws Exception {
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", wxAppPayDTO.getAppId());
        packageParams.put("mch_id", wxAppPayDTO.getMchId());
        packageParams.put("nonce_str", randomString);
        packageParams.put("body", wxAppPayDTO.getBody());
        packageParams.put("out_trade_no", wxAppPayDTO.getOutTradeNo());
        packageParams.put("total_fee", wxAppPayDTO.getTotalFee());
        packageParams.put("spbill_create_ip", wxAppPayDTO.getSpBillCreateIp());
        packageParams.put("notify_url", wxAppPayDTO.getNotifyUrl());
        packageParams.put("trade_type", "APP");
//        packageParams.put("attach", wxAppPayDTO.getAttach());

        String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, wxAppPayDTO.getApiKey());
        packageParams.put("sign", sign);

        String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
        String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.UNIFIED_ORDER_URL, requestXML);
        Map<String, String> resMap = new HashMap<>(2);
        resMap.put("requestXml", requestXML);
        resMap.put("resultXml", resultXml);
        return resMap;
    }

    public static WxAppCallBackVo checkCallBack(HttpServletRequest request, String appId, String mchId, String apiKey)
            throws IOException, JDOMException {

        WxAppCallBackVo vo = new WxAppCallBackVo();
        String responseStr = WxCommUtil.parseWeixinCallback(request);
        vo.setCallBackStr(responseStr);

        logger.info("支付回调返回结果为：" + JSON.toJSONString(responseStr));
        SortedMap<String, String> map = XMLUtil.parseXML(responseStr);
        String signFromAPIResponse = map.get("sign");
        map.remove("sign");
        String sign = WxPayCommonUtil.createSign("UTF-8", map, apiKey);
        if (!sign.equals(signFromAPIResponse)) {
            vo.setValid(false);
        }
        String tradeResult = getTradeState(appId, mchId, map.get("transaction_id"), apiKey);
        vo.setValid("SUCCESS".equals(tradeResult));
        vo.setResultCode(map.get("result_code"));
        vo.setTradeStatus(tradeResult);
        vo.setTransId(map.get("transaction_id"));
        vo.setOutTradeNo(map.get("out_trade_no"));
        vo.setTotalFee(map.get("total_fee"));
        return vo;
    }

    /**
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     * @param appId
     * @param mchId
     * @param transId
     * @param apiKey
     * @return
     */
    private static String getTradeState(String appId, String mchId, String transId, String apiKey) {
        String randomString = UUIDGenerater.generateShortUuid();
        try {
            SortedMap<String, String> packageParams = new TreeMap<String, String>();
            packageParams.put("appid", appId);
            packageParams.put("mch_id", mchId);
            packageParams.put("nonce_str", randomString);
            packageParams.put("transaction_id", transId);
            String sign = WxPayCommonUtil.createSign("UTF-8", packageParams, apiKey);
            packageParams.put("sign", sign);

            String requestXML = WxPayCommonUtil.getRequestXml(packageParams);
            String resultXml = HttpUtil.postData(WxPayCommonConfigUtil.QUERY_ORDER_URL, requestXML);
            Map<String, String> map = XMLUtil.parseXML(resultXml);
            return map.get("trade_state");

        } catch (Exception e) {
            logger.error("WxPCPay getTradeState error", e);
        }
        return null;
    }
}
