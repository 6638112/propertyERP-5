package com.cnfantasia.server.api.payRedEnvelope.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.propertyAccountInfo.dao.IPropertyAccountInfoDao;
import com.cnfantasia.server.api.propertyAccountInfo.entity.PropertyAccountInfoEntity;
import com.cnfantasia.server.api.user.dao.IUserDao;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.userHasPropertyCard.dao.IUserHasPropertyCardDao;
import com.cnfantasia.server.api.userHasPropertyCard.entity.UserHasPropertyCardEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.payRedEnvelope.dao.IPayRedEnvelopeBaseDao;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;
import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.dao.IPropertyCardTransactionFlowingBaseDao;
import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

/**
 * 代扣卡转粮票
 * 
 * @author liyulin
 * @version 1.0 2016年11月4日 下午1:30:46
 */
public class PayRedEnvelopeService implements IPayRedEnvelopeService{
	
	private IPropertyAccountInfoDao propertyAccountInfoDao;
	private IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao;
	private IUuidManager uuidManager;
	private IUserDao userDao;
	private IPropertyCardTransactionFlowingBaseDao propertyCardTransactionFlowingBaseDao;
	private IUserHasPropertyCardDao userHasPropertyCardDao;

	public void setPropertyAccountInfoDao(
			IPropertyAccountInfoDao propertyAccountInfoDao) {
		this.propertyAccountInfoDao = propertyAccountInfoDao;
	}

