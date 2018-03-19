package com.cnfantasia.server.api.limitBuy.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.ebuy.entity.EbuyDeliveryMethodEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyStore;
import com.cnfantasia.server.api.ebuy.service.IEbuyService;
import com.cnfantasia.server.api.ebuy.util.DeliveryNoGenerator;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.limitBuy.dao.ILimitBuyDao;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.dao.IEbuyDeliveryMethodBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.service.IEbuyDeliveryOrderProductBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.dao.IEbuyOrderDeliveryTypeBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.service.IEbuyOrderHasTEbuyProductBaseService;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.limitBuyActivity.dao.LimitBuyActivityBaseDao;
import com.cnfantasia.server.domainbase.limitBuyActivity.entity.LimitBuyActivity;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.dao.UserHasTLimitBuyActivityBaseDao;
import com.cnfantasia.server.domainbase.userHasTLimitBuyActivity.entity.UserHasTLimitBuyActivity;

/**
 * @ClassName: LimitBuyService
 * @Date: 2016-12-28 13:25
 * @Auther: kangduo
 * @Description: (限时抢购service层)
 */
public class LimitBuyService implements ILimitBuyService{
    private Log logger = LogFactory.getLog(getClass());

    @Resource
    IEbuyOrderDeliveryTypeBaseDao ebuyOrderDeliveryTypeBaseDao;

	@Resource 
	private LimitBuyActivityBaseDao limitBuyActivityBaseDao;
	@Resource 
	private UserHasTLimitBuyActivityBaseDao userHasTLimitBuyActivityBaseDao;
	
    private ILimitBuyDao limitBuyDao;
    public void setLimitBuyDao(ILimitBuyDao limitBuyDao) {
        this.limitBuyDao = limitBuyDao;
    }

    private IUuidManager uuidManager;
    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private IEbuyOrderBaseService ebuyOrderBaseService;
    public void setEbuyOrderBaseService(IEbuyOrderBaseService ebuyOrderBaseService) {
        this.ebuyOrderBaseService = ebuyOrderBaseService;
    }

    private IUserBaseDao userBaseDao;
    public void setUserBaseDao(IUserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }

    private IDualDao dualDao;
    public void setDualDao(IDualDao dualDao) {
        this.dualDao = dualDao;
    }

    private IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService;
    public void setEbuyOrderHasTEbuyProductBaseService(IEbuyOrderHasTEbuyProductBaseService ebuyOrderHasTEbuyProductBaseService) {
        this.ebuyOrderHasTEbuyProductBaseService = ebuyOrderHasTEbuyProductBaseService;
    }

    private IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService;
    public void setEbuyDeliveryOrderProductBaseService(IEbuyDeliveryOrderProductBaseService ebuyDeliveryOrderProductBaseService) {
        this.ebuyDeliveryOrderProductBaseService = ebuyDeliveryOrderProductBaseService;
    }

    private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;
    public void setEbuyDeliveryOrderBaseService(IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService) {
        this.ebuyDeliveryOrderBaseService = ebuyDeliveryOrderBaseService;
    }

    private ICommonEbuyService commonEbuyService;
    private ICommonEbuyService getCommonEbuyService() {
        if (commonEbuyService == null) {
            return commonEbuyService = (ICommonEbuyService) CnfantasiaCommbusiUtil.getBeanManager("commonEbuyService");
        }
        return commonEbuyService;
    }

    private IEbuyService ebuyService;
    private IEbuyService getEbuyService() {
        if (ebuyService == null) {
            return ebuyService = (IEbuyService) CnfantasiaCommbusiUtil.getBeanManager("ebuyService");
        }
        return ebuyService;
    }

    @Override
    public List<LimitBuyInfo> getLimitBuyListByGbId(BigInteger gbId, Integer appType, Integer positionType, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("positionType", positionType);
        param.put("appType", appType == null ? 1 : appType);
        if (pageModel != null) {
            param.put("_begin", pageModel.getBegin());
            param.put("_length", pageModel.getLength());
        }
        return limitBuyDao.getLimitBuyListByGbId(param);
    }

    public LimitBuyInfo getLimitBuyInfo(BigInteger limitBuyId, Integer appType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("limitBuyId", limitBuyId);
        param.put("appType", appType == null ? 1 : appType);
        return limitBuyDao.getLimitBuyInfo(param);
    }


