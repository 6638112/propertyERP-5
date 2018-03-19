/**   
* Filename:    PrizeRecordTmpQuery.java   
* @version:    1.0  
* Create at:   2014年5月8日 上午11:05:40   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月8日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * Filename:    PrizeRecordTmpQuery.java
 * @version:    1.0.0
 * Create at:   2014年5月8日 上午11:05:40
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月8日       shiyl             1.0             1.0 Version
 */
public class PrizeRecordTmpQuery extends PrizeRecordTmp{
	private static final long serialVersionUID = 1L;
	/**查询抽奖时间：开始*/
	private String prizeTime_START;//此处命名需与Mybatis的sql处理保持一致
	/**查询抽奖时间：结束*/
	private String prizeTime_END;
	public String getPrizeTime_START() {
		return prizeTime_START;
	}
	public void setPrizeTime_START(String prizeTime_START) {
		this.prizeTime_START = prizeTime_START;
	}
	public String getPrizeTime_END() {
		return prizeTime_END;
	}
	public void setPrizeTime_END(String prizeTime_END) {
		this.prizeTime_END = prizeTime_END;
	}
	
}
