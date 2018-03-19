package com.cnfantasia.server.api.ebuy.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonEbuyService;
import com.cnfantasia.server.api.ebuy.dao.IEbuyDao;
import com.cnfantasia.server.api.ebuy.dao.IGroupPurchaseDao;
import com.cnfantasia.server.api.ebuy.entity.EbuyFilmTicket;
import com.cnfantasia.server.api.ebuy.entity.FightgroupProductEntity;
import com.cnfantasia.server.api.ebuy.util.DeliveryNoGenerator;
import com.cnfantasia.server.api.ebuy.util.OrderNoGenerator;
import com.cnfantasia.server.api.ebuyorder.entity.OrderBuyInfo;
import com.cnfantasia.server.api.ebuyorder.service.IEbuyorderService;
import com.cnfantasia.server.api.payment.constant.EbuyDict;
import com.cnfantasia.server.api.pub.propertiyUtil.SmsPropertyUtil;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.dao.IEbuyDeliveryAddressBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.dao.IEbuyDeliveryOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrder.entity.EbuyDeliveryOrder;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.dao.IEbuyDeliveryOrderProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;
import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;
import com.cnfantasia.server.domainbase.ebuyFightOrder.service.IEbuyFightOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.dao.IEbuyHandleAddressBaseDao;
import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;
import com.cnfantasia.server.domainbase.ebuyOrder.dao.IEbuyOrderBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao.IEbuyOrderHasTEbuyProductBaseDao;
import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductFightGroups.service.IEbuyProductFightGroupsBaseService;
import com.cnfantasia.server.domainbase.user.dao.UserBaseDao;

public class GroupPurchaseService implements IGroupPurchaseService{
	private IGroupPurchaseDao groupPurchaseDao;
	public void setGroupPurchaseDao(IGroupPurchaseDao groupPurchaseDao) {
		this.groupPurchaseDao = groupPurchaseDao;
	}
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IEbuyDao ebuyDao;
	public void setEbuyDao(IEbuyDao ebuyDao) {
		this.ebuyDao = ebuyDao;
	}
	
	private IEbuyOrderBaseDao ebuyOrderBaseDao;
	public void setEbuyOrderBaseDao(IEbuyOrderBaseDao ebuyOrderBaseDao) {
		this.ebuyOrderBaseDao = ebuyOrderBaseDao;
	}
	private UserBaseDao userBaseDao;
	public void setUserBaseDao(UserBaseDao userBaseDao) {
		this.userBaseDao = userBaseDao;
	}
	private IEbuyFightOrderBaseService ebuyFightOrderBaseService;
	public void setEbuyFightOrderBaseService(IEbuyFightOrderBaseService ebuyFightOrderBaseService) {
		this.ebuyFightOrderBaseService = ebuyFightOrderBaseService;
	}

	private IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao;
	public void setEbuyOrderHasTEbuyProductBaseDao(IEbuyOrderHasTEbuyProductBaseDao ebuyOrderHasTEbuyProductBaseDao) {
		this.ebuyOrderHasTEbuyProductBaseDao = ebuyOrderHasTEbuyProductBaseDao;
	}
	private IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao;
	public void setEbuyDeliveryOrderBaseDao(IEbuyDeliveryOrderBaseDao ebuyDeliveryOrderBaseDao) {
		this.ebuyDeliveryOrderBaseDao = ebuyDeliveryOrderBaseDao;
	}
	private IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao;
	public void setEbuyDeliveryOrderProductBaseDao(IEbuyDeliveryOrderProductBaseDao ebuyDeliveryOrderProductBaseDao) {
		this.ebuyDeliveryOrderProductBaseDao = ebuyDeliveryOrderProductBaseDao;
	}
	// 收货地址，手动输入
	private IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao;
	public void setEbuyHandleAddressBaseDao(IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao) {
		this.ebuyHandleAddressBaseDao = ebuyHandleAddressBaseDao;
	}


	private ICommonEbuyService commonEbuyService;
	public void setCommonEbuyService(ICommonEbuyService commonEbuyService) {
		this.commonEbuyService = commonEbuyService;
	}


	private IEbuyorderService ebuyorderService;
	public void setEbuyorderService(IEbuyorderService ebuyorderService) {
		this.ebuyorderService = ebuyorderService;
	}

