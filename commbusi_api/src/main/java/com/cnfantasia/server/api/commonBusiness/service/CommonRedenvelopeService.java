/**   
* Filename:    CommonRedenvelopeService.java   
* @version:    1.0  
* Create at:   2014年6月30日 上午6:59:30   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.dao.ICommonRedenvelopeDao;
import com.cnfantasia.server.api.ebuyorder.dao.IEbuyorderDao;
import com.cnfantasia.server.api.payment.serviceUntran.IPayConfigService;
import com.cnfantasia.server.api.payment.util.PayConfigUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.constant.RedenvelopeDict;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.payCoupon.dao.IPayCouponBaseDao;
import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;
import com.cnfantasia.server.domainbase.payRedEnvelope.dao.IPayRedEnvelopeBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.dao.IPayRedEnvelopeDetailBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Filename:    CommonRedenvelopeService.java
 * @version:    1.0.0
 * Create at:   2014年6月30日 上午6:59:30
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月30日       shiyl             1.0             1.0 Version
 */
public class CommonRedenvelopeService implements ICommonRedenvelopeService{
	private ICommonRedenvelopeDao commonRedenvelopeDao;
	public void setCommonRedenvelopeDao(ICommonRedenvelopeDao commonRedenvelopeDao) {
		this.commonRedenvelopeDao = commonRedenvelopeDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	private IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao;
	public void setPayRedEnvelopeBaseDao(IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao) {
		this.payRedEnvelopeBaseDao = payRedEnvelopeBaseDao;
	}

	@Resource
	private IPayCouponBaseDao payCouponBaseDao;
	@Resource
	private IPayRedEnvelopeDetailBaseDao payRedEnvelopeDetailBaseDao;
	@Resource
	private IPayConfigService payConfigService;
	@Resource
	private IEbuyorderDao ebuyorderDao;

	@Override
	public Long getTotalHbBalance(BigInteger userId) {
		// 查询总金额
		Long balance = commonRedenvelopeDao.selectTotalHbBalance(userId, 0);
		return balance;
	}

	@Override
	public Long getTotalHbBalance(BigInteger userId, int type) {
		// 查询总金额
		Long balance = commonRedenvelopeDao.selectTotalHbBalance(userId, type);
		return balance;
	}

	@Override
	public void addPayRedEnvelope(BigInteger userId,BigInteger roomId,BigInteger fromId,Integer fromType,Long moneyLong) {
		BigInteger payRedEnvelopeAddId = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope);
		PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
		payRedEnvelope.setAmountAvaible(moneyLong);
		payRedEnvelope.setAmountTotal(moneyLong);
		payRedEnvelope.setFromId(fromId);
		payRedEnvelope.setFromTime(dualDao.getNowTime());
		payRedEnvelope.setFromType(fromType);// 来源类型
		payRedEnvelope.setId(payRedEnvelopeAddId);
		payRedEnvelope.setOutDate(null);// 不失效
		payRedEnvelope.setStatus(RedenvelopeDict.PayRedEnvelope_Status.CAN_USE);
		payRedEnvelope.setUserId(userId);// 拥有者
		payRedEnvelope.setRoomId(roomId);
		payRedEnvelope.setConverterId(userId);// 兑换人
		int res = payRedEnvelopeBaseDao.insertPayRedEnvelope(payRedEnvelope);
		if (res <= 0) {
			throw new BusinessRuntimeException("CommonRedenvelopeService.addPayRedEnvelope.insertPayRedEnvelope.failed");
		}
	}

