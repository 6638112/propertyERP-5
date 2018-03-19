package com.cnfantasia.server.api.cache.constant;

/**
 * @ClassName: RedisCachePrefix
 * @Date: 2016-06-28 10:04
 * @Auther: kangduo
 * @Description:(redis 缓存键值前缀)
 */
public class RedisCachePrefix {

    //物业维修，是否第一次付款，第一次有提示信息
    public static final String isFirstPayDredge = "isFirstPayDredge_";

    public static final String storeViewCountToday = "storeViewCountToday_";

    public static final String is_Some_One_Query_Software_Property_Fee = "isSomeOneQuerySoftwarePropertyFee_";

    public static final String dredge_bill_confirm_pay_params = "dredge_bill_confirm_pay_params";

    public static final String property_repair_push_software = "propertyRepairPushSoftware_";

    public static final String property_repair_need_software_detail = "propertyRepairNeedSoftwareDetail_";

    public static final String Repeat_Req_Validation = "RepeatReqValidation_";

    public static final String Merchant_Add_Limit_Buy_Push = "MerchantAddLimitBuyPush_";
    
    public static final String Ebuy_Order_Id = "EbuyOrderId_";
    
    public static final String car_offline_list = "car_offline_list";

    public static final String Pay_Bill = "Pay_Bill_";
    
    public static final String Bank_Collection = "bc_";

    public static final String Short_Url = "jfq:shortUrl:";

    public static final String Dredge_Product_View_OpenId = "jfq:dredge:dredgeProductView:";
    
    public static final String TEMP_CAR_VIEW_OPENID = "jfq:car:carFeeView:";

    /**
     * 解放区体验店查看
     */
    public static final String JFQ_Store_View_OpenId = "jfq:store:view:";

    /**
     * 商户扫码:临停车
     */
    public static final String JFQ_Store_Car_Scan_OpenId = "jfq:store:car:scan:";
    public static final String JFQ_Store_Car_Scan_UserId = "jfq:store:car:userId:";

    public static final String WECHAT_ACCESS_TOKEN = "wechat_access_token_";

    public static final String JOB_ADMIN_TOKEN = "job_admin_token:";

    public static final String SYN_STORE_PRODUCT = "jfq:synStoreProductList";
}