	private ICommMobileService commMobileService;
	public void setCommMobileService(ICommMobileService commMobileService) {
		this.commMobileService = commMobileService;
	}

	@Override
	public List<FightgroupProductEntity> selectproList(Map<String, Object> paramMap){
		return groupPurchaseDao.qryproList(paramMap);
	}
	@Override
	public FightgroupProductEntity selectProDetail(BigInteger productId, Integer appType){
		return groupPurchaseDao.qryproDeatil(productId, appType);
	}
	@Override
	public Map<String, Object> saveEbuyOrderFightNew(FightgroupProductEntity fightProduct, Long hbAmount, Set<BigInteger> couponIdSet,
			Integer subChannelId,BigInteger userId,String userName,String phone, Integer buyNum) {
		//是否重复生成订单
//		if(groupPurchaseDao.getUserFightNumMsg(userId)!=null){
//			return groupPurchaseDao.getUserFightNumMsg(userId).getId();
//		}
		if (buyNum == null || buyNum < 1) {
			buyNum = 1;
		}
		// 校验支付优惠信息
		if (hbAmount != null && hbAmount > 0 && couponIdSet != null && couponIdSet.size() > 0) {
			throw new BusinessRuntimeException("ebuy.submitOrder.coupon.multi.error");
		}

		//获取拼购价格
		if(fightProduct==null){
			throw new BusinessRuntimeException("ebuy.fightproduct.is.not.exist");
		}
		//校验库存
//		checkLeftCount(fightProduct.getId(),1,1,fightProduct.getFightProductId());//默认为电商商品
		int res = 0;
		int fight = 0;
		String nowTime = dualDao.getNowTime();
		BigInteger resOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order);
		
