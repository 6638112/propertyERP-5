/**   
* Filename:    EbuyBuyCarHasTEbuyProduct_WithExtData.java   
* @version:    1.0  
* Create at:   2015年2月3日 上午7:49:48   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年2月3日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    EbuyBuyCarHasTEbuyProduct_WithExtData.java
 * @version:    1.0.0
 * Create at:   2015年2月3日 上午7:49:48
 * Description:订单商品关系 包含一些拓展数据
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年2月3日       shiyl             1.0             1.0 Version
 */
public class EbuyBuyCarHasTEbuyProduct_WithExtData extends EbuyBuyCarHasTEbuyProductEntity_Product implements Comparable<EbuyBuyCarHasTEbuyProductEntity_Product>{
	private static final long serialVersionUID = 1L;
	
	public EbuyBuyCarHasTEbuyProduct_WithExtData(UserSimpleEntity buyUser,EbuyBuyCarHasTEbuyProductEntity_Product ebuyBuyCarHasTEbuyProductEntity_Product){
		this.buyUser = buyUser;
		this.ebuyBuyCarHasTEbuyProductEntity_Product = ebuyBuyCarHasTEbuyProductEntity_Product;
	}
	
	/**购物车上商品信息*/
	private EbuyBuyCarHasTEbuyProductEntity_Product ebuyBuyCarHasTEbuyProductEntity_Product;
	public EbuyBuyCarHasTEbuyProductEntity_Product getEbuyBuyCarHasTEbuyProductEntity_Product() {
		return ebuyBuyCarHasTEbuyProductEntity_Product;
	}
	public void setEbuyBuyCarHasTEbuyProductEntity_Product(
			EbuyBuyCarHasTEbuyProductEntity_Product ebuyBuyCarHasTEbuyProductEntity_Product) {
		this.ebuyBuyCarHasTEbuyProductEntity_Product = ebuyBuyCarHasTEbuyProductEntity_Product;
	}
	
	
	/**已注册的购买者*/
	private UserSimpleEntity buyUser;
	public UserSimpleEntity getBuyUser() {
		return buyUser;
	}
	public void setBuyUser(UserSimpleEntity buyUser) {
		this.buyUser = buyUser;
	}
	@Override
	public int compareTo(EbuyBuyCarHasTEbuyProductEntity_Product goal) {
		if(goal==null){
			return 0;
		}
		Long currBuyTime = getCreateTime()==null?0L:DateUtil.timeToLong(getCreateTime());
		Long goalBuyTime = goal.getCreateTime()==null?0L:DateUtil.timeToLong(goal.getCreateTime());
		return (int)(currBuyTime-goalBuyTime);
	}
	
	
}