	@Override
	public Integer updateRedenvelopeByUserId(BigInteger userId, long JFBAmount, BigInteger orderId) {
		//查询用户可用粮票信息
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("status",1);
		paraMap.put("userId",userId);
		List<PayRedEnvelope> payRedEnvelopes = payRedEnvelopeBaseDao.selectPayRedEnvelopeByCondition(paraMap, false);


		List<PayRedEnvelopeDetail> payRedEnvelopeDetailAddBatchList = new ArrayList<PayRedEnvelopeDetail>();
		List<PayRedEnvelope> PayRedEnvelopeUpdateBatchList = new ArrayList<PayRedEnvelope>();
		if(payRedEnvelopes != null && payRedEnvelopes.size() > 0) {
			//优惠表id t_pay_coupon
			HashMap<String, Object> paraMapCoupon = new HashMap<String, Object>();
			paraMapCoupon.put("tEbuyOrderFId",orderId);
			List<PayCoupon> payCouponsList = payCouponBaseDao.selectPayCouponByCondition(paraMapCoupon, false);
			int resCount = 0;
			BigInteger payCouponAddId = null;
			if(payCouponsList != null && payCouponsList.size() > 0) {
				payCouponAddId = payCouponsList.get(0).getId();
			} else {
				throw new BusinessRuntimeException("订单优惠表信息不匹配，更新失败！");
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("orderId", orderId);
			// paramMap.put("merchantId", CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Experience_Store_Id));
			List<BigInteger> merchantIdList = ebuyorderDao.selectMerchantIdListByOrderId(paramMap);
			boolean hasContainExperienceStoreId = merchantIdList.contains(new BigInteger(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Experience_Store_Id)));
			// hasContainExperienceStoreId=true时，表示包含体验店的商品，此时只能用体验店专用粮票 added by wenfq 2019-09-19
			if(hasContainExperienceStoreId && merchantIdList.size() == 1){
				Collections.sort(payRedEnvelopes, new Comparator<PayRedEnvelope>(){
					@Override
					public int compare(PayRedEnvelope o1, PayRedEnvelope o2) {
						//订单包只含体验店商品时，体验店粮票要优先使用
						return o2.getFromType().compareTo(o1.getFromType());
					}
				});
			}

			int i = 0;
			while(JFBAmount > 0 && (i < payRedEnvelopes.size())) {
				PayRedEnvelope payRedEnvelope = payRedEnvelopes.get(i++);

				if (!hasContainExperienceStoreId && //订单不包含体验店的商品，可用粮票要去掉体验店的粮票
						payRedEnvelope.getFromType().intValue() == RedenvelopeDict.PayRedEnvelope_FromType.Experience_Store) {
					continue;
				}

				long amountAvaible = payRedEnvelope.getAmountAvaible();
				if (amountAvaible > 0) {
					long afterAmount = 0l;//支付后剩余金额
					long payAmount = 0l;//每次支付的金额
					if(JFBAmount >= amountAvaible) {
						JFBAmount = JFBAmount - amountAvaible;
						afterAmount = 0;
						payAmount = amountAvaible;
					} else {
						afterAmount = amountAvaible - JFBAmount;
						payAmount = JFBAmount;
						JFBAmount = 0;
					}
					payRedEnvelope.setAmountAvaible(afterAmount);
					PayRedEnvelopeUpdateBatchList.add(payRedEnvelope);

					PayRedEnvelopeDetail payRedEnvelopeDetail = new PayRedEnvelopeDetail();
					payRedEnvelopeDetail.setAnount(payAmount);
					payRedEnvelopeDetail.setConsumTime(dualDao.getNowTime());
					//payRedEnvelopeDetail.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope_detail));
					payRedEnvelopeDetail.setPayRedEnvelopeId(payRedEnvelope.getId());//粮票Id
					payRedEnvelopeDetail.settPayCouponFId(payCouponAddId);//优惠信息Id
					payRedEnvelopeDetail.setUserId(userId);
					payRedEnvelopeDetail.setSys0AddUser(userId);
					payRedEnvelopeDetailAddBatchList.add(payRedEnvelopeDetail);
				}
			}

			List<BigInteger> payRedEnvelopeDetailAddIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope_detail,payRedEnvelopeDetailAddBatchList.size());
			for (int j = 0;j<payRedEnvelopeDetailAddBatchList.size();j++) {
				PayRedEnvelopeDetail payRedEnvelopeDetail = payRedEnvelopeDetailAddBatchList.get(j);
				payRedEnvelopeDetail.setId(payRedEnvelopeDetailAddIdList.get(j));
			}

			//批量新增t_pay_red_envelope_detail
			int resCount02=payRedEnvelopeDetailBaseDao.insertPayRedEnvelopeDetailBatch(payRedEnvelopeDetailAddBatchList);
			if(resCount02!=payRedEnvelopeDetailAddBatchList.size()){
				throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.insertPayRedEnvelopeDetailBatch.resCount.notEqual");
			}

			int resCount03 = payRedEnvelopeBaseDao.updatePayRedEnvelopeBatch(PayRedEnvelopeUpdateBatchList);
			if(resCount03 == 0){
				throw new BusinessRuntimeException("粮票使用更新失败！-->PayRedEnvelope");
			}

			return 1;
		}
		return 0;
	}

	@Override
	public void updateOrderEntityByJFB(EbuyOrder ebuyOrder, Long jfbAmt, Long preferAmt) {
		if (jfbAmt == null || jfbAmt < 0) {//金额为0或者负数，则直接返回
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.lessOrEqual0");
		}
		if(ebuyOrder==null){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.ebuyOrder.isNull");
		}

		//订单总金额
		Long succAmount = ebuyOrder.getAmount() + ebuyOrder.getTotalCouponAmount();

		//校验粮票金额是否大于订单金额
		if (jfbAmt > (succAmount - preferAmt)) {//粮票金额不能大于订单总额
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.biggerThan.orderAmount");
		}
		//校验余额是否充足
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderId", ebuyOrder.getId());
		List<BigInteger> merChantIdList = ebuyorderDao.selectMerchantIdListByOrderId(paramMap);
		int type = merChantIdList.contains(new BigInteger(CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Experience_Store_Id))) ? 1 : 0;
		// type=1时，表示包含体验店的商品，此时只能用体验店专用粮票 added by wenfq 2019-09-19
		Long hbBalance = commonRedenvelopeDao.selectTotalHbBalance(ebuyOrder.getBuyerId(), type);
		if(type == 1 && hbBalance <= jfbAmt) {//体验店的票用完了，还可以用全国的券来抵
			type = 0;
			hbBalance += commonRedenvelopeDao.selectTotalHbBalance(ebuyOrder.getBuyerId(), type);
		}

		if(hbBalance==null||jfbAmt.compareTo(hbBalance)>0){
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbBalance.notEnough.error",new Object[]{PriceUtil.div100(hbBalance)});
		}
		//粮票使用百分比判断 syl-add-2015-11-10 15:13:19
		{
			Double payPercent = payConfigService.getPayConfigHbPercent(ebuyOrder.getType());
			Long maxPercentMoney = PayConfigUtil.calculateMaxCouponAmount(succAmount - preferAmt, payPercent);
			if(jfbAmt.compareTo(maxPercentMoney)>0){
				throw new BusinessRuntimeException("payConfigService.hbPercentUse.toMuch",new Object[]{PriceUtil.div100(maxPercentMoney)});
			}
		}
		ebuyOrder.setAmount(succAmount - jfbAmt - preferAmt);
		ebuyOrder.setTotalCouponAmount(preferAmt + jfbAmt);
	}

	@Override
	public void updatePayCouponByJFB(EbuyOrder ebuyOrder, Long jfbAmt) {
		//更新优惠表信息
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tEbuyOrderFId",ebuyOrder.getId());
		List<PayCoupon> payCouponsList = payCouponBaseDao.selectPayCouponByCondition(paraMap, false);
		int resCount = 0;
		if(payCouponsList != null && payCouponsList.size() > 0) {
			PayCoupon payCoupon = payCouponsList.get(0);
			payCoupon.setAmount(jfbAmt);
			payCoupon.setSys0UpdTime(DateUtils.getCurrentDate());
			resCount = payCouponBaseDao.updatePayCoupon(payCoupon);
		} else {
			PayCoupon payCoupon = new PayCoupon();
			payCoupon.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_coupon));
			payCoupon.setAmount(jfbAmt);
			payCoupon.settEbuyOrderFId(ebuyOrder.getId());
			payCoupon.setType(1);//1粮票  2 优惠券
			payCoupon.setSys0AddTime(DateUtils.getCurrentDate());
			payCoupon.setSys0AddUser(ebuyOrder.getBuyerId());
			resCount = payCouponBaseDao.insertPayCoupon(payCoupon);
		}
		if(resCount < 0) {
			throw new BusinessRuntimeException("CommonEbuyService.updateOrderByHb.hbMoney.payCouponANDorder");
		}
	}
}