	public void setPayRedEnvelopeBaseDao(
			IPayRedEnvelopeBaseDao payRedEnvelopeBaseDao) {
		this.payRedEnvelopeBaseDao = payRedEnvelopeBaseDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void setPropertyCardTransactionFlowingBaseDao(
			IPropertyCardTransactionFlowingBaseDao propertyCardTransactionFlowingBaseDao) {
		this.propertyCardTransactionFlowingBaseDao = propertyCardTransactionFlowingBaseDao;
	}

	public void setUserHasPropertyCardDao(
			IUserHasPropertyCardDao userHasPropertyCardDao) {
		this.userHasPropertyCardDao = userHasPropertyCardDao;
	}

	/**
	 * 代扣卡转粮票
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	@Transactional
	public JsonResponse propertyCard2Envelope(BigInteger userId) {
		JsonResponse jsonResponse = new JsonResponse();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 1、查询用户代扣卡总额
		paramMap.put("tUserFId", userId);
		List<PropertyAccountInfo> propertyAccountInfos = propertyAccountInfoDao.selectPropertyAccountInfoByCondition(paramMap, false);
		List<UserHasPropertyCard> userHasPropertyCards = userHasPropertyCardDao.selectUserHasPropertyCardByUserId(userId);
		if(propertyAccountInfos==null || propertyAccountInfos.size()==0){
			jsonResponse.setMessage("代扣卡余额为0，不能转粮票！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		} else {
			String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
			PropertyAccountInfo propertyAccountInfo = propertyAccountInfos.get(0);
			Long amountTotal = propertyAccountInfo.getBalanceAmt();
			if(amountTotal==null || amountTotal<=0){
				jsonResponse.setMessage("代扣卡余额为0，不能转粮票！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			} else {
				propertyAccountInfo.setBalanceAmt(0L);
				// 2、更新t_property_account_info
				PropertyAccountInfoEntity propertyAccountInfoEntity = new PropertyAccountInfoEntity(propertyAccountInfo);
				propertyAccountInfoEntity.setOldUpdateTime(propertyAccountInfo.getSys0UpdTime());
				propertyAccountInfoEntity.setSys0UpdTime(now);
				boolean isSuccess = propertyAccountInfoDao.updatePropertyAccountInfoWithLock(propertyAccountInfoEntity);
				if(!isSuccess){
					jsonResponse.setMessage("操作异常，数据脏读！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
					new BusinessRuntimeException("PayRedEnvelopeService.propertyCard2Envelope. 操作异常，数据脏读！");
				} else {
					// 3、t_pay_red_envelope
					PayRedEnvelope payRedEnvelope = new PayRedEnvelope();
					payRedEnvelope.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_red_envelope));
					payRedEnvelope.setFromType(5);
					payRedEnvelope.setFromId(propertyAccountInfo.getId());
					payRedEnvelope.setFromTime(now);
					payRedEnvelope.setUserId(userId);
					{
						UserEntity user = userDao.selectUserById(userId);
						BigInteger roomId = user.getDefaultRoomEntity().getId();
						payRedEnvelope.setRoomId(roomId);
					}
					payRedEnvelope.setConverterId(userId);
					payRedEnvelope.setAmountTotal(amountTotal);
					payRedEnvelope.setAmountAvaible(amountTotal);
					payRedEnvelope.setStatus(0);
					payRedEnvelope.setSys0AddTime(now);
					payRedEnvelope.setSys0AddUser(userId);
					payRedEnvelope.setSys0DelState(0);
					
					payRedEnvelopeBaseDao.insertPayRedEnvelope(payRedEnvelope);
					
					// 4、用户购买记录表（t_user_has_property_card）余额置0
					if(userHasPropertyCards==null || userHasPropertyCards.size()==0){
						jsonResponse.setMessage("数据异常，操作失败！");
						jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
						new BusinessRuntimeException("PayRedEnvelopeService.propertyCard2Envelope. 数据异常，操作失败！");
					} else {
						for(UserHasPropertyCard userHasPropertyCard:userHasPropertyCards){
							UserHasPropertyCardEntity userHasPropertyCardEntity = new UserHasPropertyCardEntity(userHasPropertyCard);
							userHasPropertyCardEntity.setBalanceAmt(0L);
							userHasPropertyCardEntity.setOldUpdateTime(userHasPropertyCardEntity.getSys0UpdTime());
							userHasPropertyCardEntity.setSys0UpdTime(now);
							userHasPropertyCardEntity.setSys0UpdUser(userId);
							
							boolean valid = userHasPropertyCardDao.updateUserHasPropertyCardWithLcok(userHasPropertyCardEntity);
							if(!valid){
								jsonResponse.setMessage("操作异常，数据脏读！");
								jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
								new BusinessRuntimeException("PayRedEnvelopeService.propertyCard2Envelope. 操作异常，数据脏读！");
							}
						}
						
						// 5、代扣卡交易流水表，insert 每个代扣卡记录
						PropertyCardTransactionFlowing propertyCardTransactionFlowing = new PropertyCardTransactionFlowing();
						propertyCardTransactionFlowing.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_property_card_transaction_flowing));
						propertyCardTransactionFlowing.setDescription("代扣卡转粮票");
						{   // 参考PropertyCardService.updateData4Deducntion()
							StringBuilder number = new StringBuilder();
							number.append(DateUtil.formatMinuteTogether.get().format(new java.util.Date()));
							number.append(userId);
							number.append(RandomUtils.createRandom(true, 3));
							propertyCardTransactionFlowing.setTransNo(number.toString());
						}
						propertyCardTransactionFlowing.setTransAmt(amountTotal);
						propertyCardTransactionFlowing.setTransType(2);
						propertyCardTransactionFlowing.setTransTime(now);
						propertyCardTransactionFlowing.settUserFId(userId);
						propertyCardTransactionFlowing.setSys0AddTime(now);
						propertyCardTransactionFlowing.setSys0AddUser(userId);
						propertyCardTransactionFlowing.setSys0DelState(0);
						
						propertyCardTransactionFlowingBaseDao.insertPropertyCardTransactionFlowing(propertyCardTransactionFlowing);
						jsonResponse.setMessage("操作成功！");
					}
					jsonResponse.setMessage("操作成功！");
				}
			}
		}
		return jsonResponse;
	}

}
