package com.cnfantasia.server.jobhandler.storeproduct.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.common.utils.HttpUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.jobhandler.storeproduct.entity.JfqStoreInfo;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @className: LgzUtil.
 * @date: 2017-11-29 14:34
 * @author: kangduo
 * @description: ()
 */
public final class LgzUtil {
    private static final String PUSH_ORDER_URL = "http://web.365lzg.com/haosyMob/intf_lwdOrder";

    public static boolean sendOrderMessage(EbuyOrder ebuyOrder, List<EbuyProductEntity> productList, JfqStoreInfo jfqStoreInfo) {

        //创建参数队列
        JSONObject json = new JSONObject();
        json.put("orderid", ebuyOrder.getId().toString());//订单id
        json.put("sellerid", jfqStoreInfo.getSellerid());//卖家id----"解放区"
        json.put("buyerid", ebuyOrder.getBuyerId().toString());//买家id
        json.put("buyername", ebuyOrder.getDelivPeopleName());//买家名称
        Long orderPrice = ebuyOrder.getAmount() + (ebuyOrder.getTotalCouponAmount() == null ? 0 : ebuyOrder.getTotalCouponAmount());
        json.put("orderprice", PriceUtil.div100(orderPrice));//订单价格
        json.put("orderdelivery", "Y");//订单是否支持配送 Y:支持
        json.put("orderdeliverymemo", "");//订单配送备注
        json.put("orderdt", ebuyOrder.getBuyTime());//下单时间
        json.put("status", "0");//订单状态：'0:下单成功 1:正常关闭 2:买家关闭 3:卖家关闭 4:发货' ,
        json.put("desc", "");//订单关闭原因
        json.put("name", ebuyOrder.getDelivPeopleName());//收货人名称
        json.put("tel", ebuyOrder.getDelivPhone());//收货人电话
        json.put("area", "");//地址，现在待定
        json.put("address", ebuyOrder.getDelivAddressDetail());//收货地址
        json.put("remark", "");//订单备注
        json.put("username", jfqStoreInfo.getUsername());//----"解放区"
        json.put("shopname", jfqStoreInfo.getShopname());//----"深圳园景园店"
//        json.put("shopname", "测试2");
        json.put("paymentname", jfqStoreInfo.getPaymentname());//----"APP账户"
        JSONArray orderDetail = new JSONArray();
        if (CollectionUtils.isEmpty(productList)) {
            return false;
        }
        boolean hasErpProduct = false;
        for (EbuyProductEntity entity : productList) {
            if (!StringUtils.isEmpty(entity.getErpCode())) {
                JSONObject obj = new JSONObject();
                obj.put("leid", entity.getErpCode());//产品id
                obj.put("productname", entity.getName());//产品名称
                obj.put("price", PriceUtil.div100s(entity.getPrice()));//价格
                obj.put("num", entity.getBuyNum().toString());//数量
                orderDetail.add(obj);
                hasErpProduct = true;
            }
        }
        if (!hasErpProduct) {
            return false;
        }
        json.put("orderdetail", orderDetail);

        //填充参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("param", json.toString());
        String result = null;
        //三次尝试
        for (int i = 0; i < 3; i++) {
            result = HttpUtil.post(PUSH_ORDER_URL, paramMap);
            XxlJobLogger.log("同步订单到乐掌柜，传参数为：" + paramMap + ",返回结果为" + result);
            if ("000|成功".equals(result)) {
                return true;
            }
        }
        return "000|成功".equals(result);
    }
}