    /**
     * 生成订单（仅限APP用）
     * @param limitBuyId
     * @param buyNum
     * @param hbAmount
     * @param couponId
     * @param address
     * @param isSelfGet
     * @return
     */
    @Override
    public Map<String, Object> confirmPay(BigInteger limitBuyId, Integer buyNum, Long hbAmount, BigInteger couponId,
                                          String address, String linkName, String linkPhone, int isSelfGet, BigInteger gbId) {
        if (hbAmount != null && hbAmount > 0d && couponId != null) {
            throw new BusinessRuntimeException("ebuy.submitOrder.coupon.multi.error");
        }
        LimitBuyInfo limitBuyInfo = this.getLimitBuyInfo(limitBuyId, HeaderManager.getWlAppType().intValue());
        if (limitBuyInfo == null || limitBuyInfo.getStatus() != 1) {
            throw new BusinessRuntimeException("limitBuy.submitOrder.status.error");
        }
        if (limitBuyInfo.getLeftCount() < buyNum) {
            throw new BusinessRuntimeException("ebuyService.checkLeftCount.not.enough",new Object[]{limitBuyInfo.getProductName(),limitBuyInfo.getLeftCount()});
        }

        BigInteger orderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);

        // 处理配送费
        Long totalDeliveryFee = 0L;// 总配送费
        EbuyDeliveryMethod ebuyDeliveryMethod = getEbuyDeliveryMethod(limitBuyInfo.getMerchantId(), (long) (buyNum * limitBuyInfo.getLimitBuyPriceInteger()));

