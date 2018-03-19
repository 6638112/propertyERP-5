/**   
* Filename:    CouponCombiEntity.java   
* @version:    1.0  
* Create at:   2015年4月16日 上午7:16:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.surpriseGift.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Filename:    CouponCombiEntity.java
 * @version:    1.0.0
 * Create at:   2015年4月16日 上午7:16:27
 * Description:优惠信息实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月16日       shiyl             1.0             1.0 Version
 */
public class CouponCombiEntity extends CouponCombiBaseEntity{
	private static final long serialVersionUID = 1L;
	
	public CouponCombiEntity(List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList,Double payPercent,Long totalAmount){
		super(payPercent, totalAmount);
		
		Collections.sort(prizeSurpriseGiftList, new Comparator<PrizeSurpriseGiftEntity>() {
			@Override
			public int compare(PrizeSurpriseGiftEntity o1, PrizeSurpriseGiftEntity o2) {
				int res =  (int) (o1.getHbAmount()-o2.getHbAmount());
				if(res==0){//将大的排在前 为空则最大
					Long o1TimeLong = o1.getExpiredTimeLong();
					Long o2TimeLong = o2.getExpiredTimeLong();
					if(o1TimeLong == null){
						res = -1;
					}else if(o2TimeLong == null){
						res = 1;
					}else{
						res = (int)(o2TimeLong-o1TimeLong);
					}
				}
				return res;
			}
		});
		
		
		this.prizeSurpriseGiftList = prizeSurpriseGiftList;
		List<PrizeSurpriseGiftEntity> suggestEntityList = null;
		if(prizeSurpriseGiftList!=null&&prizeSurpriseGiftList.size()>0&&payPercent!=null&&totalAmount!=null){
			suggestEntityList = new ArrayList<PrizeSurpriseGiftEntity>();
			Long goalAmount = calculateMaxCouponAmount(totalAmount, payPercent); //(long) (totalAmount*payPercent);
			Long sum = 0L;
			PrizeSurpriseGiftEntity tempPrize = null;
			for(PrizeSurpriseGiftEntity tmpPrizeSurpriseGift:prizeSurpriseGiftList){
				if(tmpPrizeSurpriseGift.getHbAmount() >= goalAmount) {
					tempPrize = tmpPrizeSurpriseGift;
					sum = goalAmount;
					break;
				}
//				sum+=tmpPrizeSurpriseGift.getHbAmount();
//				suggestEntityList.add(tmpPrizeSurpriseGift);
//				//达到指定的额度则返回
//				if(sum>=goalAmount){ break;}
			}
			if(tempPrize == null) {
				tempPrize = prizeSurpriseGiftList.get(prizeSurpriseGiftList.size() - 1);
				sum = tempPrize.getHbAmount();
			}
			suggestEntityList.add(tempPrize);
			
			prizeSurpriseGiftList.clear();
			prizeSurpriseGiftList.add(tempPrize);
			
			//syl-add 2015-4-20 14:35:30 移除可能多出的
			Collections.sort(suggestEntityList, new Comparator<PrizeSurpriseGiftEntity>() {
				@Override
				public int compare(PrizeSurpriseGiftEntity o1, PrizeSurpriseGiftEntity o2) {
					int res =  (int) (o1.getHbAmount()-o2.getHbAmount());
					if(res==0){//将大的排在前 为空则最大
						Long o1TimeLong = o1.getExpiredTimeLong();
						Long o2TimeLong = o2.getExpiredTimeLong();
						if(o1TimeLong == null){
							res = -1;
						}else if(o2TimeLong == null){
							res = 1;
						}else{
							res = (int)(o2TimeLong-o1TimeLong);
						}
					}
					return res;
				}
			});
			
			List<BigInteger> tmpSuggestIdList = new ArrayList<BigInteger>();
			for(PrizeSurpriseGiftEntity tmpEntity:suggestEntityList){
				if(sum-tmpEntity.getHbAmount()>=goalAmount){
					sum = sum-tmpEntity.getHbAmount();
					continue;
				}
				tmpSuggestIdList.add(tmpEntity.getId());
			}
			
			Long savedAmount =  sum>goalAmount?goalAmount:sum;
			this.suggestSavedAmount = savedAmount;
			this.suggestIdList = tmpSuggestIdList;
		}
	}
	
//	public static void main(String[] args) {
//		List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList = new ArrayList<PrizeSurpriseGiftEntity>();
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("15"));
//			aa.setHbAmount(200L);
//			aa.setExpiryTime("2015-4-20 15:19:16");
//			prizeSurpriseGiftList.add(aa);
//		}
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("16"));
//			aa.setHbAmount(200L);
//			aa.setExpiryTime("2015-4-21 15:19:16");
//			prizeSurpriseGiftList.add(aa);
//		}
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("11"));
//			aa.setHbAmount(100L);
//			prizeSurpriseGiftList.add(aa);
//		}
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("14"));
//			aa.setHbAmount(200L);
//			prizeSurpriseGiftList.add(aa);
//		}
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("17"));
//			aa.setHbAmount(200L);
//			prizeSurpriseGiftList.add(aa);
//		}
//		{
//			PrizeSurpriseGiftEntity aa = new PrizeSurpriseGiftEntity();
//			aa.setId(new BigInteger("12"));
//			aa.setHbAmount(500L);
//			prizeSurpriseGiftList.add(aa);
//		}
//		Long totalAmount = 600L;
//		Double payPercent = 1.0;
//		CouponCombiEntity tmp = new CouponCombiEntity(prizeSurpriseGiftList, payPercent, totalAmount);
//		System.out.println(tmp.getSuggestIdList());
//	}
	
	/**总优惠列表*/
	private List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList;
	public List<PrizeSurpriseGiftEntity> getPrizeSurpriseGiftList() {
		return prizeSurpriseGiftList;
	}
//	public void setPrizeSurpriseGiftList(List<PrizeSurpriseGiftEntity> prizeSurpriseGiftList) {
//		this.prizeSurpriseGiftList = prizeSurpriseGiftList;
//	}
	
	
	/**建议的勾选项*/
	private List<BigInteger> suggestIdList;
	public List<BigInteger> getSuggestIdList() {
		return suggestIdList;
	}
//	public void setSuggestIdList(List<BigInteger> suggestIdList) {
//		this.suggestIdList = suggestIdList;
//	}
	
	/**建议使用优惠券所节省的金额*/
	private Long suggestSavedAmount;
	public Long getSuggestSavedAmount(){
		return suggestSavedAmount;
	}
	
	
	public boolean isContainCoupon(){
		boolean res = false;
		if(prizeSurpriseGiftList!=null&&prizeSurpriseGiftList.size()>0){
			res = true;
		}
		return res;
	}
	
}
