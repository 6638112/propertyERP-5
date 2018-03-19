/**   
* Filename:    PayRedEnvelopeDetailEntity.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午7:53:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.api.redenvelope.util.OrderType2ConsumDesc;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.payRedEnvelopeDetail.entity.PayRedEnvelopeDetail;

/**
 * Filename:    PayRedEnvelopeDetailEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午7:53:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class PayRedEnvelopeDetailEntity extends PayRedEnvelopeDetail{
	private static final long serialVersionUID = 1L;
	
	public BigDecimal getAnountDiv100() {
		if(getAnount()==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(getAnount());
	}
	
	/**
	 * 粮票消费对应的订单类型
	 */
	private Integer orderType;
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	public String fetchConsumInfo(){
		return OrderType2ConsumDesc.getConsumInfoByOrderType(orderType);
	}
	
	/**
	 * 消费者信息
	 */
	private UserSimpleEntity consumerUser;
	public UserSimpleEntity getConsumerUser() {
		return consumerUser;
	}
	public void setConsumerUser(UserSimpleEntity consumerUser) {
		this.consumerUser = consumerUser;
	}
	@Override
	public BigInteger getUserId() {
		if(consumerUser==null){return null;}
		return consumerUser.getId();
	}
	@Override
	public void setUserId(BigInteger userId) {
		if(consumerUser==null){consumerUser =  new UserSimpleEntity();}
		consumerUser.setId(userId);
	}

	
}