        if(isSelfGet == 0) // 1是自提，自提免配送费
            totalDeliveryFee = ebuyDeliveryMethod.getFee();
        EbuyOrderDeliveryType ebuyOrderDeliveryType = new EbuyOrderDeliveryType();
        ebuyOrderDeliveryType.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_delivery_type));
        ebuyOrderDeliveryType.setDeliveryType(isSelfGet == 1 ? 1 : 0);
        ebuyOrderDeliveryType.settEbuyOrderFId(orderId);
        ebuyOrderDeliveryType.settEbuySupplyMerchant(limitBuyInfo.getMerchantId());

        //处理订单
        EbuyOrder ebuyOrder = new EbuyOrder();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String nowTime = dualDao.getNowTime();
        ebuyOrder.setType(EbuyDict.EbuyOrder_Type.EBuy_Product);//订单类型：改为普通商品要支持收益结算 
        ebuyOrder.setId(orderId);
        ebuyOrder.setBuyerId(userId);
        ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId).getDefaultRoomId());
        ebuyOrder.setGbId(gbId);
        ebuyOrder.setBuyTime(nowTime);
        ebuyOrder.setCreater(userId);
        ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(orderId));
        ebuyOrder.setPayMethod(null);
        ebuyOrder.setPayTime(null);
        ebuyOrder.setDelivPeopleName(linkName);
        ebuyOrder.setDelivPhone(linkPhone);
        ebuyOrder.setDelivAddressDetail(address);
        ebuyOrder.setDelivTargetType(2);
        if(isSelfGet == 0) {
        	ebuyOrder.setDelivTargetId(ebuyDeliveryMethod.getId());
        } else {
        	ebuyOrder.setDelivTargetId(BigInteger.valueOf(-1));
        }
        ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
        ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
        ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
        ebuyOrder.setTotalDeliveryFee(totalDeliveryFee);//总配送费
        ebuyOrder.setAmount((long) (limitBuyInfo.getLimitBuyPriceInteger() * buyNum) + totalDeliveryFee); //应付金额
        ebuyOrder.setSubChannel(HeaderManager.getSubChannelId());


        EbuyOrderHasTEbuyProduct hasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
        hasTEbuyProduct.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_ebuy_product));
        hasTEbuyProduct.settEbuyOrderFId(orderId);
        hasTEbuyProduct.settEbuyProductFId(limitBuyInfo.getShelfId());
        hasTEbuyProduct.setProductQty(buyNum.longValue());
        hasTEbuyProduct.setProductPrice(limitBuyInfo.getLimitBuyPriceInteger().longValue());
        hasTEbuyProduct.setProductPricePoint(0L);
        hasTEbuyProduct.setSupplyMerchantId(limitBuyInfo.getMerchantId());
        hasTEbuyProduct.setPurchasePrice(Long.valueOf(limitBuyInfo.getShelfPriceDiscount()));

        //增加运单
        EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
        BigInteger deliveryOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order);
        deliveryOrder.setId(deliveryOrderId);
        deliveryOrder.settEbuyOrderFId(orderId);
        deliveryOrder.settSupplyMerchantFId(limitBuyInfo.getMerchantId());
        deliveryOrder.setAmount((long) (limitBuyInfo.getLimitBuyPriceInteger() * buyNum) + totalDeliveryFee);
        deliveryOrder.setTotalCoupon(0L);
        if(isSelfGet == 0) {
	        deliveryOrder.setDeliveryRealFee(totalDeliveryFee);
	        deliveryOrder.setDeliveryId(ebuyDeliveryMethod.getId());//-1为免邮费
	        deliveryOrder.setUserDeliveryType(1);//"1":"店铺默认","2":"用户自提"
        } else {
        	deliveryOrder.setDeliveryRealFee(0L);
	        deliveryOrder.setDeliveryId(BigInteger.valueOf(-1));//-1为免邮费
	        deliveryOrder.setUserDeliveryType(2);//"1":"店铺默认","2":"用户自提"
        }
        deliveryOrder.setBuyerId(UserContext.getOperIdMustExistBigInteger());
        deliveryOrder.setCreateTime(nowTime);
        deliveryOrder.setDeliveryNo(DeliveryNoGenerator.getDeliveryNo(deliveryOrderId, deliveryOrder.gettSupplyMerchantFId()));
        deliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.NotStart);// 未启动状态
        deliveryOrder.setRevenueStatus(0);//新增是否计算收益
        deliveryOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);//配送单推送状态0未发送

        //增加运单商品表记录
        EbuyDeliveryOrderProduct deliveryOrderProduct = new EbuyDeliveryOrderProduct();
        deliveryOrderProduct.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order_product));
        deliveryOrderProduct.settEbuyDeliveryOrderFId(deliveryOrderId);
        deliveryOrderProduct.settEbuyOrderFId(orderId);
        deliveryOrderProduct.settEbuyOrderHasTEbuyProductFId(hasTEbuyProduct.getId());
        deliveryOrderProduct.settEbuyProductFId(hasTEbuyProduct.gettEbuyProductFId());
        deliveryOrderProduct.setSupplyMerchantId(hasTEbuyProduct.getSupplyMerchantId());

        ebuyOrderBaseService.insertEbuyOrder(ebuyOrder);
        ebuyDeliveryOrderBaseService.insertEbuyDeliveryOrder(deliveryOrder);
        ebuyOrderHasTEbuyProductBaseService.insertEbuyOrderHasTEbuyProduct(hasTEbuyProduct);
        ebuyDeliveryOrderProductBaseService.insertEbuyDeliveryOrderProduct(deliveryOrderProduct);
        if(ebuyOrderDeliveryType.getDeliveryType() == 1) //用户自提才需要保存
            ebuyOrderDeliveryTypeBaseDao.insertEbuyOrderDeliveryType(ebuyOrderDeliveryType);

        //使用了消费券或粮票
        Long orderLeftAmount = -1L;
        if (hbAmount != null && hbAmount.compareTo(0L) > 0) {
            orderLeftAmount = getCommonEbuyService().updateOrderByHb(userId, ebuyOrder.getId(), hbAmount);
            if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
                getCommonEbuyService().paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
            }
        } else if (couponId != null) {
            Set<BigInteger> couponIdSet = new HashSet<BigInteger>();
            couponIdSet.add(couponId);
            orderLeftAmount = getCommonEbuyService().updateOrderByCopounList(userId, ebuyOrder.getId(), couponIdSet, null, null);
            if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
                getCommonEbuyService().paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList);
            }
        }

        if (orderLeftAmount != null && orderLeftAmount != -1) {
            deliveryOrder.setAmount(orderLeftAmount);
            deliveryOrder.setTotalCoupon(ebuyOrder.getAmount() - orderLeftAmount);
            ebuyDeliveryOrderBaseService.updateEbuyDeliveryOrder(deliveryOrder);
        }
        
        saveLimitBuyRecord(buyNum, limitBuyInfo, userId, orderId);

