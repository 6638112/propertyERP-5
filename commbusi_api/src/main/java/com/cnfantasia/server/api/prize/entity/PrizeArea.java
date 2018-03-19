package com.cnfantasia.server.api.prize.entity;

import java.util.List;


/**
 * 
* Filename:    PrizeArea.java
* @version:    1.0.0
* Create at:   2014年7月8日 上午2:57:07
* Description:	折扣分组
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2014年7月8日       shiyl             1.0             1.0 Version
 */
public interface PrizeArea {
//	public static final DiscountValueEntity unPrizeDiscount = new DiscountValueEntity(9800L);//未中奖时返回的折扣
//	public static final DiscountValueEntity prizeDiscount10 = new DiscountValueEntity(9800L);//中奖允许的最大折扣
//	public static final DiscountValueEntity unPrizeDiscount = PrizeRuleParamUtil.getUnPrizeDiscount();//未中奖时返回的折扣
//	public static final DiscountValueEntity prizeDiscount10 = PrizeRuleParamUtil.getPrizeDiscountMax();//中奖允许的最大折扣
	
	/**
	 * 随机获取一个折扣
	 * @return
	 */
	public DiscountValueEntity getDiscount();
	/**
	 * 返回实际替换的折扣数量
	 * @param discountValueList
	 * @return
	 */
	public int insertDiscount(List<DiscountValueEntity> discountValueList);
	/**
	 * 获取奖池初始化奖品数
	 * @return
	 */
	public int getPrizeInitCount();
	
	/**
	 * 获取指定索引对应的折扣
	 * @param index
	 * @return
	 */
	public DiscountValueEntity getData(int index);
	
	/**
	 * 获取折扣区间
	 * @return
	 */
	public DiscountInterval getInterval();

	/**
	 * 获取剩余奖品数
	 * @return
	 */
	public int getLeftCount();
	
	/**
	 * 折扣是否属于该奖品区
	 * @return
	 */
	public boolean isInArea(DiscountValueEntity discount);
	
	/**
	 * 折扣是否属于该奖品区
	 * @return
	 */
	public boolean isInArea(double discount);
	
	/**
	 * 从折扣区间内移除第一个匹配的折扣,将其标记为unPrizeDiscount，成功返回true，失败返回false
	 * @param discount
	 * @return
	 */
	public boolean removeInArea(double discount);
	
}