		// 新增订单表记录
		EbuyOrder ebuyOrder = new EbuyOrder();
		ebuyOrder.setType(EbuyDict.EbuyOrder_Type.EBuy_Product);//订单类型：电商商品
		ebuyOrder.setId(resOrderId);
		ebuyOrder.setBuyerId(userId);
		ebuyOrder.setCurrRoomId(userBaseDao.selectUserBySeqId(userId) != null ?
				userBaseDao.selectUserBySeqId(userId).getDefaultRoomId() : null);
		ebuyOrder.setBuyTime(nowTime);
		ebuyOrder.setCreater(userId);
		ebuyOrder.setOrderNo(OrderNoGenerator.getOrderNo(resOrderId));
		ebuyOrder.setPayMethod(null);
		ebuyOrder.setPayTime(null);
		ebuyOrder.setDelivPeopleName(userName);
		ebuyOrder.setDelivPhone(phone);
		ebuyOrder.setStatus(EbuyDict.EbuyOrder_Status.DaiFuKuan);
		ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Not_Pay);//支付状态为待支付
		ebuyOrder.setDelivStatus(EbuyDict.EbuyOrder_DelivStatus.Not_Deliv);//发货状态为未发货
		//收货地址为岗亭
		ebuyOrder.setDelivAddressArea(fightProduct.getZitiDian().getName());
		ebuyOrder.setDelivAddressDetail(fightProduct.getZitiDian().getDelivAddress());
		//联系人为提交的联系人电话
		ebuyOrder.setDelivPeopleName(userName);
		ebuyOrder.setDelivPhone(phone);
		ebuyOrder.setDelivTargetType(2);//设为手工输入
		ebuyOrder.setTotalDeliveryFee(0L);//总配送费
		ebuyOrder.setAmount(fightProduct.getFightPrice() * buyNum); //应付金额

		if (ebuyOrder.getAmount().compareTo(0L) == 0) {
			ebuyOrder.setPayStatus(EbuyDict.EbuyOrder_PayStatus.Pay_Success);
		}
		res = ebuyOrderBaseDao.insertEbuyOrder(ebuyOrder);

		if (ebuyOrder.getAmount().compareTo(0L) == 0) {
			commonEbuyService.paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
		}

		//新增订单商品关系表,本期不考虑多个商品
		List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductAddList = new ArrayList<EbuyOrderHasTEbuyProduct>();
		BigInteger orderHasProductId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_order_has_t_ebuy_product);
		{
			EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct  = new EbuyOrderHasTEbuyProduct();
			ebuyOrderHasTEbuyProduct.setId(orderHasProductId);
			ebuyOrderHasTEbuyProduct.settEbuyOrderFId(resOrderId);
			ebuyOrderHasTEbuyProduct.settEbuyProductFId(BigInteger.valueOf(fightProduct.getProductShelfId()));
			ebuyOrderHasTEbuyProduct.settEbuyProductSpecFId(fightProduct.getProductSpecId());// 商品规格Id
			ebuyOrderHasTEbuyProduct.setProductQty(Long.valueOf(buyNum));//默认商品数量为1
			ebuyOrderHasTEbuyProduct.setProductPrice(fightProduct.getFightPrice());//拼购价格
			ebuyOrderHasTEbuyProduct.setProductPricePoint(null);// 所需积分为空
			ebuyOrderHasTEbuyProduct.setSupplyMerchantId(fightProduct.getMerchantName().getId());// 供应商Id
			ebuyOrderHasTEbuyProduct.setPurchasePrice(fightProduct.getPurchasePrice());// 采购价
			ebuyOrderHasTEbuyProductAddList.add(ebuyOrderHasTEbuyProduct);
			res = ebuyOrderHasTEbuyProductBaseDao.insertEbuyOrderHasTEbuyProductBatch(ebuyOrderHasTEbuyProductAddList);
			if(res!=ebuyOrderHasTEbuyProductAddList.size()){
				throw new BusinessRuntimeException("ebuy.submitOrder.insertEbuyOrderHasTEbuyProduct.error");
			}
		}

		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("orderId", resOrderId);
		resMap.put("payStatus", ebuyOrder.getPayStatus());

		//使用了消费券或粮票
		if (hbAmount != null && hbAmount.compareTo(0L) > 0) {
			Long orderLeftAmount=commonEbuyService.updateOrderByHb(userId, ebuyOrder.getId(), hbAmount);
			if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
				commonEbuyService.paySuccessOperateComm(ebuyOrder.getId(),EbuyDict.EbuyPay_PayMethod.PureRedEnvelope);
				resMap.put("payStatus", EbuyDict.EbuyOrder_PayStatus.Pay_Success);
			}
		} else if (!DataUtil.isEmpty(couponIdSet)) {
			Long orderLeftAmount = commonEbuyService.updateOrderByCopounList(userId, ebuyOrder.getId(), couponIdSet, null, null);
			if (orderLeftAmount != null && orderLeftAmount.compareTo(0L) == 0) {// 剩余应付金额直接设置订单状态为支付成功
				commonEbuyService.paySuccessOperateComm(ebuyOrder.getId(), EbuyDict.EbuyPay_PayMethod.PureSupriseGiftList);
				resMap.put("payStatus", EbuyDict.EbuyOrder_PayStatus.Pay_Success);
			}
		}

		//新增拼购订单
		{
			EbuyFightOrder ebuyFightOrder = new EbuyFightOrder();
			BigInteger fightOrderId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_fight_order);
			ebuyFightOrder.setId(fightOrderId);
			ebuyFightOrder.settEbuyProductFightGroupsFId(fightProduct.getFightProductId());
			ebuyFightOrder.settEbuyOrderFId(resOrderId);
			ebuyFightOrder.setBuyTime(nowTime);
			ebuyFightOrder.setSys0DelState(0);
			ebuyFightOrder.setDelivAddressArea(fightProduct.getZitiDian().getDelivAddress());
			ebuyFightOrder.setSys0AddTime(nowTime);
			fight = ebuyFightOrderBaseService.insertEbuyFightOrder(ebuyFightOrder);
		}
		//拼购不考虑介入购物车
		if (res <= 0 || fight<=0) {
			throw new BusinessRuntimeException("com.cnfantasia.server.api.ebuy.service.savefightOrder.error");
		}

		//添加自提点配送地址为自提点
		{
			// 新增手动输入地址
			BigInteger addressHandleAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_handle_address);
			EbuyHandleAddress ebuyHandleAddress = new EbuyHandleAddress();
			ebuyHandleAddress.setId(addressHandleAddId);
			ebuyHandleAddress.setAddressArea(fightProduct.getZitiDian().getName());
			ebuyHandleAddress.setAddressDetail(fightProduct.getZitiDian().getDelivAddress());
			res = ebuyHandleAddressBaseDao.insertEbuyHandleAddress(ebuyHandleAddress);
