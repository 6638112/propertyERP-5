package com.cnfantasia.server.jobhandler.storeproduct;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.ebuy.entity.EbuyProductEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.jobhandler.storeproduct.entity.JfqStoreInfo;
import com.cnfantasia.server.jobhandler.storeproduct.util.LgzUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

/**
 * @className: StoreProductSynHandler.
 * @date: 2017-11-29 13:43
 * @author: kangduo
 * @description: (线上消费后, 同步商品情况到店里, 5分钟100单应该足够了吧 6 0/5 * * * ?)
 */
@JobHander(value = "storeProductSynHandler")
@Component
public class StoreProductSynHandler extends IJobHandler {

    /**
     * 每次处理订单数
     */
    private static final int BATCH_DEAL_SIZE = 100;

    @Resource
    private IEbuyOrderBaseService ebuyOrderBaseService;
    @Resource
    private IEbuyService ebuyService;
    @Resource
    protected ISysParamManager sysParamManager;

    @Override
    public ReturnT<String> execute(String... params) throws Exception {
    	String jfqStoreInfoStr = sysParamManager.getSysParaValue(SysParamKey.JFQ_SOTRE_INFO);
    	JfqStoreInfo jfqStoreInfo = JSON.parseObject(jfqStoreInfoStr, JfqStoreInfo.class);
    	
        for (int i = 0; i < BATCH_DEAL_SIZE; i++) {
            String orderId = RedisCacheHandler.rpop(RedisCachePrefix.SYN_STORE_PRODUCT);
            if (StringUtils.isEmpty(orderId)) {
                break;
            }
            synSingleOrderProduct(new BigInteger(orderId), jfqStoreInfo);
        }
        return ReturnT.SUCCESS;
    }

    private void synSingleOrderProduct(BigInteger orderId, JfqStoreInfo jfqStoreInfo) {
        EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
        if (ebuyOrder == null || !Objects.equals(ebuyOrder.getType(), EbuyDict.EbuyOrder_Type.EBuy_Product)) {
            return;
        }
        List<EbuyProductEntity> orderProductList = ebuyService.getOrderProductList(orderId);
        LgzUtil.sendOrderMessage(ebuyOrder, orderProductList, jfqStoreInfo);
    }
}
