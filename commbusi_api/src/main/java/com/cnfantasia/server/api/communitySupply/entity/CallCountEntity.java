/**   
* Filename:    CallCountEntity.java   
* @version:    1.0  
* Create at:   2014年8月27日 上午9:01:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月27日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.entity;

import java.io.Serializable;

/**
 * Filename:    CallCountEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月27日 上午9:01:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月27日       shiyl             1.0             1.0 Version
 */
public class CallCountEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**当前联系方式的拨打次数*/
	private Long count;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	/**所有联系方式总的拨打次数*/
	private Long totalCallCount;
	public Long getTotalCallCount() {
		return totalCallCount;
	}
	public void setTotalCallCount(Long totalCallCount) {
		this.totalCallCount = totalCallCount;
	}
	
	/**
	 * 构造方法
	 * @param count 当前联系方式的拨打次数
	 * @param totalCallCount 所有联系方式总的拨打次数
	 */
	public CallCountEntity(Long count,Long totalCallCount){
		this.count = count;
		this.totalCallCount = totalCallCount;
	}
	
	
}
