package com.cnfantasia.server.api.coupon.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.dao.ICouponDao;
import com.cnfantasia.server.api.coupon.entity.CarCoupon;
import com.cnfantasia.server.api.coupon.entity.CouponDto;
import com.cnfantasia.server.api.coupon.entity.CouponEntity;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.coupon.service.couponSender.AbstractCouponSender;
import com.cnfantasia.server.api.couponArea.service.ICouponAreaService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.coupon.service.ICouponBaseService;
import com.cnfantasia.server.domainbase.couponArea.entity.CouponArea;
import com.cnfantasia.server.domainbase.couponArea.service.ICouponAreaBaseService;
import com.cnfantasia.server.domainbase.couponExchange.entity.CouponExchange;
import com.cnfantasia.server.domainbase.couponExchange.service.ICouponExchangeBaseService;
import com.cnfantasia.server.domainbase.couponProduct.dao.ICouponProductBaseDao;
import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;
import com.cnfantasia.server.domainbase.dredgeCouponConfig.entity.DredgeCouponConfig;
import com.cnfantasia.server.domainbase.dredgeCouponConfig.service.IDredgeCouponConfigBaseService;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.service.IEbuyDeliveryOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.domainbase.userCoupon.service.IUserCouponBaseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shiyl on 2016/4/14.
 */
public class CouponService implements ICouponService {

    private ICouponBaseService couponBaseService;
    private IUuidManager uuidManager;
    private ICouponAreaBaseService couponAreaBaseService;
    private ICouponDao couponDao;
    private ICouponAreaService couponAreaService;
    private IDredgeCouponConfigBaseService dredgeCouponConfigBaseService;
    private ICouponExchangeBaseService couponExchangeBaseService;
    private IUserCouponBaseService userCouponBaseService;
    private AbstractCouponSender cashCouponSender;
    private ICouponProductBaseDao couponProductBaseDao;
    private IEbuyOrderBaseService ebuyOrderBaseService;
    private ISysParamManager sysParamManager;
    private IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService;

