package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
/**
 * 描述:(中奖记录) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class PrizeRecordEntity extends PrizeRecordSimpleEntity{
	private static final long serialVersionUID = 1L;
	/**中奖人*/
	private UserSimpleEntity prizeUser;
	public UserSimpleEntity getPrizeUser() {
		return prizeUser;
	}
	public void setPrizeUser(UserSimpleEntity prizeUser) {
		this.prizeUser = prizeUser;
	}
	
	/**抽奖日期 家庭运势查询折扣列表时使用*/
	private String prizeDate;
	public String getPrizeDate() {
		return prizeDate;
	}
	public void setPrizeDate(String prizeDate) {
		this.prizeDate = prizeDate;
	}
	
	/**
	 * 重写抽奖时间
	 */
	@Override
	public String getPrizeTime() {
		String prizeTime = super.getPrizeTime();
		if(prizeTime==null){
			prizeTime = this.prizeDate;
		}
		return prizeTime;
	}
	
	
	
}
