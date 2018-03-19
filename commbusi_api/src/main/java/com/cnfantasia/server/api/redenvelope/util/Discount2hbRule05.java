/**   
* Filename:    Discount2hbRule04.java   
* @version:    1.0  
* Create at:   2014年12月31日 上午9:05:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

import java.math.BigInteger;

import com.cnfantasia.server.api.commonBusiness.dao.ICommonRoomDao;
import com.cnfantasia.server.api.redenvelope.dao.IRedenvelopeDao;
import com.cnfantasia.server.domainbase.userHasTRoom.entity.UserHasTRoom;

/**
 * Filename:    Discount2hbRule05.java
 * @version:    1.0.0
 * Create at:   2014年12月31日 上午9:05:45
 * Description:去掉特使用户粮票兑换处理
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月31日       shiyl             1.0             1.0 Version
 */
public class Discount2hbRule05 extends AbstractDiscount2hbRule{
	
	private IRedenvelopeDao redenvelopeDao;
	public void setRedenvelopeDao(IRedenvelopeDao redenvelopeDao) {
		this.redenvelopeDao = redenvelopeDao;
	}
	
	private ICommonRoomDao commonRoomDao;
	public void setCommonRoomDao(ICommonRoomDao commonRoomDao) {
		this.commonRoomDao = commonRoomDao;
	}

	public static void main(String[] args) {
//		System.out.println(new Discount2hbRule04().getMoneyByDiscount(1.2));
//		System.out.println(new Discount2hbRule04().getMoneyByDiscount(2.2));
//		System.out.println(new Discount2hbRule04().getMoneyByDiscount(3.2));
//		System.out.println(new Discount2hbRule04().getMoneyByDiscount(6.2));
//		System.out.println(new Discount2hbRule04().getMoneyByDiscount(8.2));
		System.out.println(specialPlan(100L, 8.1));
		System.out.println(specialPlan(19550L, 8.1));
		System.out.println(specialPlan(100L, 8.1));
	}
	
	@Override
	public Long getMoneyByDiscount(Double discount) {
		return redenvelopeDao.selectMoneyByDiscount(discount);
	}
	
	
	@Override
	public UserHasTRoom getDefaultUserHasTRoom(BigInteger userId) {
		UserHasTRoom userHasTRoom = null;
		if(userId!=null){
			userHasTRoom = commonRoomDao.selectDefaultUserHasTRoom(userId);
		}
		return userHasTRoom;
	}

	@Override
	public Long getMoneyByDiscount(BigInteger userId, Double discount) {
		UserHasTRoom userHasTRoom = getDefaultUserHasTRoom(userId);
		return getMoneyByDiscount(userHasTRoom, discount);
	}

	@Override
	public Long getMoneyByDiscount(UserHasTRoom userHasTRoom, Double discount) {
		if(discount==null){return 0L;}
		//1.尝试获取特殊金额
		/*Long specialAmount = null;
		if(userHasTRoom!=null){
			if(SpecialUserChekUtil.checkIsSpecialUser(userHasTRoom)){//优先特使
				specialAmount = userHasTRoom.getPlanPropertyAmount();
			}else{//realRoom有金额则直接使用金额
				RealRoom realRoom = commonRoomDao.selectRealRoomByRoomId(userHasTRoom.gettRoomFId());
				if (realRoom != null
						&& (RoomDict.CheckStatus.WuXuShenHe == realRoom.getCheckStatus() || RoomStatusCheckUtil.checkIsRealRoomValidated(realRoom))) {
					//无需审核的门牌或门牌通过验证
					specialAmount=realRoom.getPropMoney();
				}
			}
		}*/
		//2.根据特殊金额判断兑换结果
		Long resAmount = null;
		/*if(specialAmount!=null){
			resAmount = specialPlan(specialAmount, discount);
		}else*/{
			resAmount = getMoneyByDiscount(discount);
		}
		return resAmount;
	}
	
//	@Override
//	public Long getMoneyByDiscount(Double discount) {
//		if(discount==null||discount<0||discount>=10){return 0L;}
//		if(discount>=0&&discount<1){return 30*100L;}
//		if(discount>=1&&discount<5){return 20*100L;}
//		if(discount>=5&&discount<8){return 15*100L;}
//		if(discount>=8&&discount<9){return 10*100L;}
//		if(discount>=9&&discount<10){return 5*100L;}
//		return 0L;
//	}
	
	private static Long specialPlan(Long amount,Double discount){
		if(amount==null||discount==null||discount>=10||discount<0){return 0L;}
		return (long)(amount*(100-discount*10))/100;
	}
	
}
