package com.cnfantasia.server.api.payment.entity;

import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;

import java.math.BigInteger;

/**
 * @className: AliPrePayParamEntity
 * @date: 2017-12-07 14:31
 * @author: yanghua
 * @description:(支付宝预支付参数)
 */
public class AliPrePayParamEntity {
    //订单ID
    private BigInteger orderId;
    //用户ID
    private BigInteger userId;
    //小区ID
    private BigInteger gbId;
    //是否是默认网关
    private boolean isExpressGateway;
    //回调URL
    private String notifyUrl;
    //商户网站唯一订单号
    private String outTradeNo;
    //商品的标题/交易标题/订单标题/订单关键字等
    private String subject;
    //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
    private String total_amount;
    //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
    private String body;
    //获取商户的支付宝信息：物业公司/管理处/解放区
    private PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isExpressGateway() {
        return isExpressGateway;
    }

    public void setExpressGateway(boolean expressGateway) {
        isExpressGateway = expressGateway;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public PropertyCompanyThirdPayCfg getPropertyCompanyThirdPayCfg() {
        return propertyCompanyThirdPayCfg;
    }

    public void setPropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg) {
        this.propertyCompanyThirdPayCfg = propertyCompanyThirdPayCfg;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }
}