//			fight = ebuyDeliveryAddressBaseDao.insertEbuyDeliveryAddress(ebuyDeliveryAddress);
			if (res <= 0) {
				throw new BusinessRuntimeException("fightEbuy.addDeliveryAddress.insertEbuyDeliveryAddress.error");
			}
		}
		//生成配送单信息(拼购只有一个商品，本期不考虑多个商品拼购)
		ebuyOrder = ebuyOrderBaseDao.selectEbuyOrderBySeqId(ebuyOrder.getId());
		BigInteger ebuyDeliveryOrderAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order);
		{
			EbuyDeliveryOrder deliveryOrder = new EbuyDeliveryOrder();
			EbuyDeliveryOrderProduct deliveryOrderProduct = new EbuyDeliveryOrderProduct();
			deliveryOrder.setId(ebuyDeliveryOrderAddId);
			deliveryOrder.setBuyerId(userId);
			deliveryOrder.setCreateTime(nowTime);
			deliveryOrder.setAmount(ebuyOrder.getAmount());
			deliveryOrder.setTotalCoupon(ebuyOrder.getTotalCouponAmount() == null ? 0L : ebuyOrder.getTotalCouponAmount());
			deliveryOrder.setDeliveryId(fightProduct.getZitiDian().gettEbuyDeliveryMethodFId());//自提点的配送方式
			deliveryOrder.setDeliveryRealFee(0L);//拼购运费为0元
			deliveryOrder.setUserDeliveryType(1);
			deliveryOrder.setDeliveryNo(DeliveryNoGenerator.getDeliveryNo(ebuyDeliveryOrderAddId, fightProduct.getMerchantName().getId()));
			deliveryOrder.setStatus(EbuyDict.EbuyDeliveryOrder_Status.NotStart);// 未启动状态
			deliveryOrder.settEbuyOrderFId(resOrderId);
			deliveryOrder.settSupplyMerchantFId(fightProduct.getMerchantName().getId());
			deliveryOrder.setRevenueStatus(0);//新增是否计算收益
			deliveryOrder.setPushStatus(EbuyDict.DeliveryOrderpush_Status.NotStart);//配送单推送状态0未发送
			deliveryOrder.setUserDeliveryType(1);

			int res01=ebuyDeliveryOrderBaseDao.insertEbuyDeliveryOrder(deliveryOrder);
			if(res01<=0){
				throw new BusinessRuntimeException("ebuy.submitfightOrder.insertEbuyDeliveryOrderBatch.error");
			}
			BigInteger ebuyDeliveryOrderProductAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_ebuy_delivery_order_product);
			deliveryOrderProduct.setId(ebuyDeliveryOrderProductAddId);
			deliveryOrderProduct.settEbuyDeliveryOrderFId(ebuyDeliveryOrderAddId);
			deliveryOrderProduct.settEbuyOrderFId(resOrderId);
			deliveryOrderProduct.settEbuyOrderHasTEbuyProductFId(orderHasProductId);
			deliveryOrderProduct.settEbuyProductFId(BigInteger.valueOf(fightProduct.getProductShelfId()));
			deliveryOrderProduct.setSupplyMerchantId(fightProduct.getMerchantName().getId());
			deliveryOrderProduct.setOpType(0);
			int res02=ebuyDeliveryOrderProductBaseDao.insertEbuyDeliveryOrderProduct(deliveryOrderProduct);
			if(res02<=0){
				throw new BusinessRuntimeException("ebuy.insertEbuyDeliveryOrderProduct.fightProduct.error");
			}
		}

		// 库存减少
		ebuyDao.lockProductLeftCountByOrderId(resOrderId, userId, fightProduct.getPointType(), null);

		return resMap;
	}
	//效验库存
	private synchronized void checkLeftCount(BigInteger productId,long count,Integer pointType,
			BigInteger fightId){
		Map<String, Object> proMap = new HashMap<String, Object>();
		proMap.put("fightProductId", fightId);
		proMap.put("fightProductCount", count);
		groupPurchaseDao.updateFightCounts(proMap);
	}
	@Override
	public EbuyOrder selectUserFightMsg(BigInteger userId) {
		return groupPurchaseDao.getUserFightNumMsg(userId);
	}

}