    @Override
    @Transactional
    public Integer addCoupon(CouponEntity coupon) {
        coupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_coupon));
        coupon.setCouponStatus(1);
        coupon.setSendCount(0);
        coupon.setMaxDiscountPercent(100);
        if (coupon.getUseEndDateType() != 1) {
            coupon.setUseEndDate(DateUtil.formatDay.get().format(new Date()));
        }
        coupon = dealCoupon(coupon);
        couponBaseService.insertCoupon(coupon);

        //维修券会有配置
        if (coupon.getUseType().equals(CouponUseTypeConstant.REPAIR)) {
            DredgeCouponConfig config = new DredgeCouponConfig();
            BigInteger configId = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_coupon_config);
            config.setId(configId);
            config.setCouponId(coupon.getId());
            config.setCouponFeeType(coupon.getCouponFeeType());
            config.setCommunitySupplyTypeId(coupon.getCommunitySupplyTypeId());
            config.setSupportLevel(coupon.getCommunitySupplyTypeId().equals(new BigInteger("-1")) ? 1 : 2);
            dredgeCouponConfigBaseService.insertDredgeCouponConfig(config);
        }

        //处理发券范围
        Set<CouponArea> couponAreas = new HashSet<CouponArea>();
        CouponArea couponArea = null;
        if (coupon.getSendAreaType().equals(2) || coupon.getSendAreaType().equals(3)) {
            Set<BigInteger> target = null;
            if (coupon.getSendAreaType().equals(2)) {
                target = coupon.getCityIds();
            }else if (coupon.getSendAreaType().equals(3)) {
                target = coupon.getBuildingIds();
            }
            if (target == null) {
                throw new BusinessRuntimeException("couponController.cityOrBuilding.empty");
            }
            List<BigInteger> couponAreaIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_coupon_area, target.size());
            int count=0;
            for (BigInteger city : target) {
                couponArea = new CouponArea();
                couponArea.setAreaId(city);
                couponArea.settCouponFId(coupon.getId());
                couponArea.setId(couponAreaIds.get(count++));
                couponArea.setSys0AddUser(coupon.getSys0AddUser());
                couponAreas.add(couponArea);
            }
            List<CouponArea> list = new ArrayList<CouponArea>();
            list.addAll(couponAreas);
            couponAreaBaseService.insertCouponAreaBatch(list);
        } else if (!coupon.getSendAreaType().equals(1)){
            throw new BusinessRuntimeException("couponController.wrong.sendAreaType");
        }
        return 1;
    }
    
    private CouponEntity dealCoupon(CouponEntity coupon){
    	if(!(coupon.getUseType().equals(CouponUseTypeConstant.COMMUNITY_PRODUCT)||coupon.getUseType().equals(CouponUseTypeConstant.HOME_PRODUCT))){
    		coupon.setReceiveScene(null);
    		coupon.setLinkUrl(null);
    	} else {
    		// 删除old product
    		if(null!=coupon.getId()){
    			couponDao.delCouponProductsByCouponId(coupon.getId());
    		}
    		String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    		List<CouponProduct> insertCouponProduct = new ArrayList<CouponProduct>();
    		String[] productIds = coupon.getProductIds();
			List<BigInteger> cpIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_coupon_product, productIds.length);
			for(int i=0; i<productIds.length; i++){
				CouponProduct couponProduct = new CouponProduct();
				couponProduct.setId(cpIdList.get(i));
				couponProduct.setCouponId(coupon.getId());
				if(coupon.getUseType().equals(CouponUseTypeConstant.COMMUNITY_PRODUCT)){
					couponProduct.setShelfId(new BigInteger(productIds[i]));
	    		} else if(coupon.getUseType().equals(CouponUseTypeConstant.HOME_PRODUCT)){
	    			couponProduct.setDredgeProductId(new BigInteger(productIds[i]));
	    		}
				couponProduct.setSys0AddUser(coupon.getSys0AddUser());
				couponProduct.setSys0AddTime(now);
				couponProduct.setSys0DelState(0);
				
				insertCouponProduct.add(couponProduct);
			}
			if(insertCouponProduct.size()>0){
				couponProductBaseDao.insertCouponProductBatch(insertCouponProduct);
			}
    	}
    	return coupon;
    }

    @Override
    @Transactional
    public Integer updCoupon(CouponEntity coupon) {
        couponAreaService.delCouponAreaByCouponId(coupon.getId());
        coupon = dealCoupon(coupon);
        couponBaseService.updateCoupon(coupon);

        if (coupon.getUseType().equals(CouponUseTypeConstant.REPAIR)) {
            DredgeCouponConfig config = new DredgeCouponConfig();
            config.setId(coupon.getDredgeCouponConfigId());
            config.setCouponId(coupon.getId());
            config.setCouponFeeType(coupon.getCouponFeeType());
            config.setCommunitySupplyTypeId(coupon.getCommunitySupplyTypeId());
            config.setSupportLevel(coupon.getCommunitySupplyTypeId().equals(new BigInteger("-1")) ? 1 : 2);
            dredgeCouponConfigBaseService.updateDredgeCouponConfig(config);
        }
        Set<CouponArea> couponAreas = new HashSet<CouponArea>();
        if (coupon.getSendAreaType().equals(2) || coupon.getSendAreaType().equals(3)) {
            Set<BigInteger> target = null;
            if (coupon.getSendAreaType().equals(2)) {
                target = coupon.getCityIds();
            }else if (coupon.getSendAreaType().equals(3)) {
                target = coupon.getBuildingIds();
            }
            if (target == null) {
                throw new ValidateRuntimeException("couponController.cityOrBuilding.empty");
            }
            List<BigInteger> couponAreaIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_coupon_area, target.size());
            int count=0;
            for (BigInteger city : target) {
            	CouponArea couponArea = new CouponArea();
                couponArea.setAreaId(city);
                couponArea.settCouponFId(coupon.getId());
                couponArea.setId(couponAreaIds.get(count++));
                couponArea.setSys0AddUser(coupon.getSys0UpdUser());
                couponAreas.add(couponArea);
            }
            List<CouponArea> list = new ArrayList<CouponArea>();
            list.addAll(couponAreas);
            couponAreaBaseService.insertCouponAreaBatch(list);
        } else if (!coupon.getSendAreaType().equals(1)){
            throw new BusinessRuntimeException("couponController.wrong.sendAreaType");
        }
        return 1;
    }

    @Override
    public List<CouponDto> getCouponListByCondition(Map<String,Object> paramMap, PageModel pageModel) {
        return couponDao.getCouponListByCondition(paramMap, pageModel);
    }


    @Override
    public List<UserCoupon> getUserCouponList(Map<String, Object> paramMap) {
        return couponDao.getUserCouponList(paramMap);
    }

    @Override
    public Integer updateCouponStatusClosedBatch(Map<String, Object> paramMap) {
        return couponDao.updateCouponStatusClosedBatch(paramMap);
    }

    @Override
    public List<Coupon> getCouponListCanSendToUser(int couponType, BigInteger roomId) {
        Map<String, Object> param = new HashMap();
        param.put("couponType", couponType);
        param.put("roomId", roomId);
        return couponDao.getCouponListCanSendToUser(param);
    }

    @Override
    public synchronized String couponExchange(String code, BigInteger userId) {
        CouponExchange exchange = new CouponExchange();
        exchange.setExchangeCode(code);
        exchange.setStatus(1);
        List<CouponExchange> exchangeList = couponExchangeBaseService.getCouponExchangeByCondition(MapConverter.toMap(exchange));
        if (DataUtil.isEmpty(exchangeList)) {
            throw new BusinessRuntimeException("couponService.couponExchange.noSuchCode");
        }
        exchange = exchangeList.get(0);
        BigInteger couponId = exchange.getCouponId();
        Coupon coupon = couponBaseService.getCouponBySeqId(couponId);
        if (coupon.getUseEndDate().compareTo(DateUtil.formatDay.get().format(new Date())) < 0) {
            throw new BusinessRuntimeException("couponService.couponExchange.overTime");
        }
        //更改本条记录
        exchange.setStatus(2);
        exchange.setUserId(userId);
        couponExchangeBaseService.updateCouponExchange(exchange);
        //给用户添加消费券
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_coupon));
        userCoupon.settCouponFId(exchange.getCouponId());
        userCoupon.settUserFId(userId);
        userCoupon.settEbuyOrderFId(BigInteger.ZERO);
        userCoupon.setStatus(1);
        userCoupon.setUseEndDate(coupon.getUseEndDate());
        userCouponBaseService.insertUserCoupon(userCoupon);
        return coupon.getCouponName();
    }

    @Override
    public List<Map<String, Long>> qryCanCouponReceiveScene(BigInteger userId, BigInteger gbId) {
        List<Map<String, Long>> couponSceneReceiveThing = couponDao.getCouponSceneReceiveThing(userId);
        BigInteger cityId = couponDao.getCityIdByGb(gbId);
        List<Long> scenes = couponDao.qryCanCouponReceiveScene(userId, cityId, gbId);
        for (Map<String, Long> stringLongMap : couponSceneReceiveThing) {
            if (scenes.contains(stringLongMap.get("couponSceneId"))) {
                stringLongMap.put("canReceive", 1L);
            } else {
                stringLongMap.put("canReceive", 0L);
            }
        }
        return couponSceneReceiveThing;
    }

    @Override
    public List<UserCouponEntity> receiveSceneCoupon(BigInteger userId, Integer sceneId, BigInteger gbId) {
        BigInteger cityId = couponDao.getCityIdByGb(gbId);
        List<Coupon> couponEntityList = couponDao.getCanReceiveCouponBySceneId(userId, sceneId, cityId, gbId);
        if (DataUtil.isEmpty(couponEntityList)) {
            throw new BusinessRuntimeException("couponService.receiveSceneCoupon.noCoupon");
        }
        List<UserCouponEntity> userCouponEntityList = new ArrayList<>(couponEntityList.size());
        UserCouponEntity entity = null;
        for (Coupon coupon : couponEntityList) {
            entity = new UserCouponEntity();
            entity.setDiscountMoney(coupon.getDiscountMoney());
            entity.setLeastSpendUse(coupon.getLeastSpendUse());
            entity.setUseEndDate(coupon.getUseEndDateType() == 1 ? coupon.getUseEndDate() :
                    DateUtil.formatDay.get().format(DateUtil.getNextDate(new Date(), Calendar.DAY_OF_YEAR, coupon.getUseDateNumber())));
            entity.setCouponName(coupon.getCouponName());
            entity.setUseType(coupon.getUseType());
            entity.setLinkUrl(coupon.getLinkUrl());
            userCouponEntityList.add(entity);
        }
        cashCouponSender.sendCoupons(couponEntityList, userId, BigInteger.ZERO);
        return userCouponEntityList;
    }
    
    @Override
    public List<Map<String, Object>> getCouponProductList(BigInteger couponId) {
        return couponDao.getCouponProductList(couponId);
    }
    
    /**
     * 根据用户id查询停车券
     * @param userId
     * @return
     */
    @Override
    public CarCoupon selectCarCouponByUserId(BigInteger userId){
    	return couponDao.selectCarCouponByUserId(userId);
    }
    
    /**
     * 根据用户id查询可用的停车券
     * @param userId
     * @return
     */
    @Override
    public CarCoupon selectAvailableCarCouponByUserId(BigInteger userId){
    	return couponDao.selectAvailableCarCouponByUserId(userId);
    }

    @Override
    public List<Coupon> getShareOrderCouponCanSend(BigInteger orderId) {
        EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
        //校验订单类型
        if (order == null || !Objects.equals(order.getType(), EbuyDict.EbuyOrder_Type.EBuy_Product)) {
            return Collections.emptyList();
        }
        //校验运单有没有楼下店
        EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
        deliveryOrder.settEbuyOrderFId(orderId);
        List<EbuyDeliveryOrder> deliveryOrderList = ebuyDeliveryOrderBaseService.getEbuyDeliveryOrderByCondition(MapConverter.toMap(deliveryOrder));
        String storeId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
        boolean hasExperienceStore = false;
        for (EbuyDeliveryOrder ebuyDeliveryOrder : deliveryOrderList) {
            if (ebuyDeliveryOrder.gettSupplyMerchantFId().toString().equals(storeId)) {
                hasExperienceStore = true;
                break;
            }
        }
        if (!hasExperienceStore) {
            return Collections.emptyList();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("amount", order.getAmount());
        param.put("orderId", orderId);
        param.put("useCoupon", order.getTotalCouponAmount() != null && order.getTotalCouponAmount() > 0);
        return couponDao.getShareOrderCouponCanSend(param);
    }

    public ICouponBaseService getCouponBaseService() {
        return couponBaseService;
    }

    public void setCouponBaseService(ICouponBaseService couponBaseService) {
        this.couponBaseService = couponBaseService;
    }

    public IUuidManager getUuidManager() {
        return uuidManager;
    }

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    public ICouponAreaBaseService getCouponAreaBaseService() {
        return couponAreaBaseService;
    }

    public void setCouponAreaBaseService(ICouponAreaBaseService couponAreaBaseService) {
        this.couponAreaBaseService = couponAreaBaseService;
    }

    public ICouponDao getCouponDao() {
        return couponDao;
    }

    public void setCouponDao(ICouponDao couponDao) {
        this.couponDao = couponDao;
    }

    public ICouponAreaService getCouponAreaService() {
        return couponAreaService;
    }

    public void setCouponAreaService(ICouponAreaService couponAreaService) {
        this.couponAreaService = couponAreaService;
    }

    public void setDredgeCouponConfigBaseService(IDredgeCouponConfigBaseService dredgeCouponConfigBaseService) {
        this.dredgeCouponConfigBaseService = dredgeCouponConfigBaseService;
    }

    public void setCouponExchangeBaseService(ICouponExchangeBaseService couponExchangeBaseService) {
        this.couponExchangeBaseService = couponExchangeBaseService;
    }

    public void setUserCouponBaseService(IUserCouponBaseService userCouponBaseService) {
        this.userCouponBaseService = userCouponBaseService;
    }

	public void setCouponProductBaseDao(ICouponProductBaseDao couponProductBaseDao) {
		this.couponProductBaseDao = couponProductBaseDao;
	}

    public void setCashCouponSender(AbstractCouponSender cashCouponSender) {
        this.cashCouponSender = cashCouponSender;
    }

    public void setEbuyOrderBaseService(IEbuyOrderBaseService ebuyOrderBaseService) {
        this.ebuyOrderBaseService = ebuyOrderBaseService;
    }

    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    public void setEbuyDeliveryOrderBaseService(IEbuyDeliveryOrderBaseService ebuyDeliveryOrderBaseService) {
        this.ebuyDeliveryOrderBaseService = ebuyDeliveryOrderBaseService;
    }
}
