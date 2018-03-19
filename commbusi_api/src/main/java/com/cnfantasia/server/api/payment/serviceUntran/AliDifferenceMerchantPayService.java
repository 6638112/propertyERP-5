package com.cnfantasia.server.api.payment.serviceUntran;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.payment.dao.AliDiffPaymentPayDao;
import com.cnfantasia.server.api.payment.entity.AliPrePayParamEntity;
import com.cnfantasia.server.api.payment.entity.OrderPayInfo;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderExt.dao.IEbuyOrderExtBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderExt.entity.EbuyOrderExt;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.dao.IEbuyPrepayAlipayLogBaseDao;
import com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity.EbuyPrepayAlipayLog;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.service.IPropertyCompanyThirdPayCfgBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @className: AliDifferenceMerchantPayService
 * @date: 2017-12-07 13:51
 * @author: yanghua
 * @description:(支付宝：多商户支付：分别支付到不同的商户)
 */
public class AliDifferenceMerchantPayService {
    //字符集utf-8
    private final static String CHARSET = "UTF-8";
    //RSA加密方式
    private final static String RSA_TYPE = "RSA2";
    //传输格式
    private final static String FOMAT = "json";
    /**默认网关*/
    private final static String GATEWAY = "https://openapi.alipay.com/gateway.do";

    private Log logger = LogFactory.getLog(getClass());
    @Resource
    private ICommPayService commPayService;
    @Resource
    private IEbuyPrepayAlipayLogBaseDao ebuyPrepayAlipayLogBaseDao;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private AliDiffPaymentPayDao aliDiffPaymentPayDao;
    @Resource
    private ICommonEbuyService commonEbuyService;
    @Resource
    private CommEbuyPayRecordService commEbuyPayRecordService;
    @Resource
    private IPropertyCompanyThirdPayCfgBaseService propertyCompanyThirdPayCfgBaseService;
    @Resource
    private IEbuyOrderExtBaseDao ebuyOrderExtBaseDao;

    /**
     * 预支付
     * @param aliPrePayParamEntity
     * @return APP调用支付宝支付需要的参数
     */
    public String prePay(AliPrePayParamEntity aliPrePayParamEntity) {
        logger.info("AliDifferenceMerchantPayService prePay start,userId is:"+aliPrePayParamEntity.getUserId()+",orderId is:"+aliPrePayParamEntity.getOrderId()+",notifyUrl is:"+aliPrePayParamEntity.getNotifyUrl()+",isExpressGateway is:"+aliPrePayParamEntity.isExpressGateway());
        //增加支付需要的参数信息（订单、支付宝信息：物业公司/管理处/自己平台）
        addPrePayParams(aliPrePayParamEntity);

        //对参数进行签名（返回给APP支付需要的url）
        String resStr = null;
        try {
            resStr = getPrePayParamSignStr(aliPrePayParamEntity);
        } catch (Exception e) {
            logger.info("Alipay prePay cause RuntimeException,errorMsg is :"+e.getMessage(),e);
        } finally {
            //记录请求日志到数据库
            ebuyPrepayAlipayLogAdd(aliPrePayParamEntity, resStr);
        }
        logger.info("AliDifferenceMerchantPayService prePay end");

        return resStr;
    }

    /**
     * 支付宝回到通知
     * @param params
     * @return
     */
    public Boolean payNotify(Map<String,String> params) {
        Boolean payNotifyRes = false;
        logger.info("Alipay payNotify start,params is:"+ JSON.toJSONString(params));
        EbuyPayRecord ebuyPayRecord = new EbuyPayRecord();

        //支付宝分配给开发者的应用Id
        String app_id = params.get("app_id");
        //获取支付宝配置信息
        PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = getAlipayCfg(app_id);

        try {
            //验签
            Boolean verifyNotifyRes = null;
            try {
                verifyNotifyRes = verifyNotify(params, propertyCompanyThirdPayCfg.getAliPublicKey());
            } catch (Exception e) {
                logger.info("Alipay.payNotify.verifyNotify.params.failed", e);
                throw new BusinessRuntimeException("Alipay.payNotify.verifyNotify.params.failed", e);
            }
            logger.info("Alipay payNotify,verifyNotifyRes is :"+verifyNotifyRes);
            //订单处理(回调逻辑处理、判断回调业务参数是否正确)
            payNotifyRes = optionEbuyOrder(params, verifyNotifyRes, ebuyPayRecord, propertyCompanyThirdPayCfg);
        } catch (RuntimeException e) {
            logger.info("Alipay payNotify cause RuntimeException "+e.getMessage(), e);
            ebuyPayRecord.setPayErrInfo(e.getMessage());
            throw e;
        }finally{
            //记录日志
            addEbuyPayRecord(ebuyPayRecord, params);
            logger.info("Alipay payNotify end.");
        }
        return payNotifyRes;
    }

