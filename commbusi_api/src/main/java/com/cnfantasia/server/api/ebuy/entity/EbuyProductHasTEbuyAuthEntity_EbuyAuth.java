/**   
 * Filename:    EbuyProductHasTEbuyAuthEntity_EbuyAuth.java   
 * @version:    1.0  
 * Create at:   2014年6月15日 上午7:22:58   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月15日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;
import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.entity.EbuyProductHasTEbuyAuth;

/**
 * Filename: EbuyProductHasTEbuyAuthEntity_EbuyAuth.java
 * 
 * @version: 1.0.0 Create at: 2014年6月15日 上午7:22:58 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月15日 shiyl 1.0 1.0 Version
 */
public class EbuyProductHasTEbuyAuthEntity_EbuyAuth extends EbuyProductHasTEbuyAuth {
	private static final long serialVersionUID = 1L;
	private EbuyAuth ebuyAuth;

	public EbuyAuth getEbuyAuth() {
		return ebuyAuth;
	}

	public void setEbuyAuth(EbuyAuth ebuyAuth) {
		this.ebuyAuth = ebuyAuth;
	}

	@Override
	public BigInteger gettEbuyAuthFId() {
		if (ebuyAuth == null) {
			return null;
		}
		return ebuyAuth.getId();
	}

	@Override
	public void settEbuyAuthFId(BigInteger tEbuyAuthFId) {
		if (ebuyAuth == null) {
			ebuyAuth = new EbuyAuth();
		}
		ebuyAuth.setId(tEbuyAuthFId);
	}

}