//      减少库存
        getEbuyService().lockProductLeftCountByOrderId(orderId, userId, EbuyDict.PointType.EBUY_PRODUCT, 1L);
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("orderId", orderId);
        resMap.put("shouldPay", deliveryOrder.getAmount());
        return resMap;
    }

    /**
     * 记录用户限时促俏购买记录，并减库存 added by wenfq 2017-03-15
     * @param limitBuyId
     * @param buyNum
     * @param limitBuyInfo
     * @param userId
     * @param orderId 
     */
    @Override
	public void saveLimitBuyRecord(Integer buyNum, LimitBuyInfo limitBuyInfo,
			BigInteger userId, BigInteger orderId) {
    	LimitBuyActivity lbc = new LimitBuyActivity();
    	lbc.setId(limitBuyInfo.getLimitBuyId());
    	lbc.setLeftCount((long)Math.max(limitBuyInfo.getLeftCount() - buyNum, 0));
    	limitBuyActivityBaseDao.updateLimitBuyActivity(lbc);
    	
    	UserHasTLimitBuyActivity ulbc = new UserHasTLimitBuyActivity();
    	ulbc.settUserFId(userId);
    	ulbc.settLimitBuyActivityFId(limitBuyInfo.getLimitBuyId());
    	ulbc.settEbuyProductFId(limitBuyInfo.getProductId());
    	ulbc.settEbuyOrderFId(orderId);
    	ulbc.setProductCount((long)buyNum);
    	ulbc.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_limit_buy_activity));
    	userHasTLimitBuyActivityBaseDao.insertUserHasTLimitBuyActivity(ulbc);
	}

    @Override
    public List<LimitBuyInfo> getLimitBuyListByMerchant(BigInteger merchantId, Integer appType, Integer status, PageModel pageModel) {
        return limitBuyDao.getLimitBuyListByMerchant(merchantId, appType, status, pageModel);
    }

    @Override
    public List<EbuyStore> getLimitBuyListStore(BigInteger gbId, Integer appType, Integer positionType, Integer status, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("gbId", gbId);
        param.put("appType", appType);
        param.put("status", status);
        param.put("positionType", positionType);
        if (pageModel != null) {
            param.put("_begin", pageModel.getBegin());
            param.put("_length", pageModel.getLength());
        }
        return limitBuyDao.getLimitBuyListStore(param);
    }

	@Override
	public int qryBuyCountWithActivtyIdForUser(Map<String, Object> param) {
		return limitBuyDao.qryBuyCountWithActivtyIdForUser(param);
	}
	
	@Override
	public int deleteLimitActivityByOrderId(BigInteger orderId) {
		return limitBuyDao.deleteLimitActivityByOrderId(orderId);
	}
	
	@Override
	public List<EbuyProduct> getEbuyProductByCondition(Map<String,Object> paramMap){
		return limitBuyDao.getEbuyProductByCondition(paramMap);
	}

    @Override
    public List<EbuyProduct> getHotSaleProduct(BigInteger storeId) {
        return limitBuyDao.getHotSaleProduct(storeId);
    }

    @Resource
    IEbuyDeliveryMethodBaseDao ebuyDeliveryMethodBaseDao;

    @Override
    public EbuyDeliveryMethod getEbuyDeliveryMethod(BigInteger merchantId, Long productAmount) {
        List<EbuyDeliveryMethodEntity> currMerEbuyDeliveryMethodList = limitBuyDao.selectEbuyDeliveryMethodListByMerId(merchantId);
        if (currMerEbuyDeliveryMethodList == null || currMerEbuyDeliveryMethodList.size() <= 0) {
            logger.info("ebuy.fetchEbuyDeliveryMethod.deliveryMethodList.empty,merchantId is:" + merchantId);
            throw new ValidateRuntimeException("ebuy.fetchEbuyDeliveryMethod.deliveryMethodList.empty", new Object[]{merchantId});
        }
        BigInteger deliveryMethodId = null;
        // 通过价格和供应商判断配送方式
        for (EbuyDeliveryMethodEntity tmpEbuyDeliveryMethodEntity : currMerEbuyDeliveryMethodList) {
            if (tmpEbuyDeliveryMethodEntity.fetchIsContain(productAmount)) {
                deliveryMethodId = tmpEbuyDeliveryMethodEntity.getId();
                break;
            }
        }

        // 根据Id查询记录详情
        EbuyDeliveryMethod ebuyDeliveryMethod = ebuyDeliveryMethodBaseDao.selectEbuyDeliveryMethodBySeqId(deliveryMethodId);
        if (ebuyDeliveryMethod == null) {
            throw new ValidateRuntimeException("ebuy.submitOrder.selectEbuyDeliveryMethodBySeqId.isnull.error");
        }

        return ebuyDeliveryMethod;
    }

    @Override
	public Integer synchronizeProductPrice() {
		return limitBuyDao.synchronizeProductPrice();
	}
}
