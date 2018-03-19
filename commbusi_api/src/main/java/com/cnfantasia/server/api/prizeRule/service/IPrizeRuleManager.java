/**   
* Filename:    IPrizeRuleManager.java   
* @version:    1.0  
* Create at:   2014年8月29日 上午3:19:31   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.service;

import com.cnfantasia.server.api.prize.entity.DiscountValueEntity;
import com.cnfantasia.server.api.prizeRule.constant.PrizeRuleDict.ConfigType;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleDrawOnlinedaysEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateAreaEntity;
import com.cnfantasia.server.api.prizeRule.entity.PrizeRuleGenerateConfigEntity;

/**
 * Filename:    IPrizeRuleManager.java
 * @version:    1.0.0
 * Create at:   2014年8月29日 上午3:19:31
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月29日       shiyl             1.0             1.0 Version
 */
public interface IPrizeRuleManager {
	
	/**
	 * 初始化
	 */
	public void init();
	
	/**
   * 查询某个参数码对应的参数值
   * @param paraCode 参数码
   * @return 参数值
   */
  public String getParaValue(String paraCode);
  
  public PrizeRuleGenerateAreaEntity getPrizeAreaAConfig();
  public PrizeRuleGenerateAreaEntity getPrizeAreaBConfig();
  public PrizeRuleGenerateAreaEntity getPrizeAreaCConfig();
  public PrizeRuleGenerateAreaEntity getPrizeAreaDConfig();
  public PrizeRuleGenerateAreaEntity getPrizeAreaEConfig();
  
//  /**
//   * 通过用户数获取对应抽奖规则的配置
//   * @param userTotalCount
//   * @return
//   */
//  public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateUsercountConfig(Long userTotalCount,ConfigType configType);
  
  /**
   * 通过当前时间获取对应抽奖规则的配置
   * @param date
   * @param signStatus 签约状态:true 已签约 false 未签约
   * @return
   */
//  public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateTimeConfig(Date date);
  public PrizeRuleGenerateConfigEntity getPrizeRuleGenerateTimeConfig(String date,ConfigType configType);
  
  /**
   * 通过在线时间获取用户当前的中奖概率信息
   * @param onlineDays
   * @return
   */
  public PrizeRuleDrawOnlinedaysEntity getPrizeRuleDrawOnlinedays(Long onlineDays);
  
//  public DiscountValueEntity getUnPrizeDiscount();
//  public DiscountValueEntity getPrizeDiscountMax();
  
  /**
   * 更新重新加载参数信息到缓存
   */
  public void updParaValue();

	/**
	 * @return
	 */
  public DiscountValueEntity getUnPrizeDiscount();

	/**
	 * @return
	 */
  public DiscountValueEntity getPrizeDiscountMax();

	/**
	 * @return
	 */
  public int getMaxPrizeCount();

	/**
	 * @return
	 */
  public Long getRuleA1();

	/**
	 * @return
	 */
  public Long getRuleD();
  
  /**
   * @return
   */
  public DiscountValueEntity getPrizeLastCheckDiscount();
  
}
