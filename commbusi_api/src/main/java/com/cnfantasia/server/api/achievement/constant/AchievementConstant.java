/**   
* Filename:    AchievementConstant.java   
* @version:    1.0  
* Create at:   2015年1月26日 上午10:34:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.achievement.constant;

import java.math.BigInteger;

/**
 * Filename:    AchievementConstant.java
 * @version:    1.0.0
 * Create at:   2015年1月26日 上午10:34:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月26日       shiyl             1.0             1.0 Version
 */
public class AchievementConstant {
	/**第一勇士*/
	public static final BigInteger Achieve_diyiyongshi_id = new BigInteger("100");
	/**认证用户*/
	public static final BigInteger Achieve_renzhyonghu_id = new BigInteger("101");
	
	/**勤劳蜜蜂*/
	public static final BigInteger Honour_maxPrizeCountBee_id = new BigInteger("201");
	/**幸运神手指*/
	public static final BigInteger Honour_leastDiscountPrizer_id = new BigInteger("202");
	
	public enum AchievementEnum{
		/**第一勇士*/
		diyiyongshi(Achieve_diyiyongshi_id,true)
		/**认证用户*/
		,renzhyonghu(Achieve_renzhyonghu_id,true);
		
		private BigInteger recordId;
		private Boolean isAboutRoom;
		public BigInteger getRecordId() {
			return recordId;
		}
		public void setRecordId(BigInteger recordId) {
			this.recordId = recordId;
		}
		public Boolean getIsAboutRoom() {
			return isAboutRoom;
		}
		public void setIsAboutRoom(Boolean isAboutRoom) {
			this.isAboutRoom = isAboutRoom;
		}
		private AchievementEnum(BigInteger recordId,Boolean isAboutRoom){
			this.recordId = recordId;
			this.isAboutRoom = isAboutRoom;
		}
	}
	
}
