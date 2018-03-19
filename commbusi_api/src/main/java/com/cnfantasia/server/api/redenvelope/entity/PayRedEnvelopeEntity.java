/**   
* Filename:    PayRedEnvelopeEntity.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午3:33:01   
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

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;

/**
 * Filename:    PayRedEnvelopeEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午3:33:01
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class PayRedEnvelopeEntity extends PayRedEnvelope{
	private static final long serialVersionUID = 1L;
	
	public BigDecimal getAmountTotalDiv100() {
		if(getAmountTotal()==null){return BigDecimal.valueOf(0L);}
		return PriceUtil.div100(getAmountTotal());
	}
	/**具体粮票来源详情*/
	private HbFromEntity  singalDetail;

	public HbFromEntity getSingalDetail() {
		return singalDetail;
	}

	public void setSingalDetail(HbFromEntity singalDetail) {
		this.singalDetail = singalDetail;
	}
	
	/**粮票兑换人信息*/
	private UserSimpleEntity converterUser;
	public UserSimpleEntity getConverterUser() {
		return converterUser;
	}
	public void setConverterUser(UserSimpleEntity converterUser) {
		this.converterUser = converterUser;
	}

	@Override
	public BigInteger getConverterId() {
		if(converterUser==null){return null;}
		return converterUser.getId();
	}

	@Override
	public void setConverterId(BigInteger converterId) {
		if(converterUser==null){converterUser = new UserSimpleEntity();}
		converterUser.setId(converterId);
	}

	
}
