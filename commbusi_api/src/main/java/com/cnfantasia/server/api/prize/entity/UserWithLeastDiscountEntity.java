/**   
* Filename:    UserWithLeastDiscountEntity.java   
* @version:    1.0  
* Create at:   2015年5月6日 下午1:02:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;

/**
 * Filename:    UserWithLeastDiscountEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月6日 下午1:02:26
 * Description:用户信息及对应月份的最低折扣信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月6日       shiyl             1.0             1.0 Version
 */
public class UserWithLeastDiscountEntity extends UserSimpleEntity{
		private static final long serialVersionUID = 1L;
		/**最低折扣*/
		private Double ext_leastDiscount;
		public Double getExt_leastDiscount() {
			return ext_leastDiscount;
		}
		public void setExt_leastDiscount(Double ext_leastDiscount) {
			this.ext_leastDiscount = ext_leastDiscount;
		}
		
		/**抽奖次数*/
		private Integer ext_doneCount;
		public Integer getExt_doneCount() {
			return ext_doneCount;
		}
		public void setExt_doneCount(Integer ext_doneCount) {
			this.ext_doneCount = ext_doneCount;
		}
		
}