    /**
     * 传入参数获取带签名的URL
     * @param aliPrePayParamEntity
     * @return
     * @throws Exception
     */
    private String getPrePayParamSignStr(AliPrePayParamEntity aliPrePayParamEntity) throws Exception {
        String resStr = null;
        String APP_ID = aliPrePayParamEntity.getPropertyCompanyThirdPayCfg().getAppid();
        String APP_PRIVATE_KEY = aliPrePayParamEntity.getPropertyCompanyThirdPayCfg().getPrivateKey();
        String ALIPAY_PUBLIC_KEY = aliPrePayParamEntity.getPropertyCompanyThirdPayCfg().getAliPublicKey();

        //支付宝开放平台SDK封装了签名和验签过程，只需配置账号及密钥参数即可
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY, APP_ID, APP_PRIVATE_KEY, FOMAT, CHARSET, ALIPAY_PUBLIC_KEY, RSA_TYPE);
        //实例化客户端
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        request.setBizModel(model);

        model.setOutTradeNo(aliPrePayParamEntity.getOutTradeNo());
        model.setSubject(aliPrePayParamEntity.getSubject());
        model.setTotalAmount(aliPrePayParamEntity.getTotal_amount());
        model.setBody(aliPrePayParamEntity.getBody());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setTimeoutExpress("30m");
        //商户外网可以访问的异步地址
        request.setNotifyUrl(aliPrePayParamEntity.getNotifyUrl());

        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            resStr = response.getBody();
            logger.info("AliDifferenceMerchantPayService orderString" + resStr);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return resStr;
    }

    /**
     * 记录预支付请求信息到数据库
     * @param aliPrePayParamEntity
     * @param payLinkMap
     */
    private void ebuyPrepayAlipayLogAdd(AliPrePayParamEntity aliPrePayParamEntity, String payLinkMap) {
        EbuyPrepayAlipayLog ebuyPrepayAlipayLogAdd = new EbuyPrepayAlipayLog();
        try {
            BigInteger toAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_prepay_alipay_log);
            ebuyPrepayAlipayLogAdd.setId(toAddId);
            ebuyPrepayAlipayLogAdd.setAmount(aliPrePayParamEntity.getTotal_amount());
            if(aliPrePayParamEntity.isExpressGateway()){
                ebuyPrepayAlipayLogAdd.setIsExpressGateway(EbuyDict.EbuyPrepayAlipayLog_IsExpressGateway.YES);
            }else{
                ebuyPrepayAlipayLogAdd.setIsExpressGateway(EbuyDict.EbuyPrepayAlipayLog_IsExpressGateway.NO);
            }
            ebuyPrepayAlipayLogAdd.setNotifyUrl(aliPrePayParamEntity.getNotifyUrl());
            ebuyPrepayAlipayLogAdd.setOrderId(aliPrePayParamEntity.getOrderId());
            ebuyPrepayAlipayLogAdd.setOutTradeNo(aliPrePayParamEntity.getOutTradeNo());
            ebuyPrepayAlipayLogAdd.setPayLinkMap(payLinkMap);
            ebuyPrepayAlipayLogAdd.setPayLinkStr(payLinkMap);
            ebuyPrepayAlipayLogAdd.setProductDetail(aliPrePayParamEntity.getBody());
            ebuyPrepayAlipayLogAdd.setProductInfo(aliPrePayParamEntity.getSubject());
            ebuyPrepayAlipayLogAdd.setUserId(aliPrePayParamEntity.getUserId());
            Integer res = ebuyPrepayAlipayLogBaseDao.insertEbuyPrepayAlipayLog(ebuyPrepayAlipayLogAdd);
            if(res==null||res<=0){
                logger.info("Alipay prePay insertEbuyPrepayAlipayLog failed,res="+res);
            }
        } catch (Exception e2) {
            logger.info("Alipay prePay insertEbuyPrepayAlipayLog cause exception",e2);
        }
    }

    /**
     * 组装预支付需要的参数信息：订单/支付宝信息（物业公司、管理处、自己平台）
     * @param aliPrePayParamEntity
     */
    private void addPrePayParams(AliPrePayParamEntity aliPrePayParamEntity) {
        //查询订单信息
        OrderPayInfo orderPayInfo = commPayService.getOrderPayInfoById(aliPrePayParamEntity.getUserId(), aliPrePayParamEntity.getOrderId());
        //获取商户的支付宝信息：物业公司/管理处/解放区
        PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = new PropertyCompanyThirdPayCfg();
        //判断订单是否支持到账到物业公司
        if(isPaymentToCompany(orderPayInfo.getType())) {
            //车禁需要单独处理： 停车场和门牌可能不在同一个小区
            if(new Integer("4").equals(orderPayInfo.getType())) {
                BigInteger gbId = aliDiffPaymentPayDao.getCarnumGbId(orderPayInfo.getOrderId());
                propertyCompanyThirdPayCfg = getPropertyCompanyThirdPayCfg(gbId);
            } else {
                propertyCompanyThirdPayCfg = getPropertyCompanyThirdPayCfg(aliPrePayParamEntity.getGbId());
            }
        } else {
            //查询默认（自己平台）支付宝配置 id=1
            propertyCompanyThirdPayCfg = propertyCompanyThirdPayCfgBaseService.getPropertyCompanyThirdPayCfgBySeqId(new BigInteger("1"));
        }
        aliPrePayParamEntity.setPropertyCompanyThirdPayCfg(propertyCompanyThirdPayCfg);
        //商户网站唯一订单号
        aliPrePayParamEntity.setOutTradeNo(orderPayInfo.getOutTradeNo());
        //商品的标题/交易标题/订单标题/订单关键字等
        aliPrePayParamEntity.setSubject(orderPayInfo.getProductInfo());
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        aliPrePayParamEntity.setTotal_amount(PriceUtil.div100(orderPayInfo.getTotalAmount()).toString());
        //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body
        String body = orderPayInfo.getProductDetail()!=null?orderPayInfo.getProductDetail():orderPayInfo.getProductInfo();
        aliPrePayParamEntity.setBody(body);
    }

    /**
     * 支付宝回调验签
     * @param params
     * @return
     */
    private boolean verifyNotify(Map<String,String> params, String alipaypublicKey) {
        boolean flag = false;

        try {
            flag = AlipaySignature.rsaCheckV1(params, alipaypublicKey, CHARSET,RSA_TYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 处理订单
     * @param params 回调参数
     * @param verifyNotifyRes 是否验签成功
     * @param propertyCompanyThirdPayCfg
     * @return 业务参数正确
     */
    private Boolean optionEbuyOrder(Map<String, String> params, Boolean verifyNotifyRes, EbuyPayRecord ebuyPayRecord, PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg) {
        Boolean payNotifyRes = false;
        // 商户订单号
        String out_trade_no = params.get("out_trade_no");
        // 交易状态
        String trade_status = params.get("trade_status");
        //默认为失败
        ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Failed);
        if (verifyNotifyRes) {
            EbuyOrder ebuyOrder = commonEbuyService.selectEbuyOrderByOrderNo(out_trade_no);
            ebuyPayRecord.settEbuyOrderFId(ebuyOrder.getId());
            //防止订单重复支付
            if (trade_status.equals("TRADE_FINISHED")||trade_status.equals("TRADE_SUCCESS")) {
                //订单支付成功，提示不能重复支付
                if (ebuyOrder.getPayStatus() != null && EbuyDict.EbuyOrder_PayStatus.Pay_Success.compareTo(ebuyOrder.getPayStatus()) == 0){
                    logger.info("Repeat notify,the order info has been updated .");
                    ebuyPayRecord.setPayErrInfo("Repeat notify,the order info has been updated");
                }else{
                    //变更支付状态为：成功
                    ebuyPayRecord.setPayStatus(EbuyDict.EbuyPayRecord_PayStatus.Pay_Success);
                    //支付成功处理逻辑
                    commPayService.paySuccessOperate(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.Alipay);
                    //标记账单到账到哪里的状态
                    addEbuyOrderExt(ebuyOrder.getId(), propertyCompanyThirdPayCfg);
                }
                payNotifyRes = true;
            }else{
                ebuyPayRecord.setPayErrInfo("trade_status result is:"+trade_status);
            }
        } else {// 验证失败
            ebuyPayRecord.setPayErrInfo("签名验证失败,verifyNotifyRes="+verifyNotifyRes);
        }

        return payNotifyRes;
    }

    /**
     * 记录交易信息
     * @param ebuyPayRecord
     * @param params
     */
    private void addEbuyPayRecord(EbuyPayRecord ebuyPayRecord, Map<String,String> params) {
        // 商户订单号
        String out_trade_no = params.get("out_trade_no");
        // 支付宝交易号
        String trade_no = params.get("trade_no");
        try {
            ebuyPayRecord.setFlowNo(trade_no);
            ebuyPayRecord.setOrderNo(out_trade_no);
            ebuyPayRecord.setPayAccount(null);
            ebuyPayRecord.setPayAmount(null);
            //支付描述，用户 xxx购买商品：aa*3;bb*4;
            ebuyPayRecord.setPayDesc(null);
            ebuyPayRecord.setPayMethod(EbuyDict.EbuyPay_PayMethod.Alipay);
            ebuyPayRecord.setPayResultInfo(JSON.toJSONString(params));
            ebuyPayRecord.setPayTime(DateUtils.getCurrentDate());
            boolean isSuccess = commEbuyPayRecordService.insertEbuyPayRecord(ebuyPayRecord);
            if(!isSuccess){
                logger.info("Alipay payNotify Alipay payNotify failed,resCount is "+isSuccess);
            }
        } catch (Exception e2) {
            logger.info("Alipay payNotify Alipay payNotify cause error"+e2.getMessage(),e2);
        }
    }

    /**
     * 标记订单付款到哪里
     * @param orderId
     * @param propertyCompanyThirdPayCfg
     */
    private void addEbuyOrderExt(BigInteger orderId, PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg) {
        EbuyOrderExt ebuyOrderExt = new EbuyOrderExt();
        ebuyOrderExt.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_ext));
        ebuyOrderExt.settEbuyOrderFId(orderId);
        //自己平台:0为自己平台，1物业公司，2管理处
        //propertyCompanyThirdPayCfg第一条f_id=1的为默认数据（自己平台）
        if(new BigInteger("1").equals(propertyCompanyThirdPayCfg.getId())) {
            ebuyOrderExt.setPaymentTo(0);
        } else if(!DataUtil.isEmpty(propertyCompanyThirdPayCfg.gettPcId()) & DataUtil.isEmpty(propertyCompanyThirdPayCfg.gettPmId())) {
            //物业公司
            ebuyOrderExt.setPaymentTo(1);
        } else {
            //管理处
            ebuyOrderExt.setPaymentTo(2);
        }
        ebuyOrderExt.setWyCouponAmount(0L);
        ebuyOrderExt.setSys0AddTime(DateUtils.getCurrentDate());
        ebuyOrderExt.setSys0DelState(0);
        ebuyOrderExtBaseDao.insertEbuyOrderExt(ebuyOrderExt);
    }

    /**
     * 查询支付宝配置信息（回调）
     * @param appId
     * @return
     */
    private PropertyCompanyThirdPayCfg getAlipayCfg(String appId) {
        PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = new PropertyCompanyThirdPayCfg();
        List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgBys = new ArrayList<PropertyCompanyThirdPayCfg>();
        Map<String, Object> querMap = new HashMap<>();
        if(!DataUtil.isEmpty(appId)) {
            querMap.put("appid", appId);
            propertyCompanyThirdPayCfgBys = propertyCompanyThirdPayCfgBaseService.getPropertyCompanyThirdPayCfgByCondition(querMap);
        } else {//使用默认的解放区配置
            querMap.put("id", 1);
            propertyCompanyThirdPayCfgBys = propertyCompanyThirdPayCfgBaseService.getPropertyCompanyThirdPayCfgByCondition(querMap);
        }
        if(!DataUtil.isEmpty(propertyCompanyThirdPayCfgBys)) {
            propertyCompanyThirdPayCfg = propertyCompanyThirdPayCfgBys.get(0);
        }
        logger.info("Alipay payNotify  propertyCompanyThirdPayCfg " + propertyCompanyThirdPayCfg);
        return propertyCompanyThirdPayCfg;
    }

    /**
     * 判断订单是否能够付款到物业公司
     * @return
     */
    private boolean isPaymentToCompany(Integer orderType) {
        boolean isPaymentToCompany = false;
        //订单类型=="2":"物业费账单","4":"车禁缴费","99":"物业缴费总账单" 支持到账到物业公司
        switch (orderType) {
            case 2 : isPaymentToCompany = true;
                break;
            case 4 : isPaymentToCompany = true;
                break;
            case 99 : isPaymentToCompany = true;
                break;
        }

        return isPaymentToCompany;
    }

    /**
     * 获取(物业公司/管理处/自有平台)的支付宝配置信息
     * @param gbId 小区ID
     * @return
     */
    public PropertyCompanyThirdPayCfg getPropertyCompanyThirdPayCfg(BigInteger gbId) {
        PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = aliDiffPaymentPayDao.getDefaultAliPayInfo(gbId);
        logger.info("Alipay prePay  propertyCompanyThirdPayCfg " + propertyCompanyThirdPayCfg);
        return propertyCompanyThirdPayCfg;
    }
}
