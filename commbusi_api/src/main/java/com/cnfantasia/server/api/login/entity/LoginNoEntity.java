package com.cnfantasia.server.api.login.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
/**
 * 描述:(用户登录账号) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class LoginNoEntity extends LoginNo{
	private static final long serialVersionUID = 1L;
	/**用户信息*/
	private UserEntity userEntity;
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	@Override
	public BigInteger gettUserFId() {
		if(userEntity==null){return null;}
		return userEntity.getId();
	}
	@Override
	public void settUserFId(BigInteger tUserFId) {
		if(userEntity==null){userEntity = new UserEntity() ;}
		userEntity.setId(tUserFId);
	}
//	/**
//	 * 判断是否为首次注册后的登录，true是
//	 * @return
//	 */
//	public Boolean getIsFirstRegist() {
//		if(userEntity==null){return null;}
//		if(userEntity.getIsFirstLoginStatus()==null){//为空则返回true
//			return true;
//		}else{
//			if(UserDict.User_IsFirst_LoginStatus.NEVER_LOGIN.compareTo(userEntity.getIsFirstLoginStatus())==0){
//				return true;
//			}else if(UserDict.User_IsFirst_LoginStatus.HAVE_LOGIN.compareTo(userEntity.getIsFirstLoginStatus())==0){
//				return false;
//			}else{
//				return null;
//			}
//		}
//	}
	
	/**最低折扣信息*/
	private PrizeRecordSimpleEntity leastDiscount;
	public PrizeRecordSimpleEntity getLeastDiscount() {
		return leastDiscount;
	}
	public void setLeastDiscount(PrizeRecordSimpleEntity leastDiscount) {
		this.leastDiscount = leastDiscount;
	}
	
	/**最低折扣可兑换的粮票金额*/
	private Long discountConvertMoney;
	public Long getDiscountConvertMoney() {
		return discountConvertMoney;
	}
	public void setDiscountConvertMoney(Long discountConvertMoney) {
		this.discountConvertMoney = discountConvertMoney;
	}
	
	
	/**剩余抽奖次数*/
	private Integer leftPrizeCount;
	public Integer getLeftPrizeCount() {
		return leftPrizeCount;
	}
	public void setLeftPrizeCount(Integer leftPrizeCount) {
		this.leftPrizeCount = leftPrizeCount;
	}
	
	/**粮票可用余额*/
	private Long hbBalance;
	public Long getHbBalance() {
		return hbBalance;
	}
	public void setHbBalance(Long hbBalance) {
		this.hbBalance = hbBalance;
	}
	
	/**积分信息*/
	private PointData pointData;
	public PointData getPointData() {
		return pointData;
	}
	public void setPointData(PointData pointData) {
		this.pointData = pointData;
	}
	
	
}
